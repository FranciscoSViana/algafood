package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.FotoProdutoModel;
import com.fsv.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoModel.class);
    }
}
