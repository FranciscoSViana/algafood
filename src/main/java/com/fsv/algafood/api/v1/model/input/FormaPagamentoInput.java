package com.fsv.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

    @NotBlank
    @ApiModelProperty(example = "Cartão de crédito", required = true)
    private String descricao;
}
