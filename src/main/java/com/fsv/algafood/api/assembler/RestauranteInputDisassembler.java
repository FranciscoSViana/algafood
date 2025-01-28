package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.model.input.CozinhaIdInput;
import com.fsv.algafood.api.model.input.RestauranteInput;
import com.fsv.algafood.domain.model.Cozinha;
import com.fsv.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }

    public RestauranteInput toInputObject(Restaurante restaurante) {
        RestauranteInput restauranteInput = new RestauranteInput();
        restauranteInput.setNome(restaurante.getNome());
        restauranteInput.setTaxaFrete(restaurante.getTaxaFrete());

        CozinhaIdInput cozinhaIdInput = new CozinhaIdInput();
        cozinhaIdInput.setId(restaurante.getCozinha().getId());

        restauranteInput.setCozinha(cozinhaIdInput);

        return restauranteInput;
    }
}
