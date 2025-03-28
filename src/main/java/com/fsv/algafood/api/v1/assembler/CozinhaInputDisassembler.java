package com.fsv.algafood.api.v1.assembler;

import com.fsv.algafood.api.v1.model.input.CozinhaInput;
import com.fsv.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }

    public CozinhaInput toInputObject(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaInput.class);
    }
}
