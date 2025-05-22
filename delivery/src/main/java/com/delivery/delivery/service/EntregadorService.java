package com.delivery.delivery.service;

import com.delivery.delivery.entity.EntregadorEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final PasswordEncoder passwordEncoder;

    public EntregadorEntity criarEntregador(EntregadorEntity entregador) {
        if (entregadorRepository.existsByDsEmail(entregador.getDsEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já está em uso.");
        }

        if (entregadorRepository.existsByNuCpf(entregador.getNuCpf())) {
            throw new RuntimeException("CPF já está em uso.");
        }

        entregador.setDsSenha(passwordEncoder.encode(entregador.getDsSenha()));

        entregador.setFlTipoUsuario(entregador.getFlTipoUsuario() != null ? entregador.getFlTipoUsuario() : TipoUsuario.ENTREGADOR);

        return entregadorRepository.save(entregador);
    }


    public List<EntregadorEntity> listarTodos() {
        return entregadorRepository.findAll();
    }

    public Optional<EntregadorEntity> buscarPorId(Integer id) {
        return entregadorRepository.findById(id);
    }

    public EntregadorEntity atualizarEntregador(EntregadorEntity entregador) {
        if (entregador.getDsSenha() != null) {
            entregador.setDsSenha(passwordEncoder.encode(entregador.getDsSenha()));
        }
        return entregadorRepository.save(entregador);
    }
}
