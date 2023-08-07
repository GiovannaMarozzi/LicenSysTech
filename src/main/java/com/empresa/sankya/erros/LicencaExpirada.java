package com.empresa.sankya.erros;

public class LicencaExpirada extends RuntimeException{
    public LicencaExpirada(String mensagem){
        super(mensagem);
    }
}
