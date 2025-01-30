package com.fsv.algafood.api.controller;

import com.fsv.algafood.api.assembler.EstadoInputDisassembler;
import com.fsv.algafood.api.assembler.EstadoModelAssembler;
import com.fsv.algafood.api.model.EstadoModel;
import com.fsv.algafood.api.model.input.EstadoInput;
import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.exception.NegocioException;
import com.fsv.algafood.domain.model.Estado;
import com.fsv.algafood.domain.repository.EstadoRepository;
import com.fsv.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @Autowired
    EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public List<EstadoModel> listar() {
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
