package com.empresa.sankya.produtos;


import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.produtos.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(name = "nome_produto")
    private String nome_produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "peso")
    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_produto")
    private Tipo tipoDeProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_pedido")
    private TipoDePedido tipoDePedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "periculosidade")
    private Periculosidade periculosidade;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "transportadora")
    private String transportadora;

    @Column(name = "observacoes")
    private String observacoes;
}
