package com.fsv.algafood.domain.service;

import com.fsv.algafood.domain.filter.VendaDiariaFilter;
import com.fsv.algafood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
