package com.fsv.algafood.api.controller;

import com.fsv.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.fsv.algafood.api.model.FormaPagamentoModel;
import com.fsv.algafood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.fsv.algafood.domain.model.Restaurante;
import com.fsv.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @GetMapping
    public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento());
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
    }
}
