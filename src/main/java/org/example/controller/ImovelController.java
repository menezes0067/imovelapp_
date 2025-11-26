package org.example.controller;

import io.javalin.http.Context;
import org.example.DAO.ImovelDAO;
import org.example.models.Imovel;
import org.example.models.Proprietario;

import java.sql.SQLException;
import java.util.List;

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

    public void exibirImovel(Context ctx) {
        try {
            List<Imovel> imoveis = imovelDAO.exibirImovel();
            ctx.json(imoveis);
        } catch (SQLException ex) {
            ctx.status(500).result(ex.getMessage());
        } catch(ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
        }
    }

    public void atualizarImovel(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            Imovel imovel = ctx.bodyAsClass(Imovel.class);
            imovel.setId(id);
            imovelDAO.atualizarImovel(imovel);
            ctx.status(200).result("Informações atualizadas com sucesso");
        } catch (SQLException | ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
        }
    }

    public void deletarImovel(Context ctx) {
        try {
            int id = ctx.pathParamAsClass("id", Integer.class).get();

            if (!imovelDAO.deletarImovel(id)) {
                ctx.status(404).result("imovel n encontrado");
            } else {
                ctx.status(200).result("Usuário deletado com sucesso!");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ctx.status(500).result(ex.getMessage());
        }
    }
}
