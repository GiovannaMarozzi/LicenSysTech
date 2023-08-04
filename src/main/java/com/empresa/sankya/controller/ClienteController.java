package com.empresa.sankya.controller;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.clientes.Verificacao;
import com.empresa.sankya.clientes.VerificacaoDeCnpjExistente;
import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @Autowired
    ClienteRepository repository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody ClientesDTO informacoes) {
        Verificacao verificacaoCNPJ = new VerificacaoDeCnpjExistente(repository);
        try {
            if (verificacaoCNPJ.verificacao(informacoes)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ já existente");
            }
            service.salvar(informacoes);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar dados do cliente");
        }
    }

    @GetMapping
    public Object clientes() {
        try {
            return service.clientes();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar dados do cliente");
        }
    }

    @GetMapping("/cnpj={cnpj}")
    public Object buscarClientePorCNPJ(@PathVariable String cnpj) {
        try {
            return service.clientePorCnpj(cnpj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }

    @PutMapping
    public ResponseEntity<?> alterarDadosCliente(@RequestBody ClientesDTO cliente) {
        Verificacao verificacaoCnpj = new VerificacaoDeCnpjExistente(repository);
        try {
            if (verificacaoCnpj.verificacao(cliente)) {
                ClientesDTO clienteAlterado = service.alteracaoCadastral(cliente);
                return ResponseEntity.status(HttpStatus.OK).body(clienteAlterado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }

    @DeleteMapping("/cnpj={cnpj}")
    public ResponseEntity<?> deletarCliente(@PathVariable String cnpj) {
        try {
            service.deletarCliente(cnpj);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }
}
