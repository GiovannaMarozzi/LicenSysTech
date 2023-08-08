package com.empresa.sankya.clientes;

import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.erros.CnpjInexistente;
import com.empresa.sankya.repository.ClienteRepository;

public class VerificacaoDeCnpjInexistente implements Verificacao{

    private final ClienteRepository repository;

    public VerificacaoDeCnpjInexistente(ClienteRepository repository) {
        this.repository = repository;
    }

    public void verificacao(ClientesDTO cliente) throws CnpjInexistente {
        if (repository.findByCnpj(cliente.getCnpj()) == null) {
            throw new CnpjInexistente("CNPJ não encontrado!");
        }
    }
}
