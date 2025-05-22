package com.delivery.delivery.controller;

import com.delivery.delivery.entity.FornecedorEntity;
import com.delivery.delivery.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {
    
    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorEntity> criarFornecedor(@RequestBody FornecedorEntity fornecedor) {
        FornecedorEntity novoFornecedor = fornecedorService.criarFornecedor(fornecedor);
        return ResponseEntity.ok(novoFornecedor);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorEntity>> listarFornecedors() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorEntity> buscarFornecedorPorId(@PathVariable Integer id) {
        return fornecedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorEntity> atualizarFornecedor(@PathVariable Integer id, @RequestBody FornecedorEntity fornecedor) {
        return fornecedorService.buscarPorId(id)
                .map(fornecedorExistente -> {
                    fornecedor.setId(id);
                    FornecedorEntity atualizado = fornecedorService.atualizarFornecedor(fornecedor);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
