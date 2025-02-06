package com.fsv.algafood.core.modelmapper;

import com.fsv.algafood.api.model.EnderecoModel;
import com.fsv.algafood.api.model.input.ItemPedidoInput;
import com.fsv.algafood.domain.model.Endereco;
import com.fsv.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDestino, value) -> enderecoModelDestino.getCidade().setEstado(value));

        return modelMapper;
    }
}
