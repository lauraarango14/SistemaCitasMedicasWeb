<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.citamedicas.web.modelo.Paciente" %>

<%
    Paciente paciente = (Paciente) request.getAttribute("paciente");

    boolean editar = paciente != null;

    String titulo = editar ? "Editar paciente" : "Registrar paciente";
    String accion = editar ? "actualizar" : "insertar";
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title><%= titulo %></title>
</head>

<body>

    <h1><%= titulo %></h1>

    <form action="pacientes" method="post">

        <input type="hidden"
               name="accion"
               value="<%= accion %>">

        <% if (editar) { %>

            <input type="hidden"
                   name="id"
                   value="<%= paciente.getIdPaciente() %>">

        <% } %>

        <p>
            <label for="nombre">Nombre:</label><br>
            <input type="text"
                   id="nombre"
                   name="nombre"
                   value="<%= editar ? paciente.getNombre() : "" %>"
                   required>
        </p>

        <p>
            <label for="apellido">Apellido:</label><br>
            <input type="text"
                   id="apellido"
                   name="apellido"
                   value="<%= editar ? paciente.getApellido() : "" %>"
                   required>
        </p>

        <p>
            <label for="documento">Documento:</label><br>
            <input type="text"
                   id="documento"
                   name="documento"
                   value="<%= editar ? paciente.getDocumento() : "" %>"
                   required>
        </p>

        <p>
            <label for="telefono">Teléfono:</label><br>
            <input type="text"
                   id="telefono"
                   name="telefono"
                   value="<%= editar ? paciente.getTelefono() : "" %>"
                   required>
        </p>

        <p>
            <label for="correo">Correo:</label><br>
            <input type="email"
                   id="correo"
                   name="correo"
                   value="<%= editar ? paciente.getCorreo() : "" %>">
        </p>

        <p>
            <label for="fechaNacimiento">Fecha de nacimiento:</label><br>
            <input type="date"
                   id="fechaNacimiento"
                   name="fechaNacimiento"
                   value="<%= editar ? paciente.getFechaNacimiento() : "" %>"
                   required>
        </p>

        <p>
            <button type="submit">
                <%= editar ? "Actualizar paciente" : "Registrar paciente" %>
            </button>
        </p>

    </form>

    <p>
        <a href="pacientes">Volver al listado</a>
    </p>

</body>

</html>