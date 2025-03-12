package com.fsv.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    @ApiModelProperty(example = "Espetinho de Cupim", required = true)
    private String nome;

    @NotBlank
    @ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete", required = true)
    private String descricao;

    @NotNull
    @PositiveOrZero
    @ApiModelProperty(example = "12.50", required = true)
    private BigDecimal preco;

    @NotNull
    @ApiModelProperty(example = "true", required = true)
    private Boolean ativo;
}
