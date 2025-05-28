package com.delivery.delivery.security;

import com.delivery.delivery.entity.ClienteEntity;
import com.delivery.delivery.entity.EntregadorEntity;
import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.entity.enums.TipoUsuario;
import com.delivery.delivery.repository.ClienteRepository;
import com.delivery.delivery.repository.EntregadorRepository;
import com.delivery.delivery.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppDetalhesUsuarioUnificadoService implements UserDetailsService {

    private final ClienteRepository clienteRepository;
    private final EntregadorRepository entregadorRepository;
    private final FornecedorRepository fornecedorRepository;

    @Override
    public UserDetails loadUserByUsername(String dsEmail) throws UsernameNotFoundException {
        // método antigo continua funcionando (sem verificação de tipo)
        return buscarUsuario(dsEmail);
    }

    public UserDetails loadUserByTipo(String dsEmail, TipoUsuario tipoEsperado) throws UsernameNotFoundException {
        AppDetalhesUsuario usuario = (AppDetalhesUsuario) buscarUsuario(dsEmail);

        if (!usuario.getFlTipoUsuario().equals(tipoEsperado)) {
            throw new UsernameNotFoundException("Usuário não é do tipo esperado: " + tipoEsperado.name());
        }

        return usuario;
    }

    private UserDetails buscarUsuario(String dsEmail) {
        ClienteEntity cliente = clienteRepository.findByDsEmail(dsEmail).orElse(null);
        if (cliente != null) {
            return new AppDetalhesUsuario(
                    cliente.getId(),
                    cliente.getDsEmail(),
                    cliente.getDsSenha(),
                    cliente.getFlTipoUsuario()
            );
        }

        EntregadorEntity entregador = entregadorRepository.findByDsEmail(dsEmail).orElse(null);
        if (entregador != null) {
            return new AppDetalhesUsuario(
                    entregador.getId(),
                    entregador.getDsEmail(),
                    entregador.getDsSenha(),
                    entregador.getFlTipoUsuario()
            );
        }

        FornecedorEntity fornecedor = fornecedorRepository.findByDsEmail(dsEmail).orElse(null);
        if (fornecedor != null) {
            return new AppDetalhesUsuario(
                    fornecedor.getId(),
                    fornecedor.getDsEmail(),
                    fornecedor.getDsSenha(),
                    fornecedor.getFlTipoUsuario()
            );
        }

        throw new UsernameNotFoundException("Usuário com e-mail " + dsEmail + " não encontrado.");
    }
}
