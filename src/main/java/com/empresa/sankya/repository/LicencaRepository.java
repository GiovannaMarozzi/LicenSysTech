package com.empresa.sankya.repository;

import com.empresa.sankya.clientes.Cliente;
import com.empresa.sankya.dto.LicencaDTO;
import com.empresa.sankya.licenca.Licenca;
import com.empresa.sankya.licenca.TipoDeLicenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LicencaRepository extends JpaRepository<Licenca, Long> {
    List<Licenca> findByCliente(Cliente cliente);

    @Query("SELECT CASE WHEN EXISTS (SELECT l.tipoDeLicenca FROM Licenca l WHERE l.cliente.cnpj = :cnpj and l.tipoDeLicenca = :licenca) THEN TRUE ELSE FALSE END FROM Licenca l")
    Boolean findByCnpj(String cnpj, TipoDeLicenca licenca);

}
