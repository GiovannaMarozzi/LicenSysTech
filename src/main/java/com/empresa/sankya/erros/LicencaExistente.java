package com.empresa.sankya.erros;

public class LicencaExistente extends RuntimeException{

    public LicencaExistente(String mensagem){
        super(mensagem);
    }
}
