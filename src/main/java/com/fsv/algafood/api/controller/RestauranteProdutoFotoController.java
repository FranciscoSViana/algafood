package com.fsv.algafood.api.controller;

import com.fsv.algafood.api.assembler.FotoProdutoModelAssembler;
import com.fsv.algafood.api.model.FotoProdutoModel;
import com.fsv.algafood.api.model.input.FotoProdutoInput;
import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.model.FotoProduto;
import com.fsv.algafood.domain.model.Produto;
import com.fsv.algafood.domain.service.CadastroProdutoService;
import com.fsv.algafood.domain.service.CatalogoFotoProdutoService;
import com.fsv.algafood.domain.service.FotoStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private FotoStorageService fotoStorageService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return fotoProdutoModelAssembler.toModel(fotoProduto);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
        MultipartFile file = fotoProdutoInput.getArquivo();

        FotoProduto fotoProduto = new FotoProduto();
        fotoProduto.setProduto(produto);
        fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
        fotoProduto.setContentType(file.getContentType());
        fotoProduto.setTamanho(file.getSize());
        fotoProduto.setNomeArquivo(file.getOriginalFilename());

        FotoProduto fotoProdutoSalva = catalogoFotoProdutoService.salvar(fotoProduto, file.getInputStream());

        return fotoProdutoModelAssembler.toModel(fotoProdutoSalva);
    }

    @GetMapping
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
            @PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl()) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }


        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        catalogoFotoProdutoService.excluir(restauranteId, produtoId);
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypesAceita -> mediaTypesAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }


}
