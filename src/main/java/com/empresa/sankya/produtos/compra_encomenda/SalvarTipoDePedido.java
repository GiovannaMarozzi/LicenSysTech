package com.empresa.sankya.produtos.compra_encomenda;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.produtos.Produtos;
import com.empresa.sankya.produtos.TipoDePedido;
import org.springframework.stereotype.Component;

@Component
public abstract class SalvarTipoDePedido {

    protected SalvarTipoDePedido proximo;

    public SalvarTipoDePedido(SalvarTipoDePedido proximo){
        this.proximo = proximo;
    }

    public void salvar(Produtos produtos, Cliente cliente, TipoDePedido tipoDePedido){
        if(tipoDePedido(tipoDePedido)){
            salvarTipo(produtos, cliente);
        }

        if (proximo != null) {
            proximo.salvar(produtos, cliente, tipoDePedido);
        }
    }

    protected abstract void salvarTipo(Produtos produtos, Cliente cliente);

    public abstract boolean tipoDePedido(TipoDePedido tipoDePedido);

}
