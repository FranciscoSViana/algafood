package com.fsv.algafood.domain.model;

import com.fsv.algafood.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    private Estado estado;
}
