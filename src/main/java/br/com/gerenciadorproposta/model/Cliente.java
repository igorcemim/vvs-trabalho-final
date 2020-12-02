package br.com.gerenciadorproposta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.caelum.stella.bean.validation.CNPJ;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa um cliente no sistema.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @ApiModelProperty(value = "Código do cliente", example = "1")
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(value = "CNPJ sem pontuação.", example = "09211531000106")
    @CNPJ(message = "O campo deve conter um CNPJ válido.")
    @NotEmpty
    private String cnpj;

    @ApiModelProperty(value = "Razão social", example = "Exemplo de Empresa LTDA")
    @NotEmpty
    private String razaoSocial;

    @ApiModelProperty(value = "Telefone com DDD (2 dígitos) e sem pontuação", dataType = "string", example = "51912345678")
    @Pattern(message = "O campo deve conter um telefone válido.", regexp = "[0-9]{11}")
    @NotEmpty(message = "O campo é obrigatório.")
    private String telefone;

    @ApiModelProperty(value = "E-mail", example = "exemplo@empresa.com.br")
    @Email(message = "O campo deve conter um e-mail válido.")
    @NotEmpty(message = "O campo é obrigatório.")
    private String email;

    @ApiModelProperty(value = "Propostas do cliente", example = "[ { } ]")
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Proposta> propostas;
}