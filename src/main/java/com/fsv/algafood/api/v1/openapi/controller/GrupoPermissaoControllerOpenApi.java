package com.fsv.algafood.api.v1.openapi.controller;

import com.fsv.algafood.api.exceptionhandler.Problem;
import com.fsv.algafood.api.v1.model.PermissaoModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões associadas a um grupo")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    CollectionModel<PermissaoModel> listar(
            @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(
            @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
            @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

    @ApiOperation("Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class)
    })
    ResponseEntity<Void> associar(
            @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId,
            @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);
}
