# Sistema Web de Gestión de Citas Médicas

## Evidencia GA7-220501096-AA2-EV02

Proyecto académico desarrollado para la gestión de citas médicas.

## Módulo desarrollado

### Módulo de Pacientes

El módulo permite realizar las operaciones de:

- Registro de pacientes.
- Consulta de pacientes.
- Actualización de pacientes.
- Eliminación de pacientes.

## Tecnologías utilizadas

- Java
- Jakarta Servlet
- JSP
- JDBC
- MySQL
- Maven
- Apache Tomcat 11
- NetBeans
- Git
- GitHub

## Arquitectura

El proyecto utiliza una estructura organizada por capas:

- `conexion`: conexión con la base de datos.
- `modelo`: representación de los datos del paciente.
- `dao`: operaciones CRUD mediante JDBC.
- `servlet`: procesamiento de solicitudes HTTP mediante GET y POST.
- `webapp`: páginas JSP y formularios HTML.

## Funcionalidades probadas

### GET

El Servlet permite consultar y mostrar el listado de pacientes.

### POST

El Servlet permite:

- Registrar pacientes.
- Actualizar pacientes.
- Eliminar pacientes.

## Base de datos

Base de datos utilizada:

`proyecto`

Tabla principal:

`paciente`

La conexión utiliza la variable de entorno:

`DB_PASSWORD`

para evitar almacenar la contraseña directamente en el código fuente.

## Pruebas realizadas

Se verificó el funcionamiento de:

- Consulta de pacientes.
- Registro de pacientes.
- Actualización de datos.
- Eliminación de pacientes.
- Navegación entre páginas JSP.
- Comunicación entre Servlet, DAO y MySQL.

## Repositorio

Repositorio GitHub:

https://github.com/lauraarango14/SistemaCitasMedicasWeb
