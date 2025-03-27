package com.fsv.algafood.api.controller;

import com.fsv.algafood.api.AlgaLinks;
import com.fsv.algafood.api.assembler.ProdutoInputDisassembler;
import com.fsv.algafood.api.assembler.ProdutoModelAssembler;
import com.fsv.algafood.api.model.ProdutoModel;
import com.fsv.algafood.api.model.input.ProdutoInput;
import com.fsv.algafood.api.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.fsv.algafood.domain.model.Produto;
import com.fsv.algafood.domain.model.Restaurante;
import com.fsv.algafood.domain.repository.ProdutoRepository;
import com.fsv.algafood.domain.service.CadastroProdutoService;
import com.fsv.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Override
    @GetMapping
    public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId, @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        List<Produto> produtos = null;

        if (incluirInativos) {
            produtos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            produtos = produtoRepository.findAtivosByRestaurante(restaurante);
        }
        return produtoModelAssembler.toCollectionModel(produtos).add(algaLinks.linkToProdutos(restauranteId));
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = cadastroProdutoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = cadastroProdutoService.salvar(produtoAtual);

        return produtoModelAssembler.toModel(produtoAtual);
    }
}
