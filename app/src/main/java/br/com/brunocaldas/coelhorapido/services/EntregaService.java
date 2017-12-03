package br.com.brunocaldas.coelhorapido.services;

import br.com.brunocaldas.coelhorapido.models.Entrega;

public class EntregaService extends BaseService<Entrega> {

    private static final String path = "entregas";

    public EntregaService() {
        super(path,Entrega.class,Entrega[].class);
    }
}
