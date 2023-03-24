package com.projeto.marketplace.service;

import com.projeto.marketplace.exceptions.BadRequestException;
import com.projeto.marketplace.exceptions.ClienteNotFoundException;
import com.projeto.marketplace.model.Cliente;
import com.projeto.marketplace.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            throw new ClienteNotFoundException("Cliente com id " + id + " não encontrado!");
        }

        return clienteOptional.get();
    }

    public void addCliente(Cliente cliente) {
        Boolean clienteExiste = clienteRepository
                .selectExistsEmail(cliente.getEmail());
        if(clienteExiste) {
            throw new BadRequestException(
                    "Email " + cliente.getEmail() + " já está em uso."
            );
        }
    }

    public void deleteCliente(Long clienteId) {
        if(!clienteRepository.existsById(clienteId)) {
            throw new ClienteNotFoundException(
                    "O Cliente com o id " + clienteId + " não existe."
            );
        }
        clienteRepository.deleteById(clienteId);
    }
}
