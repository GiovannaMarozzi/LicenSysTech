package com.empresa.sankya.service;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.ClientesDTO;
import com.empresa.sankya.dto.LicencaDTO;
import com.empresa.sankya.erros.CnpjExistente;
import com.empresa.sankya.erros.CnpjInexistente;
import com.empresa.sankya.erros.LicencaExpirada;
import com.empresa.sankya.licenca.Licenca;
import com.empresa.sankya.repository.ClienteRepository;
import com.empresa.sankya.repository.LicencaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private LicencaRepository licencaRepository;

    @Autowired
    private final ModelMapper mapper;

    public void salvar(ClientesDTO cliente){
        Cliente novoCliente = mapper.map(cliente, Cliente.class);
        repository.save(novoCliente);
    }

    public List<Cliente> clientes() {
        return repository.findAll();
    }

    public ClientesDTO clientePorCnpj(String cnpj) {
        Cliente cliente = repository.findByCnpj(cnpj);
        return mapper.map(cliente, ClientesDTO.class);
    }

    public ClientesDTO alteracaoCadastral(ClientesDTO cliente) {
        Cliente clienteExistente = repository.findByCnpj(cliente.getCnpj());
        Date dataAtual = new Date();

        if(cliente.getNome().equals(clienteExistente.getNome())){
            clienteExistente.setNome(cliente.getNome());
        }

        if(cliente.getEmail().equals(clienteExistente.getEmail())){
            clienteExistente.setEmail(cliente.getEmail());
        }

        if(cliente.getCep().equals(clienteExistente.getCep())){
            clienteExistente.setCep(cliente.getCep());
        }

        clienteExistente.setAlterado(true);
        clienteExistente.setData_alteracao_cadastral(dataAtual);

        repository.save(clienteExistente);

        Cliente clienteAlterado = repository.findByCnpj(cliente.getCnpj());

        return mapper.map(clienteAlterado, ClientesDTO.class);
    }

    public void deletarCliente(String cnpj) {
        Cliente clienteExistente = repository.findByCnpj(cnpj);
        repository.deleteById(clienteExistente.getId());
    }

    @SneakyThrows
    public void adicionarNovaLicenca(LicencaDTO licenca) {
        Cliente clienteExistente = repository.findByCnpj(licenca.getCliente().getCnpj());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data_de_inicio = dateFormat.parse(licenca.getData_de_inicio());
        Date data_de_vencimento = dateFormat.parse(licenca.getData_de_vencimento());
        Date dataAtual = new Date();

        Licenca novaLicenca = new Licenca();
        novaLicenca.setTipoDeLicenca(licenca.getTipoDeLicenca());
        novaLicenca.setData_de_inicio(data_de_inicio);
        novaLicenca.setData_de_vencimento(data_de_vencimento);
        novaLicenca.setCliente(clienteExistente);

        if (data_de_vencimento.after(dataAtual)) {
            licencaRepository.save(novaLicenca);
        } else {
            throw new LicencaExpirada("A data de validade da Licenca expirou.");
        }
    }

    public List<LicencaDTO> licencas(String cnpj) {
        Cliente clienteExistente = repository.findByCnpj(cnpj);

        if (clienteExistente == null) {
            throw new CnpjInexistente("CNPJ não encontrado!");
        }

        List<Licenca> licencas = licencaRepository.findByCliente(clienteExistente);

        return licencas.stream()
                .map(licenca -> mapper.map(licenca, LicencaDTO.class))
                .collect(Collectors.toList());
    }

}
