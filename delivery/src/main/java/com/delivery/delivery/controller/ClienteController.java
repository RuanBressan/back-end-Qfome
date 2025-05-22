package com.delivery.delivery.controller;

import com.delivery.delivery.entity.ClienteEntity;
import com.delivery.delivery.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteEntity> criarCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> buscarClientePorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> atualizarCliente(@PathVariable Integer id, @RequestBody ClienteEntity cliente) {
        return clienteService.buscarPorId(id)
                .map(clienteExistente -> {
                    cliente.setId(id);
                    ClienteEntity atualizado = clienteService.atualizarCliente(cliente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
