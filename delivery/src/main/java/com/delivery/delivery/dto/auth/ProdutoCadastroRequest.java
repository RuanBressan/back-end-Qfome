package com.delivery.delivery.dto.auth;

import com.delivery.delivery.entity.enums.Categoria;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoCadastroRequest {

    private String dsAvatar;

    @NotBlank(message = "Nome obrigatório")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nmProduto;

    @Size(max = 500, message = "Máximo 500 caracteres")
    private String dsProduto;

    @NotNull(message = "Preço obrigatório")
    @DecimalMin(value = "0.01", message = "Preço inválido")
    private BigDecimal vlPreco;

    private Categoria categoria;
}
