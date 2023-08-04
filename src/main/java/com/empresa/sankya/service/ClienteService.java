package com.empresa.sankya.service;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private final ModelMapper mapper;

    public void salvar(ClientesDTO cliente){
        Cliente novoCliente = mapper.map(cliente, Cliente.class);
        repository.save(novoCliente);
    }

    public List<Cliente> clientes() {
        return repository.findAll();
    }

    public ClientesDTO clientePorCnpj(String cnpj) {
        Cliente cliente = repository.findByCnpj(cnpj);
        return mapper.map(cliente, ClientesDTO.class);
    }

    public ClientesDTO alteracaoCadastral(ClientesDTO cliente) {
        Cliente clienteExistente = repository.findByCnpj(cliente.getCnpj());
        Date dataAtual = new Date();

        if(cliente.getNome().equals(clienteExistente.getNome())){
            clienteExistente.setNome(cliente.getNome());
        }

        if(cliente.getEmail().equals(clienteExistente.getEmail())){
            clienteExistente.setEmail(cliente.getEmail());
        }

        if(cliente.getCep().equals(clienteExistente.getCep())){
            clienteExistente.setCep(cliente.getCep());
        }

        clienteExistente.setAlterado(true);
        clienteExistente.setData_alteracao_cadastral(dataAtual);

        repository.save(clienteExistente);

        Cliente clienteAlterado = repository.findByCnpj(cliente.getCnpj());

        return mapper.map(clienteAlterado, ClientesDTO.class);
    }

    public void deletarCliente(String cnpj) {
        Cliente clienteExistente = repository.findByCnpj(cnpj);
        repository.deleteById(clienteExistente.getId());
    }
}
