package com.fsv.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CozinhaInput")
public class CozinhaInputV2 {

    @NotBlank
    private String nomeCozinha;
}
