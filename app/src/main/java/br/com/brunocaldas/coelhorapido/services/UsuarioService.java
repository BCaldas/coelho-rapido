package br.com.brunocaldas.coelhorapido.services;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import br.com.brunocaldas.coelhorapido.models.Usuario;

/**
 * Created by bruno on 28/11/2017.
 */

public class UsuarioService extends BaseService {

    public UsuarioService() {
        super("usuarios");
    }

    public Usuario fazerLogin(String login, String senha) throws JSONException, ExecutionException, InterruptedException {

        JSONObject obj = new JSONObject();

        obj.put("login", login);
        obj.put("senha",senha);

        return new Gson().fromJson(this.doPost(obj,"login"),Usuario.class);
    }

    public Usuario buscarPorId(Integer id) throws ExecutionException, InterruptedException {
        return new Gson().fromJson(this.doGet(id.toString()),Usuario.class);
    }
}
