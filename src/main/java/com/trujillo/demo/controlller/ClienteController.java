package com.trujillo.demo.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{clienteId}")
    public Cliente getClienteById(@PathVariable String clienteId) {
        return clienteRepository.findById(clienteId).orElse(null);
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public Cliente updateCliente(@PathVariable String clienteId, @RequestBody Cliente cliente) {
        if (clienteRepository.existsById(clienteId)) {
            cliente.setClienteId(clienteId);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    @DeleteMapping("/{clienteId}")
    public void deleteCliente(@PathVariable String clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
