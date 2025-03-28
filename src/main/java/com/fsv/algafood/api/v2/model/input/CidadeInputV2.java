package com.fsv.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CidadeInput")
public class CidadeInputV2 {

    @NotBlank
    @ApiModelProperty(example = "São Paulo", required = true)
    private String nomeCidade;

    @Valid
    @NotNull
    private Long idEstado;
}
