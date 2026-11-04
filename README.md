# Sistema Web de Gestión de Citas Médicas

Proyecto académico desarrollado para la gestión integral de pacientes y citas médicas.

El sistema reúne módulos desarrollados con tecnologías Java, Jakarta EE, Spring Boot y React, utilizando MySQL como sistema gestor de base de datos.

## Evidencias del proyecto

- GA7-220501096-AA2-EV02: Módulo de pacientes desarrollado con Servlets y JSP.
- GA7-220501096-AA5-EV01: Diseño y desarrollo de servicios web para registro y autenticación.

## 1. Módulo de pacientes

Se desarrolló un módulo para administrar la información de los pacientes.

### Funcionalidades

- Registro de pacientes.
- Consulta de pacientes.
- Actualización de información.
- Eliminación de pacientes.
- Búsqueda por nombre, apellido o documento.

### Tecnologías utilizadas

- Java.
- Jakarta Servlet.
- JSP.
- JDBC.
- MySQL.
- Maven.
- Apache Tomcat 11.
- Apache NetBeans.

### Arquitectura

El módulo utiliza una organización por capas:

- `conexion`: conexión con la base de datos.
- `modelo`: representación de los datos.
- `dao`: operaciones CRUD mediante JDBC.
- `servlet`: procesamiento de solicitudes HTTP.
- `webapp`: páginas JSP y formularios HTML.

### Pruebas realizadas

Se verificaron las operaciones de consulta, registro, actualización y eliminación de pacientes, así como la comunicación entre Servlet, DAO y MySQL.

## 2. Servicios web REST: registro y autenticación

Se desarrollaron servicios web REST utilizando Spring Boot para registrar usuarios y validar sus credenciales de acceso.

### Tecnologías utilizadas

- Java 17.
- Spring Boot 3.5.6.
- Spring Web.
- Spring Data JPA.
- MySQL.
- BCrypt.
- Maven.
- Postman.

### Servicios implementados

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/auth/registro` | Registra un usuario nuevo. |
| POST | `/api/auth/login` | Valida las credenciales de acceso. |

### Seguridad

Las contraseñas se protegen mediante BCrypt antes de almacenarse en la base de datos.

### Pruebas realizadas con Postman

| Prueba | Resultado |
|---|---|
| Registro exitoso | HTTP 201 Created |
| Registro duplicado | HTTP 400 Bad Request |
| Inicio de sesión correcto | HTTP 200 OK |
| Contraseña incorrecta | HTTP 401 Unauthorized |

### Organización del servicio

- `controller`: recibe las solicitudes HTTP.
- `servicio`: contiene la lógica de registro y autenticación.
- `modelo`: representa la entidad Usuario.
- `repository`: administra las operaciones de persistencia.

## 3. Frontend React

Se incorporó una interfaz web desarrollada con React y Vite para la gestión visual de pacientes.

### Tecnologías utilizadas

- React.
- JavaScript.
- Vite.
- CSS.
- Visual Studio Code.

### Funcionalidades implementadas

- Visualización del listado de pacientes.
- Registro de pacientes.
- Edición de pacientes.
- Eliminación de pacientes.
- Búsqueda de pacientes.
- Visualización del total de registros.

### Integración con Spring Boot

El frontend consume los servicios REST del backend mediante solicitudes HTTP.

Endpoint de pacientes:

`http://localhost:8080/api/pacientes`

Dirección de desarrollo del frontend:

`http://localhost:5173`

Se verificó la comunicación entre React, Spring Boot y MySQL mediante pruebas de consulta, registro, actualización y eliminación.

## 4. Base de datos

Base de datos utilizada:

`proyecto`

Tablas utilizadas en los módulos:

- `paciente`: utilizada por el módulo anterior desarrollado con JDBC y Servlets.
- `pacientes`: utilizada por el módulo de pacientes desarrollado con Spring Data JPA.
- `usuarios`: utilizada para el registro y autenticación.

La configuración de conexión del backend Spring Boot utiliza variables de entorno para proteger las credenciales de la base de datos.

## 5. Control de versiones

El proyecto se administra mediante Git y GitHub.

Se mantiene un único repositorio para integrar los módulos y las evidencias académicas del sistema.

## 6. Repositorio oficial

GitHub:

https://github.com/lauraarango14/SistemaCitasMedicasWeb

## 7. Conclusión

El proyecto integra módulos de gestión de pacientes y servicios web de registro y autenticación, junto con una interfaz desarrollada en React.

Se realizaron pruebas funcionales para verificar las operaciones CRUD, la persistencia de la información en MySQL y el funcionamiento de los servicios REST.

El repositorio centraliza el desarrollo y la documentación de las evidencias del proyecto formativo.