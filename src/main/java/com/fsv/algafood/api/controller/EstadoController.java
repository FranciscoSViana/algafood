package com.fsv.algafood.api.controller;

import com.fsv.algafood.domain.exception.EntidadeEmUsoException;
import com.fsv.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.fsv.algafood.domain.model.Cidade;
import com.fsv.algafood.domain.model.Estado;
import com.fsv.algafood.domain.repository.EstadoRepository;
import com.fsv.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return cadastroEstadoService.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = cadastroEstadoService.buscar(estadoId);

        if (estado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(estado);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        try {
            estado = cadastroEstadoService.salvar(estado);

            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        try {
            Estado estadoAtual = cadastroEstadoService.buscar(estadoId);

            if (estado != null) {
                BeanUtils.copyProperties(estado, estadoAtual, "id");

                estadoAtual = cadastroEstadoService.salvar(estadoAtual);

                return ResponseEntity.ok(estadoAtual);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            cadastroEstadoService.excluir(estadoId);

            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
