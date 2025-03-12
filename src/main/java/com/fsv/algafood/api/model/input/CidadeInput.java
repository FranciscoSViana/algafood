package com.fsv.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

    @NotBlank
    @ApiModelProperty(example = "São Paulo", required = true)
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estadoInput;
}
