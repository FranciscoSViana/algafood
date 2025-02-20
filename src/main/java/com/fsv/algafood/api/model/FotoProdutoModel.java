package com.fsv.algafood.api.model;

import com.fsv.algafood.domain.model.Produto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
