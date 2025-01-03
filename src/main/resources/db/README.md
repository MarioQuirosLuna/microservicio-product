# Conexión local a base de datos

## Comandos para crear DB Oracle con docker

1. Verificar si esta instalado docker e instalarlo de ser necesario

```bash
$ docker --version
```

2. Descargar imagen de ORACLE

* Iniciar sesión en el registry de ORACLE con usuario propio
```bash
$ docker login container-registry.oracle.com
```

* Ingresar usuario y contraseña

3. Descargar la imagen de ORACLE SQL necesaria de

Oracle Container Registry: Descargar [aqui](https://container-registry.oracle.com/ords/f?p=113:1:103771676359737:::1:P1_BUSINESS_AREA:3&cs=3lr3baYFRLxuH0ep51d991rI53HuhElEZBVpZt4FWciO3bT-nJkG3zE4zhpEM_ZYESDLk0WQuBUQpitg1PX1dJw "Database Repositories")

```bash
$ sudo docker pull container-registry.oracle.com/database/{repository}:{version}
```

4. Configurar contenedor de docker para base de datos ORACLE

```bash
$ sudo docker run -d   -p 1521:1521   -p 5500:5500   --name oracle-db   -e ORACLE_PDB=ORCLPDB1   -e ORACLE_PWD=mySecurePassword   container-registry.oracle.com/database/{repository}:{version}
```
* Explicación de las opciones:
    -d: Ejecuta el contenedor en segundo plano.
    -p 1521:1521: Mapea el puerto 1521 del contenedor al puerto 1521 en tu máquina local (puerto estándar de Oracle).
    -p 5500:5500: Mapea el puerto 5500 para el Oracle Enterprise Manager (opcional).
    --name oracle-db: Nombra el contenedor como oracle-db.
    -e ORACLE_PDB=ORCLPDB1: Crea un PDB (Pluggable Database) con el nombre ORCLPDB1.
    -e ORACLE_PWD=mySecurePassword: Establece la contraseña del usuario sys y system a mySecurePassword.

5. Ejecutar docker con Oracle SQL

```bash
$ sudo docker exec -it oracle-db bash
```

6. Ingresar a la DB Oracle usando sqlplus

```bash
$ sqlplus sys/mySecurePassword as sysdba
```

7. Crear un nuevo usuario

* Este usuario será usado para ingresar desde el app

```SQL
CREATE USER dev_user IDENTIFIED BY dev_pass;
GRANT CONNECT, RESOURCE TO dev_user;
```

* Asignar cuota al usuario en el tablespace

```SQL
ALTER USER dev_user QUOTA UNLIMITED ON USERS;
```

* Otorgar permisos al usuario para la tabla productos

```SQL
GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW TO dev_user;
GRANT INSERT, UPDATE, DELETE, SELECT ON products TO dev_user;
```

## Extenciones utiles para usar DB de docker

1. (Opcional) Descargar extension en visual studio para poder manipublar la base de datos mas facilmente

Oracle: Descargar [aqui](https://marketplace.visualstudio.com/items?itemName=LinJun.oracle-support "Support connecting to Oracle in Visual Studio Code")

* Configurar conexion con Oracle

- SERVER TYPE: Oracle
- HOST: 127.0.0.1
- PORT: 1521
- USERNAME: dev_user
- PASSWORD: dev_pass
- DATABASE: XEPDB1

* Guardar y conectar a la base de datos

## Conexión por medio del APP

1. Configurar en el application.properties

```bash
server.port=8001

spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=dev_user
spring.datasource.password=dev_pass
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```