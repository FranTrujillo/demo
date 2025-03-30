package com.trujillo.demo.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trujillo.demo.model.Cuenta;
import com.trujillo.demo.repository.CuentaRepository;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @GetMapping("/{numeroCuenta}")
    public Cuenta getCuentaByNumeroCuenta(@PathVariable String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta).orElse(null);
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @PutMapping("/{numeroCuenta}")
    public Cuenta updateCuenta(@PathVariable String numeroCuenta, @RequestBody Cuenta cuenta) {
        if (cuentaRepository.existsById(numeroCuenta)) {
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setTipoCuenta(cuenta.getTipoCuenta());
            cuenta.setSaldoInicial(cuenta.getSaldoInicial());
            cuenta.setEstado(cuenta.isEstado());
            return cuentaRepository.save(cuenta);
        }
        return null;
    }

    @DeleteMapping("/{numeroCuenta}")
    public void deleteCuenta(@PathVariable String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
    }
}