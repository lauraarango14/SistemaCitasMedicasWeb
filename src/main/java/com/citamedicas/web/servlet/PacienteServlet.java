package com.citamedicas.web.servlet;

import com.citamedicas.web.dao.PacienteDAO;
import com.citamedicas.web.modelo.Paciente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    // MÉTODO GET
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        // Editar paciente
        if ("editar".equals(accion)) {

            int id = Integer.parseInt(request.getParameter("id"));

            Paciente paciente = pacienteDAO.buscarPorId(id);

            request.setAttribute("paciente", paciente);

            request.getRequestDispatcher("/formularioPaciente.jsp")
                   .forward(request, response);

            return;
        }

        // Listar pacientes
        List<Paciente> pacientes = pacienteDAO.listar();

        request.setAttribute("pacientes", pacientes);

        request.getRequestDispatcher("/pacientes.jsp")
               .forward(request, response);
    }

    // MÉTODO POST
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {

            Paciente paciente = crearPacienteDesdeFormulario(request);

            pacienteDAO.insertar(paciente);

        } else if ("actualizar".equals(accion)) {

            int id = Integer.parseInt(request.getParameter("id"));

            Paciente paciente = crearPacienteDesdeFormulario(request);

            paciente.setIdPaciente(id);

            pacienteDAO.actualizar(paciente);

        } else if ("eliminar".equals(accion)) {

            int id = Integer.parseInt(request.getParameter("id"));

            pacienteDAO.eliminar(id);
        }

        // Regresar al listado
        response.sendRedirect(request.getContextPath() + "/pacientes");
    }

    // Crear objeto Paciente con los datos del formulario
    private Paciente crearPacienteDesdeFormulario(HttpServletRequest request) {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        LocalDate fecha = LocalDate.parse(fechaNacimiento);

        return new Paciente(
                nombre,
                apellido,
                documento,
                telefono,
                correo,
                fecha
        );
    }
}