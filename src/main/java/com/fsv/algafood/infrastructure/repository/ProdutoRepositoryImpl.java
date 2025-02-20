package com.fsv.algafood.infrastructure.repository;

import com.fsv.algafood.domain.model.FotoProduto;
import com.fsv.algafood.domain.repository.ProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public FotoProduto save(FotoProduto fotoProduto) {
        return entityManager.merge(fotoProduto);
    }

    @Override
    @Transactional
    public void delete(FotoProduto fotoProduto) {
        entityManager.remove(fotoProduto);
    }
}
