package com.fsv.algafood.api.assembler;

import com.fsv.algafood.api.AlgaLinks;
import com.fsv.algafood.api.controller.PedidoController;
import com.fsv.algafood.api.model.PedidoResumoModel;
import com.fsv.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido pedido) {

        PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(algaLinks.linkToPedidos("pedidos"));

        pedidoResumoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoResumoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

        return pedidoResumoModel;
    }
}
