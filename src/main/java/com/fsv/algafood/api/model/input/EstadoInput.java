package com.fsv.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

    @NotBlank
    @ApiModelProperty(example = "Minas Gerais", required = true)
    private String nome;
}
