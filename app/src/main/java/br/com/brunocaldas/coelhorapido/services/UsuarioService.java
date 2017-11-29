package br.com.brunocaldas.coelhorapido.services;

import com.google.gson.Gson;
import java.util.concurrent.ExecutionException;
import br.com.brunocaldas.coelhorapido.dtos.UsuarioLoginDTO;
import br.com.brunocaldas.coelhorapido.models.Usuario;

/**
 * Created by bruno on 28/11/2017.
 */

public class UsuarioService extends BaseService<Usuario>{

    private static final String path = "usuarios";

    public UsuarioService() {
        super(path,Usuario.class,Usuario[].class);
    }

    public Usuario fazerLogin(String login, String senha) {

        String params = new Gson().toJson(new UsuarioLoginDTO(login,senha));

        try {
            return new Gson().fromJson(new HttpService(path).doPost(params,"login"),Usuario.class);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
