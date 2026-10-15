from pathlib import Path
import json,re,subprocess,tempfile
from ejecutar import ROOT,JAVA,compilar

def correr(comando,carpeta,entrada=""):
    p=subprocess.run(comando,cwd=carpeta,input=entrada,capture_output=True,text=True,encoding="utf-8",timeout=120)
    if p.returncode:raise RuntimeError(f"Error:\n{p.stdout}\n{p.stderr}")
    return p.stdout.replace("\r\n","\n")

def compilar_en(salida,fuentes,clases=None):
    args=JAVA+["com.sun.tools.javac.Main","-encoding","UTF-8","-d",str(salida)]
    if clases:args += ["-cp",str(clases)]
    correr(args+[str(f) for f in fuentes],salida)

def main():
    ejemplos=json.loads((ROOT/"scripts/manifest.json").read_text(encoding="utf-8"))
    tests=json.loads((ROOT/"tests/manifest.json").read_text(encoding="utf-8"))
    with tempfile.TemporaryDirectory() as temporal:
        base=Path(temporal);biblioteca=base/"biblioteca";biblioteca.mkdir()
        compilar_en(biblioteca,sorted((ROOT/"src/curso").glob("*.java"))+[ROOT/"tests/Pruebas.java",ROOT/"tests/Fundamentos.java",ROOT/"laboratorios/recorridos/Recorridos.java"])
        for clase in ["Pruebas","Fundamentos"]:print(correr(JAVA+["-cp",str(biblioteca),clase],base),end="",flush=True)
        recorrido=correr(JAVA+["-cp",str(biblioteca),"Recorridos","todos"],base)
        assert recorrido==(ROOT/"laboratorios/recorridos/esperado.txt").read_text(encoding="utf-8"),"Salida de recorridos guiados"
        print(recorrido,end="",flush=True)
        geo=json.loads(correr(JAVA+["-cp",str(biblioteca),"Pruebas","--geo"],base))
        assert geo["features"][0]["properties"]["id"]=='Salón "A"\n\\'
        assert geo["features"][0]["geometry"]["coordinates"]==[-75.6,6.2]
        assert len(geo["features"])==3
        for i,ruta in enumerate(ejemplos):
            salida=base/f"ejemplo{i}";salida.mkdir()
            fuentes=[ROOT/ruta/"Main.java"]
            if ruta in tests:fuentes.append(ROOT/"tests"/ruta/"Prueba.java")
            compilar_en(salida,fuentes,biblioteca)
            cp=str(salida)+__import__("os").pathsep+str(biblioteca)
            actual=correr(JAVA+["-cp",cp,"Main"],salida)
            esperado=(ROOT/ruta/"esperado.txt").read_text(encoding="utf-8")
            if actual!=esperado:raise RuntimeError(f"Salida distinta en {ruta}\n{actual}\n{esperado}")
            doc=(ROOT/ruta/"README.md").read_text(encoding="utf-8")
            fuente=re.search(r"```java\n(.*?)\n```",doc,re.S)
            assert fuente and fuente.group(1)==(ROOT/ruta/"Main.java").read_text(encoding="utf-8").rstrip(),ruta
            salida_doc=re.search(r"### Salida esperada.*?```text\n(.*?)\n```",doc,re.S)
            assert salida_doc and salida_doc.group(1)==esperado.rstrip(),ruta
            if ruta in tests:assert correr(JAVA+["-cp",cp,"Prueba"],salida).strip()=="Pruebas correctas",ruta
            if ruta.endswith("catalogo-e-indice-invertido"):
                guion="1\n4\nÁrboles nuevos\nAna\n3\narb\n5\ncatalogo.txt\n2\n4\n4\n4\n6\ncatalogo.txt\n2\n4\n9\n0\n"
                menu=correr(JAVA+["-cp",cp,"Main","--menu"],salida,guion)
                for texto in ["Libro agregado","Catálogo guardado","Libro eliminado","Catálogo cargado","Árboles nuevos","Opción inválida","Fin"]:assert texto in menu,(texto,menu)
            if (i+1)%10==0:print(f"Ejemplos: {i+1}/{len(ejemplos)}",flush=True)
        print(f"Pruebas por recurso: {len(tests)}",flush=True)
    enlaces=0
    for p in ROOT.rglob("*.md"):
        texto=p.read_text(encoding="utf-8")
        assert not re.search(r"\uFFFD|Ã[\x80-\xBF]|Â[\x80-\xBF]|[\uE200\uE201]|contentReference|oaicite|<\|(?:system|assistant|im_start|im_end)\|>",texto),p
        assert len(re.findall(r"^\s*```",texto,re.M))%2==0,p
        s=re.sub(r"```.*?```","",texto,flags=re.S)
        assert "<!-- RESULTADO -->" not in s,p
        for destino in re.findall(r"\[[^\]]*\]\(([^)]+)\)",s):
            if re.match(r"[A-Za-z][A-Za-z0-9+.-]*:",destino) or destino.startswith("#"):continue
            ruta=destino.split("#",1)[0]
            if ruta and not (p.parent/ruta).exists():raise RuntimeError(f"Enlace roto: {p.relative_to(ROOT)} -> {destino}")
            enlaces+=1
    print(f"Enlaces locales: {enlaces}")
    print("Verificación completa")

if __name__=="__main__":main()
