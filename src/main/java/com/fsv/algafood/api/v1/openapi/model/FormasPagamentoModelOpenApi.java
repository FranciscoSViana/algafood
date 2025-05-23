package com.fsv.algafood.api.v1.openapi.model;

import com.fsv.algafood.api.v1.model.FormaPagamentoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
@ApiModel("FormasPagamentoModel")
public class FormasPagamentoModelOpenApi {

    private FormasPagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    @ApiModel("FormasPagamentoEmbeddedModel")
    public class FormasPagamentoEmbeddedModelOpenApi {

        private List<FormaPagamentoModel> formasPagamento;

    }
}
