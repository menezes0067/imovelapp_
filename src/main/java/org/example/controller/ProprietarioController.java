package org.example.controller;

import io.javalin.http.Context;
import org.example.DAO.ProprietarioDAO;
import org.example.models.Proprietario;

import java.sql.SQLException;

public class ProprietarioController {
    private final ProprietarioDAO proprietarioDAO = new ProprietarioDAO();

    public void criarProprietario(Context ctx) {
        try {
            Proprietario proprietario = ctx.bodyAsClass(Proprietario.class);
            proprietarioDAO.criarProprietario(proprietario);
            ctx.status(201).result("Proprietario criado com sucesso");
        } catch(SQLException ex) {

            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
