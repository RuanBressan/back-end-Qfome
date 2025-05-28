package com.delivery.delivery.service;

import com.delivery.delivery.dto.auth.ProdutoCadastroRequest;
import com.delivery.delivery.dto.auth.ProdutoResponse;
import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.entity.ProdutoEntity;
import com.delivery.delivery.repository.FornecedorRepository;
import com.delivery.delivery.repository.ProdutoRepository;
import com.delivery.delivery.repository.EstoqueRepository;
import com.delivery.delivery.entity.EstoqueEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final EstoqueRepository estoqueRepository;

    @Transactional
    public ProdutoResponse cadastrarProduto(ProdutoCadastroRequest request, String emailFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findByDsEmail(emailFornecedor)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        if (produtoRepository.existsByNmProdutoAndFornecedorId(request.getNmProduto(), fornecedor.getId()))
            throw new RuntimeException("Já existe um produto com este nome");

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNmProduto(request.getNmProduto());
        produto.setDsProduto(request.getDsProduto());
        produto.setVlPreco(request.getVlPreco());
        produto.setFlAtivo(true);
        produto.setFornecedor(fornecedor);
        produto.setCategoria(request.getCategoria());

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);

        EstoqueEntity estoque = new EstoqueEntity();
        estoque.setProduto(produtoSalvo);
        estoque.setNuQuantidade(0);
        estoqueRepository.save(estoque);

        return convertToResponse(produtoSalvo);
    }

    public List<ProdutoResponse> listarProdutos(Authentication authentication) {
        FornecedorEntity fornecedor = fornecedorRepository.findByDsEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        return produtoRepository.findAtivosByFornecedor(fornecedor.getId())
                .stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    private ProdutoResponse convertToResponse(ProdutoEntity entity) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(entity.getId());
        response.setNmProduto(entity.getNmProduto());
        response.setDsProduto(entity.getDsProduto());
        response.setVlPreco(entity.getVlPreco());
        response.setFlAtivo(entity.getFlAtivo());
        response.setDtCriacao(entity.getDtCriacao());
        response.setDtAtualizacao(entity.getDtAtualizacao());
        response.setFornecedorId(entity.getFornecedor().getId());
        response.setCategoria(entity.getCategoria().name());
        return response;
    }
}
