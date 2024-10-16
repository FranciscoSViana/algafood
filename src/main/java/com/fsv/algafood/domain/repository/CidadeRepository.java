package com.fsv.algafood.domain.repository;

import com.fsv.algafood.domain.model.Cidade;
import com.fsv.algafood.domain.model.Cozinha;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Long cidadeId);
}
