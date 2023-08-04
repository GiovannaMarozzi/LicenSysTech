package com.empresa.sankya.clientes;

import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.repository.ClienteRepository;

public class VerificacaoDeCnpExistente implements Verificacao{

    private final ClienteRepository repository;

    public VerificacaoDeCnpExistente(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean verificacao(ClientesDTO cliente) {
        if(repository.findByCnpj(cliente.getCnpj()) == null){
            return false;
        }
        return true;
    }
}
