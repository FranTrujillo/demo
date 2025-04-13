package com.trujillo.demo.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.trujillo.demo.model.Cliente;
import com.trujillo.demo.repository.ClienteRepository;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{identificacion}")
    public Cliente getClienteById(@PathVariable String identificacion) {
        return clienteRepository.findById(identificacion).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{identificacion}")
    public Cliente updateCliente(@PathVariable String identificacion, @RequestBody Cliente cliente) {
        if (clienteRepository.existsById(identificacion)) {
            cliente.setClienteId(identificacion);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    @DeleteMapping("/{identificacion}")
    public void deleteCliente(@PathVariable String identificacion) {
        clienteRepository.deleteById(identificacion);
    }
}
