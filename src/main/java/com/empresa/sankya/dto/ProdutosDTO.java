package com.empresa.sankya.dto;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.produtos.Periculosidade;
import com.empresa.sankya.produtos.Tipo;
import com.empresa.sankya.produtos.TipoDePedido;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProdutosDTO {
    private ClientesDTO cliente;
    private String nome_produto;
    private Integer quantidade;
    private Double peso;
    private Tipo tipoDeProduto;
    private TipoDePedido tipoDePedido;
    private Periculosidade periculosidade;
    private Double preco;
    private String transportadora;
    private String observacoes;
}
