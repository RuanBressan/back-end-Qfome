package com.delivery.delivery.dto.auth;

import com.delivery.delivery.entity.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String dsEmail;

    @NotBlank(message = "A senha é obrigatória")
    private String dsSenha;

    @NotNull(message = "Tipo do usuário é obrigatório")
    private TipoUsuario flTipoUsuario;
}
