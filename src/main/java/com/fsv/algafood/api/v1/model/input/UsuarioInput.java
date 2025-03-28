package com.fsv.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    @NotBlank
    @ApiModelProperty(example = "João da Silva", required = true)
    private String nome;

    @Email
    @NotBlank
    @ApiModelProperty(example = "joao.ger@algafood.com.br", required = true)
    private String email;
}
