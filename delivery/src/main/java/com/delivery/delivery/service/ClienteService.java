package com.delivery.delivery.service;

import com.delivery.delivery.entity.ClienteEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public ClienteEntity criarCliente(ClienteEntity cliente) {
        if (clienteRepository.existsByDsEmail(cliente.getDsEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já está em uso.");
        }

        if (clienteRepository.existsByNuCpf(cliente.getNuCpf())) {
            throw new RuntimeException("CPF já está em uso.");
        }

        cliente.setDsSenha(passwordEncoder.encode(cliente.getDsSenha()));

        cliente.setFlTipoUsuario(cliente.getFlTipoUsuario() != null ? cliente.getFlTipoUsuario() : TipoUsuario.CLIENTE);

        return clienteRepository.save(cliente);
    }


    public List<ClienteEntity> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }


    public ClienteEntity atualizarCliente(ClienteEntity cliente) {
        if (cliente.getDsSenha() != null) {
            cliente.setDsSenha(passwordEncoder.encode(cliente.getDsSenha()));
        }
        return clienteRepository.save(cliente);
    }
}
