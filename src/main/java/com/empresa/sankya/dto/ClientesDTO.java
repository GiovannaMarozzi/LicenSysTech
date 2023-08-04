package com.empresa.sankya.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;


@Data
@AllArgsConstructor
public class ClientesDTO {
    private String nome;
    private String cnpj;
    private String email;
    private String cep;
    private Date data_de_cadastro;
    private boolean alterado;
    private Date data_alteracao_cadastral;

    public ClientesDTO(){
        this.alterado = false;
        this.data_alteracao_cadastral = null;
    }
}
