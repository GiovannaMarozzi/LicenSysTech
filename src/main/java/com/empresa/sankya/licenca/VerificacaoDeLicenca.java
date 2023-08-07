package com.empresa.sankya.licenca;

import com.empresa.sankya.erros.LicencaExistente;
import com.empresa.sankya.repository.LicencaRepository;

public class VerificacaoDeLicenca implements VerificacaoLicenca {

    private final LicencaRepository repository;

    public VerificacaoDeLicenca(LicencaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void verificacaoDeLicenca(TipoDeLicenca nome_licenca, String cnpj) {
        if(repository.findByCnpj(cnpj, nome_licenca)){
            throw new LicencaExistente("Licenca já existente para o cnpj "+cnpj);
        }
    }
}
