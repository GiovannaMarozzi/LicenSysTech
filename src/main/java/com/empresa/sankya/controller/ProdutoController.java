package com.empresa.sankya.controller;

import com.empresa.sankya.clientes.Verificacao;
import com.empresa.sankya.clientes.VerificacaoDeCnpjExistente;
import com.empresa.sankya.dto.EstoqueDTO;
import com.empresa.sankya.dto.ProdutosDTO;
import com.empresa.sankya.produtos.Estoque;
import com.empresa.sankya.produtos.TipoDePedido;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.service.EstoqueService;
import com.empresa.sankya.service.ProdutoService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    ProdutoService service;

    @Autowired
    EstoqueService estoqueService;

    @PostMapping("/encomenda")
    public ResponseEntity<?> encomendarNovosProdutos(@RequestBody ProdutosDTO produto){
        Verificacao verificacaoCliente = new VerificacaoDeCnpjExistente(repository);
        try {
            if(verificacaoCliente.verificacao(produto.getCliente())){
                service.novoPedidoDeEncomenda(produto, TipoDePedido.ENCOMENDA);
                return ResponseEntity.status(HttpStatus.CREATED).body("Pedido de encomenda solicitado!");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF não encontrado!");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarEstoque(@RequestBody EstoqueDTO estoque){
        estoqueService.apagarTotalmenteDoEstoque(estoque.getNomeProduto());
        return ResponseEntity.status(HttpStatus.OK).body("Todo o estoque de "+estoque.getNomeProduto()+" foi deletado");
    }

    @PutMapping("/deletar/quantidade={quantidade}")
    public ResponseEntity<?> deletarQuantidadeEspecífica(@PathVariable Integer quantidade, @RequestBody EstoqueDTO estoque){
        try{
            estoqueService.apagarParcialmente(quantidade, estoque);
            return ResponseEntity.status(HttpStatus.OK).body("Foram deletados "+quantidade+" de "+estoque.getNomeProduto()+" do estoque");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }

    }

    @PostMapping("adicionarProduto")
    public ResponseEntity<?> adicionarNovoProdutoNoEstoque(@RequestBody EstoqueDTO estoque){
        Verificacao verificacaoCliente = new VerificacaoDeCnpjExistente(repository);
        try {
            if(verificacaoCliente.verificacao(estoque.getCliente())){
                estoqueService.adicionarAoEstoque(estoque);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF não encontrado!");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }

        return null;
    }

    @PostMapping("/compra")
    public ResponseEntity<?> comprarNovosProdutos(@RequestBody ProdutosDTO produto){
        Verificacao verificacaoCliente = new VerificacaoDeCnpjExistente(repository);
        try {
            if(verificacaoCliente.verificacao(produto.getCliente())){
                service.novoPedidoDeEncomenda(produto, TipoDePedido.COMPRA);
                return ResponseEntity.status(HttpStatus.CREATED).body("Pedido de encomenda solicitado!");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF não encontrado!");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    @GetMapping("pedidos/cnpj={cnpj}")
    public Object pedidosPorCliente(@PathVariable String cnpj){
        try {
            return service.pedidosClientes(cnpj);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }
}
