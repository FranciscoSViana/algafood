package com.fsv.algafood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class })
public @interface Multiplo {

    String message() default "Múltiplo inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int numero();
}
