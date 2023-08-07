package com.empresa.sankya.produtos.compra_encomenda;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.erros.QuantidadeInexistente;
import com.empresa.sankya.produtos.Produtos;
import com.empresa.sankya.produtos.TipoDePedido;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.repository.produtos.EstoqueRepository;
import com.empresa.sankya.repository.produtos.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Compra extends SalvarTipoDePedido{

    private ProdutoRepository produtoRepository;
    private EstoqueRepository estoqueRepository;
//    private ClienteRepository clienteRepository;

    public Compra(SalvarTipoDePedido proximo, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
        super(proximo);
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Override
    @Transactional
    protected void salvarTipo(Produtos produto, Cliente cliente) {
        produto.setCliente(cliente);
        produto.setTipoDePedido(TipoDePedido.COMPRA);

        String observacoes = produto.getObservacoes();

        produto.setTipoDePedido(TipoDePedido.COMPRA);

        boolean existentesNoEstoque = estoqueRepository.existsByProduto(produto.getNome_produto(), produto.getQuantidade());

        if (existentesNoEstoque){
            if (observacoes == null || observacoes.equals("")) {
                produto.setObservacoes("Compra do produto: " + produto.getNome_produto() + " na quantidade: " + produto.getQuantidade());
            }
            estoqueRepository.alterarQuantidade(produto.getQuantidade(), cliente.getCnpj(), produto.getNome_produto());
            produtoRepository.save(produto);
        }else {
            throw new QuantidadeInexistente("Quantidade inexistente");
        }

    }

    @Override
    public boolean tipoDePedido(TipoDePedido tipoDePedido) {
        return tipoDePedido.equals(TipoDePedido.COMPRA);
    }
}
