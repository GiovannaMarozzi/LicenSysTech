package com.empresa.sankya.repository.produtos;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.EstoqueDTO;
import com.empresa.sankya.produtos.Estoque;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    void deleteByNomeProduto(String nomeProduto);

    @Transactional
    @Modifying
    @Query("UPDATE Estoque e SET e.quantidade = e.quantidade - :quantidadeSubtrair WHERE e.cliente.cnpj = :cnpj AND e.nomeProduto = :nomeProduto")
    void alterarQuantidade(Integer quantidadeSubtrair, String cnpj, String nomeProduto);

    @Query("SELECT CASE WHEN EXISTS (SELECT e FROM Estoque e WHERE e.nomeProduto = :nomeProduto and e.quantidade >= :quantidade) THEN TRUE ELSE FALSE END FROM Estoque e")
    boolean existsByProduto(String nomeProduto, Integer quantidade);

}
