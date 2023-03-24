package com.projeto.marketplace.controller;

import com.projeto.marketplace.model.Cliente;
import com.projeto.marketplace.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping(path = "/{clienteId}")
    public Cliente getCliente(
            @PathVariable("clienteId") Long clienteId
    ) {
        return clienteService.getCliente(clienteId);
    }

    @PostMapping
    public void addCliente(@Valid @RequestBody Cliente cliente) {
        clienteService.addCliente(cliente);
    }

    @DeleteMapping(path = "{clienteId}")
    public void deleteCliente(
            @PathVariable("clienteId") Long clienteId
    ) {
        clienteService.deleteCliente(clienteId);
    }
}
