package com.delivery.delivery.dto.auth;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdutoResponse {
    private Integer id;
    private String nmProduto;
    private String dsProduto;
    private BigDecimal vlPreco;
    private String dsAvatar;
    private Boolean flAtivo;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtAtualizacao;
    private Integer fornecedorId;
    private String categoria;
}
