package org.example.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Imovel {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private boolean disponivel;
    private Proprietario proprietario;

    public Imovel() {}

    public Imovel(int id, String nome, String descricao, String tipo,
                  boolean disponivel, Proprietario proprietario) {
    this.nome = nome;
    this.descricao = descricao;
    this.tipo = tipo;
    this.disponivel = disponivel;
    this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean getDisponivel() {
        return  disponivel;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

}