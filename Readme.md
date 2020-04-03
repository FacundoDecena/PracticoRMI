# Practico RMI

### Para ejecutar los proyectos A y B

1. Ubicarse en /build
2. Ejecutar el comando ```start rmiregistry 3001```, no cerrar el programa que inicia.
3. Ejecutar el comando ```java Server```
4. Ejecutar el comando ```java Client``` en una consola distinta a la del servidor

### Para ejecutar el proyecto B-Base-de-Datos
##### (Debe tener maven instalado)

1. Ubicarse en /target/clases
2. Ejecutar el comando ```start rmiregistry 3001```, no cerrar el programa que inicia.
3. Ubicarse en la raiz del proyecto
4. Ejecutar el comando ```mvn exec:java -Dexec.mainClass="Server"```
5. Ejecutar el comando ```mvn exec:java -Dexec.mainClass="Cliente"``` en una consola distinta a la del servidor
