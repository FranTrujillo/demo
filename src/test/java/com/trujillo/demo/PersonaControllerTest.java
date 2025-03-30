package com.trujillo.demo;

import com.trujillo.demo.model.Persona;
import com.trujillo.demo.repository.PersonaRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(com.trujillo.demo.controlller.PersonaController.class)
public class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaRepository personaRepository;

    Persona persona1 = new Persona("1", "Andrea", "femenino", 30, "De los Shyris", "09999999999");
    Persona persona2 = new Persona("2", "Juan", "masculino", 25, "De los Shyris", "09999999999");

    @Test
    public void testGetAllPersonas() throws Exception {

        Mockito.when(personaRepository.findAll()).thenReturn(Arrays.asList(persona1, persona2));

        mockMvc.perform(get("/personas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].identificacion").value("1"))
                .andExpect(jsonPath("$[0].nombre").value("Andrea"))
                .andExpect(jsonPath("$[1].identificacion").value("2"))
                .andExpect(jsonPath("$[1].nombre").value("Juan"));
    }

    @Test
    public void testGetPersonaById() throws Exception {

        Mockito.when(personaRepository.findById("1")).thenReturn(Optional.of(persona1));

        mockMvc.perform(get("/personas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identificacion").value("1"))
                .andExpect(jsonPath("$.nombre").value("Andrea"));
    }

    @Test
    public void testCreatePersona() throws Exception {

        Mockito.when(personaRepository.save(any(Persona.class))).thenReturn(persona1);

        mockMvc.perform(post("/personas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"identificacion\":\"1\",\"nombre\":\"Andrea\",\"genero\":\"femenino\",\"edad\":30,\"direccion\":\"De los Shyris\",\"telefono\":\"09999999999\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identificacion").value("1"))
                .andExpect(jsonPath("$.nombre").value("Andrea"));
    }

    @Test
    public void testUpdatePersona() throws Exception {

        Mockito.when(personaRepository.existsById("1")).thenReturn(true);
        Persona personaUpdated = new Persona("1", "Karla", "femenino", 30, "De los Shyris", "09999999999");

        Mockito.when(personaRepository.save(any(Persona.class))).thenReturn(personaUpdated);

        mockMvc.perform(put("/personas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"nombre\":\"Karla\",\"genero\":\"femenino\",\"edad\":30,\"direccion\":\"De los Shyris\",\"telefono\":\"09999999999\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identificacion").value("1"))
                .andExpect(jsonPath("$.nombre").value("Karla"));
    }

    @Test
    public void testDeletePersona() throws Exception {
        Mockito.doNothing().when(personaRepository).deleteById("1");

        mockMvc.perform(delete("/personas/1"))
                .andExpect(status().isOk());
    }
}