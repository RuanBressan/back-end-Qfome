package com.delivery.delivery.dto;

import com.delivery.delivery.entity.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregadorDto {

    private Integer id;
    private String nmUsuario;
    private String dsEmail;
    private TipoUsuario flTipoUsuario;
    private String nuCpf;
    private LocalDate dtNascimento;
    private String dsTelefone;
    private Double nuLatitude;
    private Double nuLongitude;
    private String dsNumeroCnh;
    private String dsPlacaVeiculo;
}
