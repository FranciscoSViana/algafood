package com.fsv.algafood.api.v1.openapi.model;

import com.fsv.algafood.api.v1.model.CozinhaModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@Getter
@Setter
@ApiModel("CozinhasModel")
public class CozinhasModelOpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @Data
    @ApiModel("CozinhasEmbeddedModel")
    public class CozinhasEmbeddedModelOpenApi {

        private List<CozinhaModel> cozinhas;
    }
}
