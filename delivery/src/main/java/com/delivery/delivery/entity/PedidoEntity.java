package com.delivery.delivery.entity;

import jakarta.persistence.*;
import com.delivery.delivery.entity.enums.StatusPedido;
import com.delivery.delivery.entity.enums.FormasPagamento;
import com.delivery.delivery.entity.enums.TipoEntrega;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido dsStatusPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEntrega dsTipoEntrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormasPagamento dsFormasPagamento;

    @Column(name = "vlTotal")
    private BigDecimal vlTotal;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "idFornecedor")
    private FornecedorEntity fornecedor;

    @OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpcoesPedido> opcoes = new ArrayList<>();

    @Column(name = "data")
    private LocalDateTime data;

    // getters/setters
}
