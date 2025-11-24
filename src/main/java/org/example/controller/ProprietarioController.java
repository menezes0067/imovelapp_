package org.example.controller;

import io.javalin.http.Context;
import org.example.DAO.ProprietarioDAO;
import org.example.exceptions.ProprietarioJaExiste;
import org.example.models.Proprietario;

import java.sql.SQLException;
import java.util.List;

public class ProprietarioController {
    private final ProprietarioDAO proprietarioDAO = new ProprietarioDAO();

    public void criarProprietario(Context ctx) {
        try {
            Proprietario proprietario = ctx.bodyAsClass(Proprietario.class);
            proprietarioDAO.criarProprietario(proprietario);
            ctx.status(201).result("Proprietario criado com sucesso");
        } catch(SQLException ex) {
            ctx.status(500).result(ex.getMessage());
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
            ex.printStackTrace();
        } catch(ProprietarioJaExiste ex) {
            ctx.status(400).result(ex.getMessage());
        }
    }

    public void exibirProprietario(Context ctx) {
        try {
            List<Proprietario> proprietarios = proprietarioDAO.exibirProprietarios();
            ctx.json(proprietarios);
        } catch (SQLException ex) {
            ctx.status(500).result(ex.getMessage());
        } catch(ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
        }
    }

    public void atualizarProprietario(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            Proprietario proprietario = ctx.bodyAsClass(Proprietario.class);
            proprietario.setId(id);
            proprietarioDAO.atualizarProprietario(proprietario);
            ctx.status(200).result("Informações atualizadas com sucesso");
        } catch (SQLException | ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
        }
    }

    public void deletarProprietario(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();

            if (!proprietarioDAO.deletarProprietario(id)) {
                ctx.status(404).result("usuário não encontrado");
            } else {
                ctx.status(200).result("Usuário deletado com sucesso!");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
        }
    }
}
