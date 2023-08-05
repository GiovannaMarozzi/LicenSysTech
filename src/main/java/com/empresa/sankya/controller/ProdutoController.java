package com.empresa.sankya.controller;

import com.empresa.sankya.clientes.Verificacao;
import com.empresa.sankya.clientes.VerificacaoDeCnpjExistente;
import com.empresa.sankya.dto.ProdutosDTO;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    ProdutoService service;

    @PostMapping("/encomenda")
    public ResponseEntity<?> encomendarNovosProdutos(@RequestBody ProdutosDTO produto){
        Verificacao verificacaoCliente = new VerificacaoDeCnpjExistente(repository);
        try {
            if(verificacaoCliente.verificacao(produto.getCliente())){
                service.novoPedidoDeEncomenda(produto);
                return ResponseEntity.status(HttpStatus.CREATED).body("Pedido de encomenda solicitado!");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF não encontrado!");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
}
