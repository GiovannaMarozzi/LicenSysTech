package com.empresa.sankya.controller;

import com.empresa.sankya.clientes.Verificacao;
import com.empresa.sankya.clientes.VerificacaoDeCnpjExistente;
import com.empresa.sankya.clientes.VerificacaoDeCnpjInexistente;
import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.dto.LicencaDTO;
import com.empresa.sankya.erros.CnpjExistente;
import com.empresa.sankya.erros.CnpjInexistente;
import com.empresa.sankya.erros.LicencaExistente;
import com.empresa.sankya.erros.LicencaExpirada;
import com.empresa.sankya.licenca.Licenca;
import com.empresa.sankya.licenca.VerificacaoDeLicenca;
import com.empresa.sankya.licenca.VerificacaoLicenca;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.repository.LicencaRepository;
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

    @Autowired
    LicencaRepository licencaRepository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody ClientesDTO informacoes) {
        Verificacao verificacaoCNPJ = new VerificacaoDeCnpjExistente(repository);
        try {
            verificacaoCNPJ.verificacao(informacoes);
            service.salvar(informacoes);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso!");
        } catch (CnpjExistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar dados do cliente. Erro: "+e.getMessage());
        }
    }

    @GetMapping
    public Object clientes() {
        try {
            return service.clientes();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor. Erro: "+e.getMessage());
        }
    }

    @GetMapping("/cnpj={cnpj}")
    public Object buscarClientePorCNPJ(@PathVariable String cnpj) {
        try {
            return service.clientePorCnpj(cnpj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor. Erro: "+e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> alterarDadosCliente(@RequestBody ClientesDTO cliente) {
        Verificacao verificacaoCnpj = new VerificacaoDeCnpjInexistente(repository);
        try {
            verificacaoCnpj.verificacao(cliente);
            ClientesDTO clienteAlterado = service.alteracaoCadastral(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteAlterado);
        } catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor. Erro: "+e.getMessage());
        }
    }

    @DeleteMapping("/cnpj={cnpj}")
    public ResponseEntity<?> deletarCliente(@PathVariable String cnpj) {
        try {
            service.deletarCliente(cnpj);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
        }catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor. Erro: "+e.getMessage());
        }
    }

    @PostMapping("/licenca")
    public ResponseEntity<?> adicionarNovasLicencas(@RequestBody LicencaDTO licenca) {
        Verificacao verificacaoCnpj = new VerificacaoDeCnpjInexistente(repository);
        VerificacaoLicenca verificacaoLicenca = new VerificacaoDeLicenca(licencaRepository);
        try {
            verificacaoCnpj.verificacao(licenca.getCliente());
            verificacaoLicenca.verificacaoDeLicenca(licenca.getTipoDeLicenca(), licenca.getCliente().getCnpj());
            service.adicionarNovaLicenca(licenca);
            return ResponseEntity.status(HttpStatus.OK).body("Licença adicionada ao cnpj: " + licenca.getCliente().getCnpj());
        }catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (LicencaExistente e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (LicencaExpirada e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor. Erro: "+e.getMessage());
        }
    }

    @GetMapping("/licenca/cnpj={cnpj}")
    public Object verificacaoDeLicencas(@PathVariable String cnpj) {
        try {
            return service.licencas(cnpj);
        } catch (CnpjInexistente e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor. Erro: "+e.getMessage());
        }
    }


}
