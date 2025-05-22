package com.delivery.delivery.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class AuthClienteRegister {
    @NotBlank(message = "O nome é obrigatório")
    private String nmUsuario;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String dsEmail;

    @NotBlank(message = "A senha é obrigatória")
    private String dsSenha;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String nuCpf;

    private LocalDate dtNascimento;

    private String dsTelefone;

    private Double nuLatitude;

    private Double nuLongitude;
}

