package com.delivery.delivery.repository;

import com.delivery.delivery.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
    List<PedidoEntity> findByFornecedorId(Integer idFornecedor);
}
