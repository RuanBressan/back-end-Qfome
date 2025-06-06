package com.delivery.delivery.dto;

import com.delivery.delivery.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorDto {

    private Integer id;
    private String nmUsuario;
    private String dsEmail;
    private TipoUsuario flTipoUsuario;
    private String nuCnpj;
    private String dsAvatar;
    private LocalDate dtNascimento;
    private String dsTelefone;
    private Double nuLatitude;
    private Double nuLongitude;
}
