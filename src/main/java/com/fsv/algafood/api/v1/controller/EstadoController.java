package com.fsv.algafood.api.v1.controller;

import com.fsv.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.fsv.algafood.api.v1.assembler.EstadoModelAssembler;
import com.fsv.algafood.api.v1.model.EstadoModel;
import com.fsv.algafood.api.v1.model.input.EstadoInput;
import com.fsv.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.exception.NegocioException;
import com.fsv.algafood.domain.model.Estado;
import com.fsv.algafood.domain.repository.EstadoRepository;
import com.fsv.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @Autowired
    EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public CollectionModel<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        try {
            Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

            return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estado));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

//        BeanUtils.copyProperties(estado, estadoAtual, "id");

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        try {
            return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        cadastroEstadoService.excluir(estadoId);
    }
}
