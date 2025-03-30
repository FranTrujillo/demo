package com.trujillo.demo;

import com.trujillo.demo.controlller.ClienteController;
import com.trujillo.demo.model.Cliente;
import com.trujillo.demo.repository.ClienteRepository;
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

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository clienteRepository;

    Cliente cliente1 = new Cliente("1", "pass1234", true);
    Cliente cliente2 = new Cliente("2", "pass1234", true);

    @Test
    public void testGetAllClientes() throws Exception {

        Mockito.when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clienteId").value("1"))
                .andExpect(jsonPath("$[1].clienteId").value("2"));
    }

    @Test
    public void testGetClienteById() throws Exception {

        Mockito.when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente1));

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value("1"));
    }

    @Test
    public void testCreateCliente() throws Exception {

        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente1);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"1\",\"contrasena\":\"pass567\",\"estado\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value("1"));
    }

    @Test
    public void testUpdateCliente() throws Exception {

        Mockito.when(clienteRepository.existsById("1")).thenReturn(true);
        Cliente clienteUpdated = new Cliente("1", "pass1234", false);
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteUpdated);

        mockMvc.perform(put("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"1\",\"contrasena\":\"pass1234\",\"estado\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value("1"))
                .andExpect(jsonPath("$.estado").value(false));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        Mockito.doNothing().when(clienteRepository).deleteById("1");

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isOk());
    }
}