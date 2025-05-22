package com.delivery.delivery.controller;

import com.delivery.delivery.entity.EntregadorEntity;
import com.delivery.delivery.service.EntregadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
@RequiredArgsConstructor
public class EntregadorController {

    private final EntregadorService entregadorService;

    @PostMapping
    public ResponseEntity<EntregadorEntity> criarEntregador(@RequestBody EntregadorEntity entregador) {
        EntregadorEntity novoEntregador = entregadorService.criarEntregador(entregador);
        return ResponseEntity.ok(novoEntregador);
    }

    @GetMapping
    public ResponseEntity<List<EntregadorEntity>> listarEntregadors() {
        return ResponseEntity.ok(entregadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregadorEntity> buscarEntregadorPorId(@PathVariable Integer id) {
        return entregadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregadorEntity> atualizarEntregador(@PathVariable Integer id, @RequestBody EntregadorEntity entregador) {
        return entregadorService.buscarPorId(id)
                .map(entregadorExistente -> {
                    entregador.setId(id);
                    EntregadorEntity atualizado = entregadorService.atualizarEntregador(entregador);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
