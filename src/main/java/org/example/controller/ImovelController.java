package org.example.controller;

import io.javalin.http.Context;
import org.example.DAO.ImovelDAO;
import org.example.models.Imovel;

import java.sql.SQLException;

public class ImovelController {
    private final ImovelDAO imovelDAO = new ImovelDAO();

    public void criarImovel(Context ctx) {
        try {
            Imovel imovel = ctx.bodyAsClass(Imovel.class);
            int idProprietario = imovel.getProprietario().getId();
            imovelDAO.criarImovel(imovel, idProprietario);
            ctx.status(201).result("Imovel criado com sucesso!");
        } catch (SQLException ex) {
            ctx.status(500).result(ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
