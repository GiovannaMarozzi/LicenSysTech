package com.empresa.sankya.clientes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@Entity(name = "cliente")
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull
    private String nome;

    @Column(name = "cnpj")
    @NotNull
    private String cnpj;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "cep")
    @NotNull
    private String cep;

    @Column(name = "data_de_cadastro")
    private Date data_de_cadastro;

    @Column(name = "alterado")
    private Boolean alterado;

    @Column(name = "data_alteracao_cadastral")
    private Date data_alteracao_cadastral;

    public Cliente(){
        this.data_de_cadastro = new Date();
        this.data_alteracao_cadastral = null;
    }
}
