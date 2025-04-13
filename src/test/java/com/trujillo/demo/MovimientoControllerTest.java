package com.trujillo.demo;

import com.trujillo.demo.controlller.MovimientoController;
import com.trujillo.demo.model.Movimiento;
import com.trujillo.demo.repository.MovimientoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovimientoController.class)
public class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimientoRepository movimientoRepository;

    Movimiento movimiento1 = new Movimiento(1L,"1234", new Date(), "Deposito", 1000.0, 5000.0);
    Movimiento movimiento2 = new Movimiento(2L,"1234", new Date(), "Retiro", 500.0, 4500.0);

    @Test
    public void testGetAllMovimientos() throws Exception {

        Mockito.when(movimientoRepository.findAll()).thenReturn(Arrays.asList(movimiento1, movimiento2));

        mockMvc.perform(get("/movimientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipoMovimiento").value("Deposito"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].tipoMovimiento").value("Retiro"));
    }

    @Test
    public void testGetMovimientoById() throws Exception {

        Mockito.when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimiento1));

        mockMvc.perform(get("/movimientos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoMovimiento").value("Deposito"));
    }

    @Test
    public void testAddMovimiento() throws Exception {

        Mockito.when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimiento1);

        mockMvc.perform(post("/movimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tipoMovimiento\":\"Deposito\",\"valor\":1000.0,\"saldo\":5000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoMovimiento").value("Deposito"));
    }

    @Test
    public void testUpdateMovimiento() throws Exception {
        Movimiento movimientoUpdated = new Movimiento(1L,"1234", new Date(), "Retiro", 500.0, 4500.0);

        Mockito.when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimiento1));
        Mockito.when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimientoUpdated);

        mockMvc.perform(put("/movimientos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tipoMovimiento\":\"Retiro\",\"valor\":500.0,\"saldo\":4500.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoMovimiento").value("Retiro"));
    }

    @Test
    public void testDeleteMovimiento() throws Exception {
        Mockito.doNothing().when(movimientoRepository).deleteById(1L);

        mockMvc.perform(delete("/movimientos/1"))
                .andExpect(status().isOk());
    }
}