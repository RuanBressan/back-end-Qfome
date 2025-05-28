package com.delivery.delivery.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoAtualizacaoRequest {

    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nmProduto;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String dsProduto;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private Double vlPreco;

}
