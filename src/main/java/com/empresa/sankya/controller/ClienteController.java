package com.empresa.sankya.controller;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.clientes.Verificacao;
import com.empresa.sankya.clientes.VerificacaoDeCnpExistente;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @Autowired
    ClienteRepository repository;

    @PostMapping("salvar")
    public ResponseEntity<?> salvar(@RequestBody Cliente informacoes){
        Verificacao verificacaoCNPJ = new VerificacaoDeCnpExistente(repository);
        try{
            if(verificacaoCNPJ.verificacao(informacoes)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já existente");
            }
            service.salvar(informacoes);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar dados do cliente");
        }
    }
}
