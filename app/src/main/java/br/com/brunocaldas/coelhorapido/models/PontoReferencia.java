package br.com.brunocaldas.coelhorapido.models;

import java.io.Serializable;

public class PontoReferencia implements Serializable {

    private Long id;
    private String descricao;
    private String detalhe;
    private Double kmFaltante;
    private Double kmPercorrido;
    private String horario;

    public PontoReferencia() {
        super();
    }
    public PontoReferencia(Long id, String descricao, String detalhe, Double kmFaltante, Double kmPercorrido,
                           String horario) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.detalhe = detalhe;
        this.kmFaltante = kmFaltante;
        this.kmPercorrido = kmPercorrido;
        this.horario = horario;
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
    public String getDetalhe() {
        return detalhe;
    }
    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
    public Double getKmFaltante() {
        return kmFaltante;
    }
    public void setKmFaltante(Double kmFaltante) {
        this.kmFaltante = kmFaltante;
    }
    public Double getKmPercorrido() {
        return kmPercorrido;
    }
    public void setKmPercorrido(Double kmPercorrido) {
        this.kmPercorrido = kmPercorrido;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
}
