package com.delivery.delivery.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "TBOPCOESPEDIDO")
public class OpcoesPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private PedidoEntity pedidoEntity;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private ProdutoEntity produto;

    private Integer nuQuantidade;
    private BigDecimal nuValor;

    // getters/setters
}

