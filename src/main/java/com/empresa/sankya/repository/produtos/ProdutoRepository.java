package com.empresa.sankya.repository.produtos;

import com.empresa.sankya.produtos.Produtos;
import com.empresa.sankya.produtos.TipoDePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produtos, Long> {

    @Query("SELECT COUNT(p) FROM produtos p WHERE p.tipoDePedido = :tipo AND p.cliente.id = :clienteId")
    Integer countByTipoDePedido(TipoDePedido tipo, Long clienteId);

    @Query("SELECT p FROM produtos p WHERE p.cliente.id = :clienteId")
    List<Produtos> findByCliente(Long clienteId);
}
