package com.delivery.delivery.repository;

import com.delivery.delivery.entity.EntregadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregadorRepository extends JpaRepository<EntregadorEntity, Integer> {
    Optional<EntregadorEntity> findByDsEmail(String dsEmail);
    boolean existsByDsEmail(String dsEmail);
    boolean existsByNuCpf(String nuCpf);
}
