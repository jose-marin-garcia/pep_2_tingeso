import subprocess
import time

def iniciar_microservicios(ruta_raiz):
    microservicios = [
        "gateway-server",
        "ms-prices",
        "ms-repairs",
        "ms-vehicles"
    ]
    
    procesos = {}
    for microservicio in microservicios:
        ruta_microservicio = f"{ruta_raiz}\\{microservicio}"
        comando = f"cd {ruta_microservicio} & mvn spring-boot:start"
        proceso = subprocess.Popen(comando, shell=True)
        procesos[microservicio] = proceso
        print(f"Iniciando {microservicio}... PID: {proceso.pid}")
        time.sleep(20)
    return procesos

def detener_microservicios(procesos):
    for microservicio, proceso in procesos.items():
        comando = "taskkill /F /PID {proceso.pid}"
        subprocess.run(comando, shell=True, check=True)
        #proceso.terminate()  # Envía una señal para terminar el proceso
        print(f"Deteniendo {microservicio}... PID: {proceso.pid}")

def main():
    ruta_raiz = "C:\\Users\\josec\\Desktop\\2024\\1er_sem\\tingeso\\pep_2_tingeso"
    procesos = iniciar_microservicios(ruta_raiz)
    respuesta = input("¿Quiere detener estos procesos? (S/n)? ")
    if respuesta.lower() == 's':
        detener_microservicios(procesos)
    else:
        print("Los microservicios continuarán ejecutándose.")

if __name__ == "__main__":
    main()