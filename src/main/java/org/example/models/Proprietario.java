package org.example.models;

import java.util.UUID;

public class Proprietario {
    private int id;
    private String nome;
    private int idade;
    private String cpf;
    private String email;
    private String telefone;

    public Proprietario(){}

    public Proprietario(int id, String nome, int idade, String cpf, String email, String telefone){
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

}
