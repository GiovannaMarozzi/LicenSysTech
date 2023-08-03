package com.empresa.sankya.clientes;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Data
@Entity(name = "cliente")
@NoArgsConstructor
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
}
