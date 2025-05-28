package com.delivery.delivery.repository;

import com.delivery.delivery.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Integer> {
    Optional<EstoqueEntity> findByProdutoId(Integer produtoId);
}
