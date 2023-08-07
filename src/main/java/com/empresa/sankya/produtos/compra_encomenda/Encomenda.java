package com.empresa.sankya.produtos.compra_encomenda;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.produtos.Produtos;
import com.empresa.sankya.produtos.TipoDePedido;
import com.empresa.sankya.repository.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Encomenda extends SalvarTipoDePedido {

    private ProdutoRepository repository;

    public Encomenda(ProdutoRepository repository, SalvarTipoDePedido proximo) {
        super(proximo);
        this.repository = repository;

    }

    @Override
    protected void salvarTipo(Produtos produto, Cliente cliente) {
        produto.setCliente(cliente);
        produto.setTipoDePedido(TipoDePedido.ENCOMENDA);

        String observacoes = produto.getObservacoes();

        if (observacoes == null || observacoes.equals("")) {
            produto.setObservacoes("Solicito a encomenda do produto: " + produto.getNome_produto() + " na quantidade: " + produto.getQuantidade());
        }

        repository.save(produto);
    }

    @Override
    public boolean tipoDePedido(TipoDePedido tipoDePedido) {
        return tipoDePedido.equals(TipoDePedido.ENCOMENDA);
    }
}
