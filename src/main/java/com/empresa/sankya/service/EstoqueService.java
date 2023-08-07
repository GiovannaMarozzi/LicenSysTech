package com.empresa.sankya.service;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.EstoqueDTO;
import com.empresa.sankya.produtos.Estoque;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.repository.produtos.EstoqueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private final ModelMapper mapper;

    public void adicionarAoEstoque(EstoqueDTO estoque){
        Cliente cliente = clienteRepository.findByCnpj(estoque.getCliente().getCnpj());
        Estoque novoEstooque = mapper.map(estoque, Estoque.class);

        novoEstooque.setCliente(cliente);
        repository.save(novoEstooque);
    }

    @Transactional
    public void apagarTotalmenteDoEstoque(String nome_produto){
        repository.deleteByNomeProduto(nome_produto);
    }

    @Transactional
    public void apagarParcialmente(Integer quantidade, EstoqueDTO estoque) {
        Cliente cliente = clienteRepository.findByCnpj(estoque.getCliente().getCnpj());
        repository.alterarQuantidade(quantidade, cliente.getCnpj(), estoque.getNomeProduto());
    }
}
