package com.delivery.delivery.repository;

import com.delivery.delivery.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>  {
    Optional<ClienteEntity> findByDsEmail(String dsEmail);
    boolean existsByDsEmail(String dsEmail);
    boolean existsByNuCpf(String nuCpf);
}
