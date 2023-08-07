package com.empresa.sankya.licenca;

import com.empresa.sankya.clientes.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Licenca")
public class Licenca {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_licenca")
    private TipoDeLicenca tipoDeLicenca;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_de_inicio")
    private Date data_de_inicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_de_vencimento")
    private Date data_de_vencimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnpj_empresa", referencedColumnName = "cnpj")
    private Cliente cliente;
}
