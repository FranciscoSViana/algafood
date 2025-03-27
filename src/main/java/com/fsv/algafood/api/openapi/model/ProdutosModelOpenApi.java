package com.fsv.algafood.api.openapi.model;

import com.fsv.algafood.api.model.ProdutoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
@ApiModel("ProdutosModel")
public class ProdutosModelOpenApi {

    private ProdutosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    @ApiModel("ProdutosEmbeddedModel")
    public class ProdutosEmbeddedModelOpenApi {

        private List<ProdutoModel> produtos;
    }
}
