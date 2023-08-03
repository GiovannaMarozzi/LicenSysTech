package com.empresa.sankya.repository;

import com.empresa.sankya.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCnpj(String cnpj);
}
