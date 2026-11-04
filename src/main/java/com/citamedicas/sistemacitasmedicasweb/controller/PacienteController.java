
package com.citamedicas.sistemacitasmedicasweb.controller;

import com.citamedicas.sistemacitasmedicasweb.modelo.Paciente;
import com.citamedicas.sistemacitasmedicasweb.repository.PacienteRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // RUTAS HTML EXISTENTES

    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "pacientes";
    }

    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "formularioPaciente";
    }

    @PostMapping("/guardar")
    public String guardarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Paciente no encontrado: " + id));

        model.addAttribute("paciente", paciente);
        return "formularioPaciente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/pacientes";
    }

    // API REST PARA REACT

    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseBody
    @GetMapping("/api/pacientes")
    public List<Paciente> listarPacientesApi() {
        return pacienteRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseBody
    @GetMapping("/api/pacientes/{id}")
    public ResponseEntity<Paciente> buscarPacienteApi(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseBody
    @PostMapping("/api/pacientes")
    public ResponseEntity<Paciente> crearPacienteApi(
            @RequestBody Paciente paciente) {
        paciente.setId(null);
        Paciente guardado = pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseBody
    @PutMapping("/api/pacientes/{id}")
    public ResponseEntity<Paciente> actualizarPacienteApi(
            @PathVariable Long id,
            @RequestBody Paciente datos) {

        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNombre(datos.getNombre());
                    paciente.setApellido(datos.getApellido());
                    paciente.setDocumento(datos.getDocumento());
                    paciente.setTelefono(datos.getTelefono());
                    paciente.setCorreo(datos.getCorreo());
                    paciente.setFechaNacimiento(datos.getFechaNacimiento());

                    Paciente actualizado = pacienteRepository.save(paciente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseBody
    @DeleteMapping("/api/pacientes/{id}")
    public ResponseEntity<Void> eliminarPacienteApi(@PathVariable Long id) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
