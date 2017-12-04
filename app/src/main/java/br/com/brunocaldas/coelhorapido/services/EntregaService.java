package br.com.brunocaldas.coelhorapido.services;

import java.util.ArrayList;
import java.util.List;

import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.Usuario;

public class EntregaService extends BaseService<Entrega> {

    private static final String path = "entregas";

    public EntregaService() {
        super(path,Entrega.class,Entrega[].class);
    }

    public List<Entrega> buscarPorUsuario(Usuario usuario) {
        List<Entrega> entregasTemp = buscarTodos();
        List<Entrega> entregas = new ArrayList<>();

        for (Entrega e : entregasTemp) {
            if (e.getCliente().getId().equals(usuario.getId())) {
                entregas.add(e);
            }
        }
        return entregas;
    }

    public List<Entrega> buscarSemMotorista() {
        List<Entrega> entregasTemp = buscarTodos();
        List<Entrega> entregas = new ArrayList<>();

        for (Entrega e : entregasTemp) {
            if (e.getMotorista() == null) {
                entregas.add(e);
            }
        }
        return entregas;
    }
}
