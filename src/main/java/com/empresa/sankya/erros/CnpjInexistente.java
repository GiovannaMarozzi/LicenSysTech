package com.empresa.sankya.erros;

public class CnpjInexistente extends RuntimeException{
    public CnpjInexistente(String mensagem){
        super(mensagem);
    }
}
