package com.fsv.algafood.api.v1.assembler;

import com.fsv.algafood.api.v1.AlgaLinks;
import com.fsv.algafood.api.v1.controller.RestauranteController;
import com.fsv.algafood.api.v1.model.RestauranteBasicoModel;
import com.fsv.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteBasicoModelAssembler() {
        super(RestauranteController.class, RestauranteBasicoModel.class);
    }

    @Override
    public RestauranteBasicoModel toModel(Restaurante restaurante) {
        RestauranteBasicoModel restauranteBasicoModel = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteBasicoModel);

        restauranteBasicoModel.add(algaLinks.linkToRestaurantes("restaurantes"));

        return restauranteBasicoModel;
    }

    @Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
    }
}
