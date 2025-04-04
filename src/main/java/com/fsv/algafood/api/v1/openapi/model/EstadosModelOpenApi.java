package com.fsv.algafood.api.v1.openapi.model;

import com.fsv.algafood.api.v1.model.EstadoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
@ApiModel("EstadosModel")
public class EstadosModelOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EstadosEmbeddedModel")
    @Data
    public class EstadosEmbeddedModelOpenApi {

        private List<EstadoModel> estados;
    }
}
