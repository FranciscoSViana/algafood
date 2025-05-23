package com.fsv.algafood.api.v1.openapi.model;

import com.fsv.algafood.api.v1.model.UsuarioModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@Data
@ApiModel("UsuariosModel")
public class UsuariosModelOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    @ApiModel("UsuariosEmbeddedModel")
    public class UsuariosEmbeddedModelOpenApi {

        private List<UsuarioModel> usuarios;
    }
}
