package com.delivery.delivery.controller;

import com.delivery.delivery.dto.PedidoDto;
import com.delivery.delivery.dto.StatusPedidoDto;
import com.delivery.delivery.entity.PedidoEntity;
import com.delivery.delivery.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PedidoEntity> criarPedido(@RequestBody PedidoDto dto, Authentication auth) {
        PedidoEntity pedido = pedidoService.criarPedido(dto, auth.getName());
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/fornecedor/{idFornecedor}")
    @PreAuthorize("hasRole('FORNECEDOR')")
    public List<PedidoEntity> listarPedidos(@PathVariable Integer idFornecedor) {
        return pedidoService.listarPedidosPorRestaurante(idFornecedor);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('FORNECEDOR')")
    public PedidoEntity alterarStatus(@PathVariable Integer id, @RequestBody StatusPedidoDto dto) {
        return pedidoService.atualizarStatus(id, dto.getDsStatusPedido());
    }
}

