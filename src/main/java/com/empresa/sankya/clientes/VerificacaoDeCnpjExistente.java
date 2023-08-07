package com.empresa.sankya.clientes;

import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.erros.CnpjExistente;
import com.empresa.sankya.repository.ClienteRepository;

public class VerificacaoDeCnpjExistente implements Verificacao{

    private final ClienteRepository repository;

    public VerificacaoDeCnpjExistente(ClienteRepository repository) {
        this.repository = repository;
    }

    public void verificacao(ClientesDTO cliente) throws CnpjExistente {
        if (repository.findByCnpj(cliente.getCnpj()) != null) {
            throw new CnpjExistente("CNPJ já existente!");
        }
    }
}
