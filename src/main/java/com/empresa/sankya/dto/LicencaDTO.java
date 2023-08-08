package com.empresa.sankya.dto;

import com.empresa.sankya.licenca.TipoDeLicenca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicencaDTO {
    private TipoDeLicenca tipoDeLicenca;
    private String data_de_inicio;
    private String data_de_vencimento;
    private ClientesDTO cliente;
}


