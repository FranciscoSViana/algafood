package com.fsv.algafood.api.v1.openapi.controller;

import com.fsv.algafood.api.exceptionhandler.Problem;
import com.fsv.algafood.api.v1.model.UsuarioModel;
import com.fsv.algafood.api.v1.model.input.SenhaInput;
import com.fsv.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.fsv.algafood.api.v1.model.input.UsuarioInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    CollectionModel<UsuarioModel> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioModel buscar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
    UsuarioModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
            UsuarioComSenhaInput usuarioInput);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário atualizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioModel atualizar(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long usuarioId,

            @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados",
                    required = true)
            UsuarioInput usuarioInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void alterarSenha(
            @ApiParam(value = "ID do usuário", example = "1", required = true)
            Long usuarioId,

            @ApiParam(name = "corpo", value = "Representação de uma nova senha",
                    required = true)
            SenhaInput senha);
}
