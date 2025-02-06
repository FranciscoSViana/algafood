package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.input.UsuarioInput;
import com.fsv.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {

        modelMapper.map(usuarioInput, usuario);
    }

    public UsuarioInput toInputObject(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioInput.class);
    }
}
