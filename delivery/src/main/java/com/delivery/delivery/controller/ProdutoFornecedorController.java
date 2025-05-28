package com.delivery.delivery.controller;

import com.delivery.delivery.dto.auth.ProdutoCadastroRequest;
import com.delivery.delivery.dto.auth.ProdutoResponse;
import com.delivery.delivery.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fornecedor/produtos")
@RequiredArgsConstructor
public class ProdutoFornecedorController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(
            @Valid @RequestBody ProdutoCadastroRequest request,
            Authentication authentication) {

        String emailFornecedor = authentication.getName();
        ProdutoResponse response = produtoService.cadastrarProduto(request, emailFornecedor);
        URI location = URI.create("/fornecedor/produtos/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar(Authentication authentication) {
        return ResponseEntity.ok(produtoService.listarProdutos(authentication));
    }
}
