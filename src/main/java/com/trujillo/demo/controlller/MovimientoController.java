package com.trujillo.demo.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trujillo.demo.model.Movimiento;
import com.trujillo.demo.repository.MovimientoRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @GetMapping("/{Id}")
    public Movimiento getMovimientoById(@PathVariable Long Id) {
        return movimientoRepository.findById(Id).orElse(null);
    }

    @PostMapping
    public Movimiento addMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @PutMapping("/{Id}")
    public Movimiento updateMovimiento(@PathVariable Long Id, @RequestBody Movimiento movimientoDetails) {
        return movimientoRepository.findById(Id).map(movimiento -> {
            movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
            movimiento.setFecha(new Date());
            movimiento.setValor(movimientoDetails.getValor());
            movimiento.setSaldo(movimientoDetails.getSaldo());
            return movimientoRepository.save(movimiento);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long id) {
        movimientoRepository.deleteById(id);
    }
}