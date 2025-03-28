package com.fsv.algafood.api.v2.controller;

import com.fsv.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.fsv.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.fsv.algafood.api.v1.model.CozinhaModel;
import com.fsv.algafood.api.v1.model.input.CozinhaInput;
import com.fsv.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.fsv.algafood.api.v2.assembler.CozinhaInputDisassemblerV2;
import com.fsv.algafood.api.v2.assembler.CozinhaModelAssemblerV2;
import com.fsv.algafood.api.v2.model.CozinhaModelV2;
import com.fsv.algafood.api.v2.model.input.CozinhaInputV2;
import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.exception.NegocioException;
import com.fsv.algafood.domain.model.Cozinha;
import com.fsv.algafood.domain.repository.CozinhaRepository;
import com.fsv.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelAssemblerV2 cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<CozinhaModelV2> listar(Pageable pageable) {
        Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModelV2> cozinhasPagedModel = pagedResourcesAssembler.toModel(cozinhas, cozinhaModelAssembler);

        return cozinhasPagedModel;
    }

    @GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {
        try {
            Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);

            return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputV2 cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        try {
            return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinhaAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{cozinhaId}", produces = {})
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
