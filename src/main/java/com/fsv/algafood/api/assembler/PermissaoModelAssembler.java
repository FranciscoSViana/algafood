package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.AlgaLinks;
import com.fsv.algafood.api.controller.PermissaoController;
import com.fsv.algafood.api.model.PermissaoModel;
import com.fsv.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoModelAssembler() {
        super(PermissaoController.class, PermissaoModel.class);
    }

    @Override
    public PermissaoModel toModel(Permissao permissao) {
        PermissaoModel permissaoModel = createModelWithId(permissao.getId(), permissao);
        modelMapper.map(permissao, permissaoModel);

        return permissaoModel;
    }

    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToPermissoes());
    }
}
