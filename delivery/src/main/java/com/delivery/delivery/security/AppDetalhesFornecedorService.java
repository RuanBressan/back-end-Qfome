package com.delivery.delivery.security;

import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppDetalhesFornecedorService implements UserDetailsService {
//aa
    private final FornecedorRepository fornecedorRepository;

    @Override
    public UserDetails loadUserByUsername(String dsEmail) throws UsernameNotFoundException {

        FornecedorEntity fornecedor = fornecedorRepository.findByDsEmail(dsEmail).orElse(null);
        if (fornecedor != null) {
            return new AppDetalhesUsuario(
                    fornecedor.getId(),
                    fornecedor.getDsEmail(),
                    fornecedor.getDsSenha(),
                    fornecedor.getFlTipoUsuario()
            );
        }

        throw new UsernameNotFoundException("Fornecedor com e-mail " + dsEmail + " não encontrado.");
    }
}
