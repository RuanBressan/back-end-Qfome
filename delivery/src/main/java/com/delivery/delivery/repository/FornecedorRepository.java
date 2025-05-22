package com.delivery.delivery.repository;

import com.delivery.delivery.entity.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Integer> {
    Optional<FornecedorEntity> findByDsEmail(String dsEmail);
    boolean existsByNuCnpj(String nuCnpj);
    boolean existsByDsEmail(String dsEmail);

}
