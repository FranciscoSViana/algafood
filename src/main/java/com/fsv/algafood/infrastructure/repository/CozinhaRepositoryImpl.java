package com.fsv.algafood.infrastructure.repository;

import com.fsv.algafood.domain.model.Cozinha;
import com.fsv.algafood.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return  entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long cozinhaId) {
        Cozinha cozinha = buscar(cozinhaId);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cozinha);
    }
}
