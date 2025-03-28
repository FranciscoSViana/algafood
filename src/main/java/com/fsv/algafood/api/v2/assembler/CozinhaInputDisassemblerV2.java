package com.fsv.algafood.api.v2.assembler;

import com.fsv.algafood.api.v1.model.input.CozinhaInput;
import com.fsv.algafood.api.v2.model.input.CozinhaInputV2;
import com.fsv.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInputV2 cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInputV2 cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }

    public CozinhaInputV2 toInputObject(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaInputV2.class);
    }
}
