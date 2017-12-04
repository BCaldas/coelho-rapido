package br.com.brunocaldas.coelhorapido.services;

import br.com.brunocaldas.coelhorapido.models.PontoReferencia;
/**
 * Created by bruno on 03/12/2017.
 */

public class PontoReferenciaService extends BaseService<PontoReferencia> {

    private static final String path = "referencia";

    public PontoReferenciaService() {
        super(path,PontoReferencia.class,PontoReferencia[].class);
    }
}
