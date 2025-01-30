package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.input.EstadoInput;
import com.fsv.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }

    public EstadoInput toInputObject(Estado estado) {
        return modelMapper.map(estado, EstadoInput.class);
    }
}
