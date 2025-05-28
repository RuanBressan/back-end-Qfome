package com.delivery.delivery.dto.auth;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProdutoResponse {
    private Integer id;
    private String nmProduto;
    private String dsProduto;
    private Double vlPreco;
    private Boolean flAtivo;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtAtualizacao;
    private Integer fornecedorId;
    private String categoria;
}
