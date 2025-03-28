package com.fsv.algafood.api.v2.openapi.model;

import com.fsv.algafood.api.v1.model.CidadeModel;
import com.fsv.algafood.api.v2.model.CidadeModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
@ApiModel("CidadesModel")
public class CidadesModelV2OpenApi {

    private CidadeEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    @ApiModel("CidadesEmbeddedModel")
    public class CidadeEmbeddedModelOpenApi {

        private List<CidadeModelV2> cidades;
    }
}
