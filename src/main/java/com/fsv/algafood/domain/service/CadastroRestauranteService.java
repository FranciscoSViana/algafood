package com.fsv.algafood.domain.service;

import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.model.Cozinha;
import com.fsv.algafood.domain.model.Restaurante;
import com.fsv.algafood.domain.repository.CozinhaRepository;
import com.fsv.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.buscar(restauranteId);
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }
}
