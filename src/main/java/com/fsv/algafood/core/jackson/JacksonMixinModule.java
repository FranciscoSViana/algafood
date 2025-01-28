package com.fsv.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fsv.algafood.api.model.mixin.CidadeMixin;
import com.fsv.algafood.api.model.mixin.CozinhaMixin;
import com.fsv.algafood.domain.model.Cidade;
import com.fsv.algafood.domain.model.Cozinha;
import com.fsv.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
