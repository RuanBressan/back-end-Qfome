package com.delivery.delivery.entity;

import com.delivery.delivery.entity.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TBENTREGADOR")
@Entity
public class EntregadorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nmUsuario;

    @Column(nullable = false, unique = true)
    private String dsEmail;

    @Column(nullable = false)
    private String dsSenha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario flTipoUsuario;

    @Column(nullable = false)
    private String nuCpf;

    private LocalDate dtNascimento;

    private String dsTelefone;

    private String dsNumeroCnh;

    private String dsPlacaVeiculo;

    private Double nuLatitude;

    private Double nuLongitude;

}

