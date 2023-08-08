package com.empresa.sankya.erros;

public class QuantidadeInexistente extends RuntimeException{
    public QuantidadeInexistente(String mensagem) {
        super(mensagem);
    }
}
