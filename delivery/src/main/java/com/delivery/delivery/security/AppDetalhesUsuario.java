package com.delivery.delivery.security;

import com.delivery.delivery.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
public class AppDetalhesUsuario implements UserDetails {

    private final Integer id;
    private final String dsEmail;
    private final String dsSenha;
    private final TipoUsuario flTipoUsuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + flTipoUsuario.name());
    }

    @Override
    public String getPassword() {
        return dsSenha;
    }

    @Override
    public String getUsername() {
        return dsEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

