package com.empresa.sankya.dto;


import com.empresa.sankya.produtos.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
    private ClientesDTO cliente;
    private String nomeProduto;
    private Tipo tipo_de_produto;
    private Integer quantidade;

}
