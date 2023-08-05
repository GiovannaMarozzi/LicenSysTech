package com.empresa.sankya.clientes;


import com.empresa.sankya.produtos.Produtos;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@Entity(name = "cliente")
@Data
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;

    @Column(name = "cep")
    private String cep;

    @Column(name = "data_de_cadastro")
    private Date data_de_cadastro;

    @Column(name = "alterado")
    private Boolean alterado;

    @Column(name = "data_alteracao_cadastral")
    private Date data_alteracao_cadastral;

    @OneToMany(mappedBy = "cliente")
    private List<Produtos> produtos;

    public Cliente(){
        this.data_de_cadastro = new Date();
        this.alterado = false;
        this.data_alteracao_cadastral = null;
    }
}