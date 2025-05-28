package com.delivery.delivery.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstoqueAtualizacaoRequest {
    @NotNull(message = "O ID do produto é obrigatório")
    private Integer IdProduto;

    @NotNull(message = "A quantidade a adicionar é obrigatória")
    private Integer nuQuantidadeAdicionar;
}
