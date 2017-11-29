package br.com.brunocaldas.coelhorapido.dtos;

import br.com.brunocaldas.coelhorapido.models.Usuario;

/**
 * Created by bruno on 29/11/2017.
 */

public class UsuarioLoginDTO {

    private String login;
    private String senha;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static UsuarioLoginDTO builder(Usuario u){
        return new UsuarioLoginDTO(u.getLogin(),u.getSenha());
    }
}
