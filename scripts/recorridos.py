"""Compila y ejecuta un recorrido docente sobre las estructuras del curso."""
import argparse
from pathlib import Path
import subprocess
import tempfile
from ejecutar import ROOT, JAVA, compilar

TEMAS = ('copias', 'amortizacion', 'colecciones', 'claves', 'trie', 'topologico', 'catalogo', 'todos')


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('tema', nargs='?', default='copias', choices=TEMAS)
    args = parser.parse_args()
    with tempfile.TemporaryDirectory(prefix='curso-recorrido-') as carpeta:
        fuentes = sorted((ROOT / 'src/curso').glob('*.java'))
        fuentes.append(ROOT / 'laboratorios/recorridos/Recorridos.java')
        compilar(Path(carpeta), fuentes)
        subprocess.run(JAVA + ['-cp', carpeta, 'Recorridos', args.tema], cwd=ROOT, check=True)


if __name__ == '__main__':
    main()
