package com.delivery.delivery.security;

import com.delivery.delivery.entity.EntregadorEntity;
import com.delivery.delivery.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppDetalhesEntregadorService implements UserDetailsService {

    private final EntregadorRepository entregadorRepository;
//aa
    @Override
    public UserDetails loadUserByUsername(String dsEmail) throws UsernameNotFoundException {

        EntregadorEntity entregador = entregadorRepository.findByDsEmail(dsEmail).orElse(null);
        if (entregador != null) {
            return new AppDetalhesUsuario(
                    entregador.getId(),
                    entregador.getDsEmail(),
                    entregador.getDsSenha(),
                    entregador.getFlTipoUsuario()
            );
        }

        throw new UsernameNotFoundException("Entregador com e-mail " + dsEmail + " não encontrado.");
    }
}
