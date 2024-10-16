package com.fsv.algafood.domain.service;

import com.fsv.algafood.domain.exception.EntidadeEmUsoException;
import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.model.Estado;
import com.fsv.algafood.domain.repository.CidadeRepository;
import com.fsv.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscar(Long estadoId) {
        return estadoRepository.findById(estadoId);
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            if (!estadoRepository.existsById(estadoId)) {
                throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código %d", estadoId));
            }
            estadoRepository.deleteById(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }
    }
}
