package com.empresa.sankya.service;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.ProdutosDTO;
import com.empresa.sankya.dto.PedidosDTO;
import com.empresa.sankya.produtos.Produtos;
import com.empresa.sankya.produtos.TipoDePedido;
import com.empresa.sankya.produtos.compra_encomenda.Compra;
import com.empresa.sankya.produtos.compra_encomenda.Encomenda;
import com.empresa.sankya.produtos.compra_encomenda.SalvarTipoDePedido;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.repository.produtos.EstoqueRepository;
import com.empresa.sankya.repository.produtos.ProdutoRepository;
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

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void novoPedido(ProdutosDTO produto, TipoDePedido tipoDePedido) {
        Produtos novoProduto = mapper.map(produto, Produtos.class);
        Cliente cliente = clienteRepository.findByCnpj(produto.getCliente().getCnpj());

        SalvarTipoDePedido salvarTipo = new Encomenda(repository, new Compra(null, repository, estoqueRepository));
        salvarTipo.salvar(novoProduto, cliente, tipoDePedido);
    }

    public PedidosDTO pedidosClientes(String cnpj) {
        Cliente cliente = clienteRepository.findByCnpj(cnpj);

        int quantidadeCompra = repository.countByTipoDePedido(TipoDePedido.COMPRA, cliente.getId());
        int quantidadeEncomenda = repository.countByTipoDePedido(TipoDePedido.ENCOMENDA, cliente.getId());

        PedidosDTO listaDePedidos = new PedidosDTO();

        listaDePedidos.setCnpj_cliente(cliente.getCnpj());
        listaDePedidos.setNome_cliente(cliente.getNome());
        listaDePedidos.setQuantidade_de_compras(quantidadeCompra);
        listaDePedidos.setQuantidade_de_encomendas(quantidadeEncomenda);

        return mapper.map(listaDePedidos, PedidosDTO.class);
    }

}
