package com.fsv.algafood.domain.repository;

import com.fsv.algafood.domain.model.Cidade;
import com.fsv.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}
