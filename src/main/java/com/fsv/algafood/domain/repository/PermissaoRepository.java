package com.fsv.algafood.domain.repository;

import com.fsv.algafood.domain.model.Cidade;
import com.fsv.algafood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
