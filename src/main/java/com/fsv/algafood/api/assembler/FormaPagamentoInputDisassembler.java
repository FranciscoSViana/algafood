package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.input.FormaPagamentoInput;
import com.fsv.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInput, formaPagamento);
    }

    public FormaPagamentoInput toInputObject(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoInput.class);
    }
}
