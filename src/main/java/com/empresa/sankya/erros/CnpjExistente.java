package com.empresa.sankya.erros;

public class CnpjExistente extends RuntimeException {
    public CnpjExistente(String mensagem) {
        super(mensagem);
    }
}
