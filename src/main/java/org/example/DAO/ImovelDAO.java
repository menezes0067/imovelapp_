package org.example.DAO;


import org.example.models.Imovel;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImovelDAO {
   public void criarImovel(Imovel imovel) throws SQLException, ClassNotFoundException {
        Connection con = MySQLConnection.getConnection();
        String script = "INSERT INTO imovel(" +
                "nome, " +
                "descricao, " +
                "tipo, " +
                "anodeConstrucao, " +
                "disponivel, " +
                "proprietarioId) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement PrepareStatement = con.prepareStatement(script);

        PrepareStatement.setString(1, imovel.getNome());
        PrepareStatement.setString(2, imovel.getDescricao());
        PrepareStatement.setString(3, imovel.getTipo());
        PrepareStatement.setDate(4, new java.sql.Date(imovel.getAnoDeConstrucao().getTime()));
        PrepareStatement.setBoolean(5, imovel.getDisponivel());
        PrepareStatement.setInt(6, imovel.getProprietario().getId());
        PrepareStatement.executeQuery();
        PrepareStatement.close();
        con.close();
   }

}
