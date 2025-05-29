package com.delivery.delivery.service;

import com.delivery.delivery.dto.ItemPedidoDto;
import com.delivery.delivery.dto.PedidoDto;
import com.delivery.delivery.entity.*;
import com.delivery.delivery.entity.enums.StatusPedido;
import com.delivery.delivery.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepo;
    @Autowired private ProdutoRepository produtoRepo;
    @Autowired private EstoqueRepository estoqueRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private FornecedorRepository fornecedorRepo;

    @Transactional
    public PedidoEntity criarPedido(PedidoDto dto, String dsEmailCliente) {
        ClienteEntity cliente = clienteRepo.findByDsEmail(dsEmailCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        FornecedorEntity restaurante = fornecedorRepo.findById(dto.getIdFornecedor())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(cliente);
        pedido.setFornecedor(restaurante);
        pedido.setDsStatusPedido(StatusPedido.REALIZADO);
        pedido.setDsTipoEntrega(dto.getDsTipoEntrega());
        pedido.setDsFormasPagamento(dto.getDsFormasPagamento());
        pedido.setData(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedidoDto item : dto.getNuItemPedidos()) {
            ProdutoEntity produto = produtoRepo.findById(item.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            EstoqueEntity estoque = estoqueRepo.findByProdutoId(item.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

            if (estoque.getNuQuantidade() < item.getNuQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNmProduto());
            }
            estoque.setNuQuantidade(estoque.getNuQuantidade() - item.getNuQuantidade());
            estoqueRepo.save(estoque);

            OpcoesPedido opcao = new OpcoesPedido();
            opcao.setProduto(produto);
            opcao.setNuQuantidade(item.getNuQuantidade());
            opcao.setNuValor(produto.getVlPreco().multiply(BigDecimal.valueOf(item.getNuQuantidade())));
            opcao.setPedidoEntity(pedido);

            pedido.getOpcoes().add(opcao);
            total = total.add(opcao.getNuValor());
        }
        pedido.setVlTotal(total);

        return pedidoRepo.save(pedido);
    }

    public List<PedidoEntity> listarPedidosPorRestaurante(Integer idRestaurante) {
        return pedidoRepo.findByFornecedorId(idRestaurante);
    }

    @Transactional
    public PedidoEntity atualizarStatus(Integer id, StatusPedido status) {
        PedidoEntity pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setDsStatusPedido(status);
        return pedidoRepo.save(pedido);
    }
}
