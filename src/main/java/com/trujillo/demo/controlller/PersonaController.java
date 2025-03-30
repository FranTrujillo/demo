package com.trujillo.demo.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trujillo.demo.model.Persona;
import com.trujillo.demo.repository.PersonaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/personas")
@Tag(name = "Persona Controller", description = "API for personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    @Operation(summary = "Get All Personas", description = "Retorna Lista de Personas")
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @GetMapping("/{identificacion}")
    @Operation(summary = "Get Persona by ID", description = "Returna por ID.")
    public Persona getPersonaById(@PathVariable String identificacion) {
        return personaRepository.findById(identificacion).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create Persona", description = "Crea persona.")
    public Persona createPersona(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

    @PutMapping("/{identificacion}")
    @Operation(summary = "Update Persona", description = "Actualiza Persona.")
    public Persona updatePersona(@PathVariable String identificacion, @RequestBody Persona persona) {
        if (personaRepository.existsById(identificacion)) {
            persona.setIdentificacion(identificacion);
            return personaRepository.save(persona);
        }
        return null;
    }

    @DeleteMapping("/{identificacion}")
    @Operation(summary = "Delete Persona", description = "Elimina Persona.")
    public void deletePersona(@PathVariable String identificacion) {
        personaRepository.deleteById(identificacion);
    }
}