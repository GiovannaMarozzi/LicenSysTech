package com.empresa.sankya.repository.produtos;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.EstoqueDTO;
import com.empresa.sankya.produtos.Estoque;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    void deleteByNomeProduto(String nomeProduto);

    @Modifying
    @Query("UPDATE Estoque e SET e.quantidade = e.quantidade - :quantidadeSubtrair WHERE e.cliente = :cliente AND e.nomeProduto =:nomeProduto")
    void deletarPorQuantidade(Integer quantidadeSubtrair, Cliente cliente, String nomeProduto);
}
