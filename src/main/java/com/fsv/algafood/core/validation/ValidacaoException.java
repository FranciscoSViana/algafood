package com.fsv.algafood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class ValidacaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private BindingResult bindingResult;
}
