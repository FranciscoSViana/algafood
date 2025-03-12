package com.fsv.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "2019-12-01T18:09:02.70844Z", position = 5)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://localhost:8080/dados-invalidos", position = 10)
    private String type;

    @ApiModelProperty(example = "Dados inválidos", position = 15)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 25)
    private String userMessage;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 30)
    private List<Object> objects;

    @Getter
    @Builder
    @ApiModel("ObjetoProblema")
    public static class Object {

        @ApiModelProperty(example = "preco")
        private String name;

        @ApiModelProperty(example = "O preço é obrigatório")
        private String userMessage;
    }
}
