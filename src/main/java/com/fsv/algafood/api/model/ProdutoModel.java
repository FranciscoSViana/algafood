package com.fsv.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Espetinho de Cupim")
    private String nome;

    @ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete")
    private String descricao;

    @ApiModelProperty(example = "12.50")
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    private Boolean ativo;
}
