package com.delivery.delivery.dto;

import com.delivery.delivery.entity.enums.FormasPagamento;
import com.delivery.delivery.entity.enums.StatusPedido;
import com.delivery.delivery.entity.enums.TipoEntrega;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoDto {
    private Integer idFornecedor;
    private List<ItemPedidoDto> nuItemPedidos = new ArrayList<>();
    private StatusPedido dsStatusPedido;
    private TipoEntrega dsTipoEntrega;
    private FormasPagamento dsFormasPagamento;

}
