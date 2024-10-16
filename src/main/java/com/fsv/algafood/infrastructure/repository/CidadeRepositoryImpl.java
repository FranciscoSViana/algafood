package com.fsv.algafood.infrastructure.repository;

import com.fsv.algafood.domain.model.Cidade;
import com.fsv.algafood.domain.model.Cozinha;
import com.fsv.algafood.domain.repository.CidadeRepository;
import com.fsv.algafood.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return  entityManager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Long cidadeId) {
        Cidade cidade = buscar(cidadeId);

        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cidade);
    }
}
