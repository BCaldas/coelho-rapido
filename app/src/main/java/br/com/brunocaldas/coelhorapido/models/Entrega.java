package br.com.brunocaldas.coelhorapido.models;

import java.io.Serializable;
import java.util.List;

public class Entrega implements Serializable {

    private Long id;
    private String cadastro;
    private PontoReferencia origem;
    private PontoReferencia destino;
    private Usuario cliente;
    private Usuario motorista;
    private List<PontoReferencia> pontos;
    private Produto produto;
    private double valor;
    private double kmPercorrido;
    private boolean entregaAberta;
    public Entrega() {
        super();
    }
    public Entrega(Long id, String cadastro, PontoReferencia origem, PontoReferencia destino, Usuario cliente,
                   Usuario motorista, List<PontoReferencia> pontos, Produto produto, double valor, double kmPercorrido,
                   boolean entregaAberta) {
        super();
        this.id = id;
        this.cadastro = cadastro;
        this.origem = origem;
        this.destino = destino;
        this.cliente = cliente;
        this.motorista = motorista;
        this.pontos = pontos;
        this.produto = produto;
        this.valor = valor;
        this.kmPercorrido = kmPercorrido;
        this.entregaAberta = entregaAberta;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCadastro() {
        return cadastro;
    }
    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }
    public PontoReferencia getOrigem() {
        return origem;
    }
    public void setOrigem(PontoReferencia origem) {
        this.origem = origem;
    }
    public PontoReferencia getDestino() {
        return destino;
    }
    public void setDestino(PontoReferencia destino) {
        this.destino = destino;
    }
    public Usuario getCliente() {
        return cliente;
    }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
    public Usuario getMotorista() {
        return motorista;
    }
    public void setMotorista(Usuario motorista) {
        this.motorista = motorista;
    }
    public List<PontoReferencia> getPontos() {
        return pontos;
    }
    public void setPontos(List<PontoReferencia> pontos) {
        this.pontos = pontos;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getKmPercorrido() {
        return kmPercorrido;
    }
    public void setKmPercorrido(double kmPercorrido) {
        this.kmPercorrido = kmPercorrido;
    }
    public boolean isEntregaAberta() {
        return entregaAberta;
    }
    public void setEntregaAberta(boolean entregaAberta) {
        this.entregaAberta = entregaAberta;
    }
}