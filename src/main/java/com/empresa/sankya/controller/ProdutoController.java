package com.empresa.sankya.controller;

import com.empresa.sankya.clientes.Verificacao;
import com.empresa.sankya.clientes.VerificacaoDeCnpjInexistente;
import com.empresa.sankya.dto.EstoqueDTO;
import com.empresa.sankya.dto.ProdutosDTO;
import com.empresa.sankya.erros.CnpjInexistente;
import com.empresa.sankya.erros.QuantidadeInexistente;
import com.empresa.sankya.produtos.TipoDePedido;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.service.EstoqueService;
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

    @Autowired
    EstoqueService estoqueService;

    @PostMapping("/encomenda")
    public ResponseEntity<?> encomendarNovosProdutos(@RequestBody ProdutosDTO produto){
        Verificacao verificacaoCliente = new VerificacaoDeCnpjInexistente(repository);
        try {
            verificacaoCliente.verificacao(produto.getCliente());
                service.novoPedido(produto, TipoDePedido.ENCOMENDA);
                return ResponseEntity.status(HttpStatus.CREATED).body("Pedido de encomenda solicitado!");
        }catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    @PostMapping("/compra")
    public ResponseEntity<?> comprarNovosProdutos(@RequestBody ProdutosDTO produto) {
        Verificacao verificacaoCliente = new VerificacaoDeCnpjInexistente(repository);
        try {
            verificacaoCliente.verificacao(produto.getCliente());
            service.novoPedido(produto, TipoDePedido.COMPRA);
            return ResponseEntity.status(HttpStatus.CREATED).body("Compra finalizada!");

        } catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (QuantidadeInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarEstoque(@RequestBody EstoqueDTO estoque){
        estoqueService.apagarTotalmenteDoEstoque(estoque.getNomeProduto());
        return ResponseEntity.status(HttpStatus.OK).body("Todo o estoque de "+estoque.getNomeProduto()+" foi deletado");
    }

    @PutMapping("/deletar/quantidade={quantidade}")
    public ResponseEntity<?> alterarQuantidadeEspecífica(@PathVariable Integer quantidade, @RequestBody EstoqueDTO estoque){
        try{
            estoqueService.apagarParcialmente(quantidade, estoque);
            return ResponseEntity.status(HttpStatus.OK).body("Foram deletados "+quantidade+" de "+estoque.getNomeProduto()+" do estoque");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }

    }

    @PostMapping("/adicionarProduto")
    public ResponseEntity<?> adicionarNovoProdutoNoEstoque(@RequestBody EstoqueDTO estoque){
        Verificacao verificacaoCliente = new VerificacaoDeCnpjInexistente(repository);
        try {
            verificacaoCliente.verificacao(estoque.getCliente());
            estoqueService.adicionarAoEstoque(estoque);
            return ResponseEntity.status(HttpStatus.OK).body("Produto adicionado ao estoque com sucesso!");

        }catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
