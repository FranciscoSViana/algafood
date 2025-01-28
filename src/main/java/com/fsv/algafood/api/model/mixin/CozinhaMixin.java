package com.fsv.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fsv.algafood.domain.model.Restaurante;

import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
