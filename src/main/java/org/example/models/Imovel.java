package org.example.models;

import java.util.Date;
import java.util.UUID;

public abstract class Imovel {
    protected UUID id;
    protected String nome;
    protected String descricao;
    protected String tipo;
    protected Date anoDeConstrucao;
    protected boolean disponivel;
    protected Proprietario proprietario;

    public Imovel() {}

    public Imovel(UUID id, String nome, String descricao, String tipo,
                  Date anoDeConstrucao, boolean disponivel, Proprietario proprietario) {
    this.id = UUID.randomUUID();
    this.nome = nome;
    this.descricao = descricao;
    this.tipo = tipo;
    this.anoDeConstrucao = anoDeConstrucao;
    this.disponivel = disponivel;
    this.proprietario = proprietario;
    }

    public UUID getId() {
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

    public void setanoDeConstrucao(Date anoDeConstrucao) {
        this.anoDeConstrucao = anoDeConstrucao;
    }

    public Date getAnoDeConstrucao() {
        return anoDeConstrucao;
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