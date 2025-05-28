package com.delivery.delivery.service;

import com.delivery.delivery.dto.auth.EstoqueAtualizacaoRequest;
import com.delivery.delivery.dto.auth.EstoqueResponse;
import com.delivery.delivery.entity.EstoqueEntity;
import com.delivery.delivery.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Transactional
    public EstoqueResponse adicionarProdutos(EstoqueAtualizacaoRequest request) {
        EstoqueEntity estoque = estoqueRepository.findByProdutoId(request.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto com id: " + request.getIdProduto()));

        Integer novaQuantidade = estoque.getNuQuantidade() + request.getNuQuantidadeAdicionar();
        estoque.setNuQuantidade(novaQuantidade);
        estoqueRepository.save(estoque);

        EstoqueResponse response = new EstoqueResponse();
        response.setIdProduto(request.getIdProduto());
        response.setNuQuantidade(novaQuantidade);
        return response;
    }

    @Transactional
    public EstoqueResponse removerProdutos(EstoqueAtualizacaoRequest request) {
        EstoqueEntity estoque = estoqueRepository.findByProdutoId(request.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto com id: " + request.getIdProduto()));

        if (request.getNuQuantidadeAdicionar() > estoque.getNuQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para remover a quantidade solicitada");
        }

        Integer novaQuantidade = estoque.getNuQuantidade() - request.getNuQuantidadeAdicionar();
        estoque.setNuQuantidade(novaQuantidade);
        estoqueRepository.save(estoque);

        EstoqueResponse response = new EstoqueResponse();
        response.setIdProduto(request.getIdProduto());
        response.setNuQuantidade(novaQuantidade);
        return response;
    }
}
