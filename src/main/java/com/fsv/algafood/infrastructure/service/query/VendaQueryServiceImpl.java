package com.fsv.algafood.infrastructure.service.query;

import com.fsv.algafood.domain.filter.VendaDiariaFilter;
import com.fsv.algafood.domain.model.Pedido;
import com.fsv.algafood.domain.model.StatusPedido;
import com.fsv.algafood.domain.model.dto.VendaDiaria;
import com.fsv.algafood.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var predicates = new ArrayList<Predicate>();

        var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class,
                root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));

        var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao, builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if (filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
        }

        if (filter.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                    filter.getDataCriacaoInicio()));
        }

        if (filter.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                    filter.getDataCriacaoFim()));
        }

        predicates.add(root.get("status").in(
                StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateDataCriacao);

        return entityManager.createQuery(query).getResultList();
    }
}
