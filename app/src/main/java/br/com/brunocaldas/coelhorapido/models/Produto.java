package br.com.brunocaldas.coelhorapido.models;

import java.io.Serializable;

/**
 * Created by bruno on 29/11/2017.
 */

public class Produto implements Serializable {

    private Long id;
    private String descricao;
    private double peso;

    public Produto() {
    }

    public Produto(Long id, String descricao, double peso) {
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
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
}
