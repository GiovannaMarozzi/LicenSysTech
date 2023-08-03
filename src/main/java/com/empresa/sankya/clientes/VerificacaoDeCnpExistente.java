package com.empresa.sankya.clientes;

import com.empresa.sankya.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VerificacaoDeCnpExistente implements Verificacao{

    private final ClienteRepository repository;

    public VerificacaoDeCnpExistente(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean verificacao(Cliente cliente) {
        if(repository.findByCnpj(cliente.getCnpj()) == null){
            return false;
        }
        return true;
    }
}
