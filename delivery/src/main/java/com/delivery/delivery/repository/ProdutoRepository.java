package com.delivery.delivery.repository;

import com.delivery.delivery.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByFornecedorId(Integer fornecedorId);

    @Query("SELECT p FROM ProdutoEntity p WHERE p.fornecedor.id = :fornecedorId AND p.flAtivo = true")
    List<ProdutoEntity> findAtivosByFornecedor(@Param("fornecedorId") Integer fornecedorId);

    Optional<ProdutoEntity> findByIdAndFornecedorId(Integer id, Integer fornecedorId);

    boolean existsByNmProdutoAndFornecedorId(String nmProduto, Integer fornecedorId);


}
