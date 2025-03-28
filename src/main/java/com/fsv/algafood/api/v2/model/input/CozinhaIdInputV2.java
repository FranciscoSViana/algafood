package com.fsv.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInputV2 {

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Long idCozinha;
}
