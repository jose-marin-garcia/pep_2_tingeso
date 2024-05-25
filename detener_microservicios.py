import subprocess

def detener_microservicios():
    comando = "taskkill /F /IM java.exe"
    subprocess.run(comando, shell=True, check=True)

def main():
    detener_microservicios()

if __name__ == "__main__":
    main()
