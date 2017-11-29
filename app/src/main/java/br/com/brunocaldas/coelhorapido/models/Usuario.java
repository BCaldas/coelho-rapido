package br.com.brunocaldas.coelhorapido.models;

import java.io.Serializable;
import br.com.brunocaldas.coelhorapido.enums.ETipoUsuario;

/**
 * Created by bruno on 27/11/2017.
 */

public class Usuario implements Serializable{

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private boolean ativo;
    private String cadastro;
    private ETipoUsuario tipo;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String login, String senha, boolean ativo, String cadastro, ETipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.cadastro = cadastro;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public ETipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(ETipoUsuario tipo) {
        this.tipo = tipo;
    }
}
