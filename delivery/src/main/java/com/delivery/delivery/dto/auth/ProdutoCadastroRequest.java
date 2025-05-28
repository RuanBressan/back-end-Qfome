package com.delivery.delivery.dto.auth;

import com.delivery.delivery.entity.enums.Categoria;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProdutoCadastroRequest {

    @NotBlank(message = "Nome obrigatório")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nmProduto;

    @Size(max = 500, message = "Máximo 500 caracteres")
    private String dsProduto;

    @NotNull(message = "Preço obrigatório")
    @DecimalMin(value = "0.01", message = "Preço inválido")
    private Double vlPreco;

    private Categoria categoria;
}
