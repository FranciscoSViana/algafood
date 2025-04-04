package com.fsv.algafood.api.v2.assembler;

import com.fsv.algafood.api.v1.AlgaLinks;
import com.fsv.algafood.api.v1.controller.CozinhaController;
import com.fsv.algafood.api.v1.model.CozinhaModel;
import com.fsv.algafood.api.v2.AlgaLinksV2;
import com.fsv.algafood.api.v2.controller.CozinhaControllerV2;
import com.fsv.algafood.api.v2.model.CozinhaModelV2;
import com.fsv.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV2 algaLinks;

    public CozinhaModelAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaModelV2.class);
    }

    public CozinhaModelV2 toModel(Cozinha cozinha) {

        CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);

        cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }
}
