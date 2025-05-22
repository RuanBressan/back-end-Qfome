package com.delivery.delivery.service;

import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final PasswordEncoder passwordEncoder;

    public FornecedorEntity criarFornecedor(FornecedorEntity fornecedor) {
        if (fornecedorRepository.existsByDsEmail(fornecedor.getDsEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já está em uso.");
        }

        if (fornecedorRepository.existsByNuCnpj(fornecedor.getNuCnpj())) {
            throw new RuntimeException("CNPJ já está em uso.");
        }

        fornecedor.setDsSenha(passwordEncoder.encode(fornecedor.getDsSenha()));

        fornecedor.setFlTipoUsuario(fornecedor.getFlTipoUsuario() != null ? fornecedor.getFlTipoUsuario() : TipoUsuario.FORNECEDOR);

        return fornecedorRepository.save(fornecedor);
    }


    public List<FornecedorEntity> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<FornecedorEntity> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public FornecedorEntity atualizarFornecedor(FornecedorEntity fornecedor) {
        if (fornecedor.getDsSenha() != null) {
            fornecedor.setDsSenha(passwordEncoder.encode(fornecedor.getDsSenha()));
        }
        return fornecedorRepository.save(fornecedor);
    }
}
