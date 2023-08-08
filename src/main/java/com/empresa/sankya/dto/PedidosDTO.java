package com.empresa.sankya.dto;

import com.empresa.sankya.clientes.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosDTO {
    private String cnpj_cliente;
    private String nome_cliente;
    private int quantidade_de_encomendas;
    private int quantidade_de_compras;
}
