package com.fsv.algafood.api.v1.assembler;

import com.fsv.algafood.api.v1.model.input.GrupoInput;
import com.fsv.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
        modelMapper.map(grupoInput, grupo);
    }

    public GrupoInput toInputObject(Grupo grupo) {
        return modelMapper.map(grupo, GrupoInput.class);
    }
}
