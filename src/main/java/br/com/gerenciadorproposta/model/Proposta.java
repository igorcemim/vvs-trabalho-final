package br.com.gerenciadorproposta.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Representa uma proposta no sistema.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proposta {

    @ApiModelProperty(value = "Código da proposta", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Descrição", example = "Implementação de sistema de autenticação")
    @NotEmpty
    private String descricao;

    @ApiModelProperty(value = "Valor", example = "7500.00")
    @NotNull
    private Double valor;

    @ApiModelProperty(value = "Data da oferta inicial", example = "25/12/2019")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate data;

    @ApiModelProperty(value = "Status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne
    private Cliente cliente;
}
