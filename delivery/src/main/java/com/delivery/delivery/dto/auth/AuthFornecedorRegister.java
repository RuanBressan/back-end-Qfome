package com.delivery.delivery.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@Data
public class AuthFornecedorRegister {

    @NotBlank(message = "O nome é obrigatório")
    private String nmUsuario;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String dsEmail;

    @NotBlank(message = "A senha é obrigatória")
    private String dsSenha;

    @NotBlank(message = "O CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido")
    private String nuCnpj;

    private LocalDate dtNascimento;

    private String dsTelefone;

    private Double nuLatitude;

    private Double nuLongitude;

}
