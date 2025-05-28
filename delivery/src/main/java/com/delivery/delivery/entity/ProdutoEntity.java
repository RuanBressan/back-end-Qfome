package com.delivery.delivery.entity;

import com.delivery.delivery.entity.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBPRODUTO")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nmProduto;

    @Column(length = 500)
    private String dsProduto;

    @Column(nullable = false)
    private Double vlPreco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorEntity fornecedor;

    @Column(nullable = false)
    private Boolean flAtivo = true;

    @Column(nullable = false)
    private LocalDateTime dtCriacao = LocalDateTime.now();

    @Column
    private LocalDateTime dtAtualizacao;

    @PreUpdate
    public void preUpdate() {
        this.dtAtualizacao = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
}
