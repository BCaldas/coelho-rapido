package br.com.brunocaldas.coelhorapido.services;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.Usuario;

public class EntregaService extends BaseService<Entrega> {

    private static final String path = "entregas";

    public EntregaService() {
        super(path, Entrega.class, Entrega[].class);
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

    public List<Entrega> buscarPorMotorista(Usuario motorista) {
        List<Entrega> entregasTemp = buscarTodos();
        List<Entrega> entregas = new ArrayList<>();

        for (Entrega e : entregasTemp) {
            if (e.getMotorista() != null) {
                if (e.getMotorista().getId().equals(motorista.getId())) {
                    entregas.add(e);
                }
            }

        }
        return entregas;
    }

    public List<Entrega> buscarAbertas() {

        try {
            return Arrays.asList(new Gson().fromJson(new HttpService(path).doGet("abertas"), Entrega[].class));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Entrega> buscarAbertasPorMotorista(Usuario motorista) {
        List<Entrega> abertas = new ArrayList<>();
        List<Entrega> entregas = buscarAbertas();

        if (entregas != null && !entregas.isEmpty()) {

            for (Entrega e : entregas) {
                if (e.getMotorista().getId().equals(motorista.getId())) {
                    abertas.add(e);
                }
            }
        }
        return abertas;
    }
}
