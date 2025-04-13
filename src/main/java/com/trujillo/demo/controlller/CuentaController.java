package com.trujillo.demo.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.trujillo.demo.model.Cliente;
import com.trujillo.demo.model.Cuenta;
import com.trujillo.demo.repository.ClienteRepository;
import com.trujillo.demo.repository.CuentaRepository;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @GetMapping("/{numeroCuenta}")
    public Cuenta getCuentaByNumeroCuenta(@PathVariable String numeroCuenta){
        return cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) throws Exception {
        if (cuenta.getClienteId() != null) {
            Cliente cliente = clienteRepository.findByClienteId(cuenta.getClienteId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            cuenta.setClienteId(cliente.getClienteId());
        } 
        return cuentaRepository.save(cuenta);
    }

    @PutMapping("/{numeroCuenta}")
    public Cuenta updateCuenta(@PathVariable String numeroCuenta, @RequestBody Cuenta cuenta) {
        if (cuentaRepository.existsById(numeroCuenta)) {
            cuenta.setNumeroCuenta(numeroCuenta);
            return cuentaRepository.save(cuenta);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{numeroCuenta}")
    public void deleteCuenta(@PathVariable String numeroCuenta) {
        cuentaRepository.deleteById(numeroCuenta);
    }
}