package com.citamedicas.web.dao;

import com.citamedicas.web.conexion.ConexionBD;
import com.citamedicas.web.modelo.Paciente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // INSERTAR
    public boolean insertar(Paciente paciente) {

        String sql = "INSERT INTO paciente "
                + "(nombre, apellido, documento, telefono, correo, fecha_nacimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDocumento());
            ps.setString(4, paciente.getTelefono());
            ps.setString(5, paciente.getCorreo());
            ps.setDate(6, Date.valueOf(paciente.getFechaNacimiento()));

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // CONSULTAR TODOS
    public List<Paciente> listar() {

        List<Paciente> pacientes = new ArrayList<>();

        String sql = "SELECT id_paciente, nombre, apellido, documento, "
                + "telefono, correo, fecha_nacimiento "
                + "FROM paciente ORDER BY id_paciente";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setDocumento(rs.getString("documento"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setCorreo(rs.getString("correo"));

                Date fecha = rs.getDate("fecha_nacimiento");

                if (fecha != null) {
                    paciente.setFechaNacimiento(fecha.toLocalDate());
                }

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    // CONSULTAR POR ID
    public Paciente buscarPorId(int idPaciente) {

        String sql = "SELECT id_paciente, nombre, apellido, documento, "
                + "telefono, correo, fecha_nacimiento "
                + "FROM paciente WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPaciente);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Paciente paciente = new Paciente();

                    paciente.setIdPaciente(rs.getInt("id_paciente"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setApellido(rs.getString("apellido"));
                    paciente.setDocumento(rs.getString("documento"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setCorreo(rs.getString("correo"));

                    Date fecha = rs.getDate("fecha_nacimiento");

                    if (fecha != null) {
                        paciente.setFechaNacimiento(fecha.toLocalDate());
                    }

                    return paciente;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ACTUALIZAR
    public boolean actualizar(Paciente paciente) {

        String sql = "UPDATE paciente SET "
                + "nombre = ?, "
                + "apellido = ?, "
                + "documento = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "fecha_nacimiento = ? "
                + "WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDocumento());
            ps.setString(4, paciente.getTelefono());
            ps.setString(5, paciente.getCorreo());
            ps.setDate(6, Date.valueOf(paciente.getFechaNacimiento()));
            ps.setInt(7, paciente.getIdPaciente());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int idPaciente) {

        String sql = "DELETE FROM paciente WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPaciente);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}