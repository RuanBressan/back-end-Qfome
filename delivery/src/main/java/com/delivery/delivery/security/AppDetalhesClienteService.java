package com.delivery.delivery.security;

import com.delivery.delivery.entity.ClienteEntity;
import com.delivery.delivery.repository.ClienteRepository;
import com.delivery.delivery.repository.EntregadorRepository;
import com.delivery.delivery.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppDetalhesClienteService implements UserDetailsService {
//aa
    private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String dsEmail) throws UsernameNotFoundException {

        ClienteEntity cliente = clienteRepository.findByDsEmail(dsEmail).orElse(null);
        if (cliente != null) {
            return new AppDetalhesUsuario(
                    cliente.getId(),
                    cliente.getDsEmail(),
                    cliente.getDsSenha(),
                    cliente.getFlTipoUsuario()
            );
        }

        throw new UsernameNotFoundException("Cliente com e-mail " + dsEmail + " não encontrado.");
    }
}

