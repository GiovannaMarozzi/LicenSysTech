package com.empresa.sankya.produtos;

import com.empresa.sankya.clientes.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Estoque")
public class Estoque {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnpj_empresa", referencedColumnName = "cnpj")
    private Cliente cliente;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "tipo_de_produto")
    @Enumerated(EnumType.STRING)
    private Tipo tipo_de_produto;

    @Column(name = "quantidade")
    private Integer quantidade;
}