package com.fsv.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

    @NotBlank
    @ApiModelProperty(example = "123", required = true)
    private String senhaAtual;

    @NotBlank
    @ApiModelProperty(example = "123", required = true)
    private String novaSenha;
}
