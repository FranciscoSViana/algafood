package com.fsv.algafood.domain.repository;

import com.fsv.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto fotoProduto);
    void delete(FotoProduto fotoProduto);
}
