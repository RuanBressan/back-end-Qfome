package com.delivery.delivery.controller;

import com.delivery.delivery.dto.auth.EstoqueAtualizacaoRequest;
import com.delivery.delivery.dto.auth.EstoqueResponse;
import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.entity.ProdutoEntity;
import com.delivery.delivery.repository.FornecedorRepository;
import com.delivery.delivery.repository.ProdutoRepository;
import com.delivery.delivery.service.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedor/estoque")
@RequiredArgsConstructor
public class EstoqueController {
    private final EstoqueService estoqueService;
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    @PostMapping("/adicionar")
    public ResponseEntity<EstoqueResponse> adicionarProdutos(@Valid @RequestBody EstoqueAtualizacaoRequest request,
                                                             Authentication authentication) {
        String email = authentication.getName();
        FornecedorEntity fornecedor = fornecedorRepository.findByDsEmail(email)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        ProdutoEntity produto = produtoRepository.findById(request.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if (!produto.getFornecedor().getId().equals(fornecedor.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(estoqueService.adicionarProdutos(request));
    }

    @PostMapping("/remover")
    public ResponseEntity<EstoqueResponse> removerProdutos(@Valid @RequestBody EstoqueAtualizacaoRequest request,
                                                           Authentication authentication) {
        String email = authentication.getName();
        FornecedorEntity fornecedor = fornecedorRepository.findByDsEmail(email)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        ProdutoEntity produto = produtoRepository.findById(request.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if (!produto.getFornecedor().getId().equals(fornecedor.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(estoqueService.removerProdutos(request));
    }
}
