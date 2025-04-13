package com.trujillo.demo;

import com.trujillo.demo.controlller.CuentaController;
import com.trujillo.demo.model.Cliente;
import com.trujillo.demo.model.Cuenta;
import com.trujillo.demo.repository.ClienteRepository;
import com.trujillo.demo.repository.CuentaRepository;
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

@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaRepository cuentaRepository;
    @MockBean
    private ClienteRepository clienteRepository;

    Cuenta cuenta1 = new Cuenta("123", "C001", "Ahorros", 1000.0, true);
    Cuenta cuenta2 = new Cuenta("456","C002" ,"Corriente", 2000.0, true);
    Cliente cliente1 = new Cliente("C001", "password123", true);

    @Test
    public void testGetAllCuentas() throws Exception {

        Mockito.when(cuentaRepository.findAll()).thenReturn(Arrays.asList(cuenta1, cuenta2));

        mockMvc.perform(get("/cuentas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCuenta").value("123"))
                .andExpect(jsonPath("$[0].tipoCuenta").value("Ahorros"))
                .andExpect(jsonPath("$[1].numeroCuenta").value("456"))
                .andExpect(jsonPath("$[1].tipoCuenta").value("Corriente"));
    }

    @Test
    public void testGetCuentaByNumeroCuenta() throws Exception {

        Mockito.when(cuentaRepository.findById("123")).thenReturn(Optional.of(cuenta1));

        mockMvc.perform(get("/cuentas/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("123"))
                .andExpect(jsonPath("$.tipoCuenta").value("Ahorros"));
    }

    @Test
    public void testCreateCuenta() throws Exception {
        Mockito.when(clienteRepository.findByClienteId("C001")).thenReturn(Optional.of(cliente1)); 
        Mockito.when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta1);

        mockMvc.perform(post("/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numeroCuenta\":\"123\",\"tipoCuenta\":\"Ahorros\",\"saldoInicial\":1000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("123"))
                .andExpect(jsonPath("$.tipoCuenta").value("Ahorros"));
    }

    @Test
    public void testUpdateCuenta() throws Exception {

        Mockito.when(cuentaRepository.existsById("123")).thenReturn(true);
        Cuenta cuentaUpdated = new Cuenta("123","C001", "Corriente", 1000.0, true);
        Mockito.when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuentaUpdated);

        mockMvc.perform(put("/cuentas/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numeroCuenta\":\"123\",\"tipoCuenta\":\"Corriente\",\"saldoInicial\":1000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("123"))
                .andExpect(jsonPath("$.tipoCuenta").value("Corriente"));
    }

    @Test
    public void testDeleteCuenta() throws Exception {
        Mockito.doNothing().when(cuentaRepository).deleteById("123");

        mockMvc.perform(delete("/cuentas/123"))
                .andExpect(status().isOk());
    }
}