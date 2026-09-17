<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.citamedicas.web.modelo.Paciente" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Gestión de Pacientes</title>
</head>

<body>

    <h1>Gestión de Pacientes</h1>

    <p>
        <a href="index.jsp">Volver al inicio</a>
    </p>

    <p>
        <a href="formularioPaciente.jsp">Registrar nuevo paciente</a>
    </p>

    <hr>

    <h2>Listado de pacientes</h2>

    <%
        List<Paciente> pacientes =
                (List<Paciente>) request.getAttribute("pacientes");
    %>

    <% if (pacientes != null && !pacientes.isEmpty()) { %>

        <table border="1" cellpadding="8" cellspacing="0">

            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Documento</th>
                <th>Teléfono</th>
                <th>Correo</th>
                <th>Fecha de nacimiento</th>
                <th>Acciones</th>
            </tr>

            <% for (Paciente paciente : pacientes) { %>

                <tr>

                    <td>
                        <%= paciente.getIdPaciente() %>
                    </td>

                    <td>
                        <%= paciente.getNombre() %>
                    </td>

                    <td>
                        <%= paciente.getApellido() %>
                    </td>

                    <td>
                        <%= paciente.getDocumento() %>
                    </td>

                    <td>
                        <%= paciente.getTelefono() %>
                    </td>

                    <td>
                        <%= paciente.getCorreo() %>
                    </td>

                    <td>
                        <%= paciente.getFechaNacimiento() %>
                    </td>

                    <td>

                        <a href="pacientes?accion=editar&id=<%= paciente.getIdPaciente() %>">
                            Editar
                        </a>

                        <br><br>

                        <form action="pacientes" method="post">

                            <input type="hidden"
                                   name="accion"
                                   value="eliminar">

                            <input type="hidden"
                                   name="id"
                                   value="<%= paciente.getIdPaciente() %>">

                            <button type="submit">
                                Eliminar
                            </button>

                        </form>

                    </td>

                </tr>

            <% } %>

        </table>

    <% } else { %>

        <p>No hay pacientes registrados.</p>

    <% } %>

</body>

</html>