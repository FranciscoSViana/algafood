package com.fsv.algafood.api.v1.assembler;

import com.fsv.algafood.api.v1.AlgaLinks;
import com.fsv.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.fsv.algafood.api.v1.model.FotoProdutoModel;
import com.fsv.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        FotoProdutoModel fotoProdutoModel = createModelWithId(fotoProduto.getId(), fotoProduto);
        modelMapper.map(fotoProduto, fotoProdutoModel);

        fotoProdutoModel.add(algaLinks.linkToFotoProduto(fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId()));

        fotoProdutoModel.add(algaLinks.linkToFotoProduto(fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId(), "produto"));

        return fotoProdutoModel;
    }
}
