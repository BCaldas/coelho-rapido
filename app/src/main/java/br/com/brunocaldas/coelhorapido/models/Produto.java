package br.com.brunocaldas.coelhorapido.models;

import java.io.Serializable;

/**
 * Created by bruno on 29/11/2017.
 */

public class Produto implements Serializable {

    private Long id;
    private String descricao;
    private Double peso;

    public Produto() {
    }

    public Produto(Long id, String descricao, Double peso) {
        this.id = id;
        this.descricao = descricao;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
