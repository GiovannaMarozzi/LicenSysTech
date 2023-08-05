package com.empresa.sankya.service;

import ch.qos.logback.core.net.server.Client;
import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.ProdutosDTO;
import com.empresa.sankya.produtos.Produtos;
import com.empresa.sankya.produtos.Tipo;
import com.empresa.sankya.produtos.TipoDePedido;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private ClienteRepository clienteRepository;

    public void novoPedidoDeEncomenda(ProdutosDTO produto, TipoDePedido tipoDePedido) {
        Produtos novoProduto = mapper.map(produto, Produtos.class);
        Cliente cliente = clienteRepository.findByCnpj(produto.getCliente().getCnpj());
        String observacoes = novoProduto.getObservacoes();


        novoProduto.setCliente(cliente);

        if(tipoDePedido.equals(TipoDePedido.ENCOMENDA)){
            novoProduto.setTipoDePedido(TipoDePedido.ENCOMENDA);
            if (observacoes == null || observacoes.equals("")) {
                novoProduto.setObservacoes("Solicito a encomenda do produto: " + novoProduto.getNome_produto() + " na quantidade: " + novoProduto.getQuantidade());
            }
        }else{
            novoProduto.setTipoDePedido(TipoDePedido.COMPRA);
            if (observacoes == null || observacoes.equals("")) {
                novoProduto.setObservacoes("Solicito a compra do produto: " + novoProduto.getNome_produto() + " na quantidade: " + novoProduto.getQuantidade());
            }
        }

        repository.save(novoProduto);
    }
}
