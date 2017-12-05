package br.com.brunocaldas.coelhorapido.services;

import java.util.ArrayList;
import java.util.List;

import br.com.brunocaldas.coelhorapido.models.PontoReferencia;
/**
 * Created by bruno on 03/12/2017.
 */

public class PontoReferenciaService extends BaseService<PontoReferencia> {

    private static final String path = "referencia";

    public PontoReferenciaService() {
        super(path,PontoReferencia.class,PontoReferencia[].class);
    }

    public List<PontoReferencia> filtrarSemData(List<PontoReferencia> pontos) {
        List<PontoReferencia> pontosSemData = new ArrayList<>();

        for(PontoReferencia p: pontos) {
            if (p.getHorario() == null) {
                pontosSemData.add(p);
            }
        }
        return pontosSemData;
    }
}
