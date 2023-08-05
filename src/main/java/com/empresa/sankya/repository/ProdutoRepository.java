package com.empresa.sankya.repository;

import com.empresa.sankya.produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {
}
