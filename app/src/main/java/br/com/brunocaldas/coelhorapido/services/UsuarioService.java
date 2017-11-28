package br.com.brunocaldas.coelhorapido.services;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import br.com.brunocaldas.coelhorapido.Usuario;

/**
 * Created by bruno on 28/11/2017.
 */

public class UsuarioService extends BaseService {

    public UsuarioService() {
        this.path = "usuarios";
    }

    public Usuario fazerLogin() {

        return null;
    }

    public Usuario buscarPorId(Integer id) throws ExecutionException, InterruptedException {
        this.method = "GET";
        this.params = id.toString();
        return new Gson().fromJson(this.execute().get(), Usuario.class);
    }
}
