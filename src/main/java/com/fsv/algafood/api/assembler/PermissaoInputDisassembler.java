package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.input.PermissaoInput;
import com.fsv.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Permissao toDomainObject(PermissaoInput permissaoInput) {
        return modelMapper.map(permissaoInput, Permissao.class);
    }

    public void copyToDomainObject(PermissaoInput permissaoInput, Permissao permissao) {
        modelMapper.map(permissaoInput, permissao);
    }

    public PermissaoInput toInputObject(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoInput.class);
    }
}
