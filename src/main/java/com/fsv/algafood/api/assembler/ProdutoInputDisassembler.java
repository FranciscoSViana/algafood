package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.input.ProdutoInput;
import com.fsv.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produtoInput);
    }

    public ProdutoInput toInputObject(Produto produto) {
        return modelMapper.map(produto, ProdutoInput.class);
    }
}
