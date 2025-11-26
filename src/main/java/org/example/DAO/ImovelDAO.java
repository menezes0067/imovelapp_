package org.example.DAO;


import org.example.models.Imovel;
import org.example.models.Proprietario;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {
   public void criarImovel(Imovel imovel, int proprietarioId) throws SQLException, ClassNotFoundException {
        Connection con = MySQLConnection.getConnection();
        String script = "INSERT INTO imovel(" +
                "nome, " +
                "descricao, " +
                "tipo, " +
                "disponivel, " +
                "proprietarioId) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement PrepareStatement = con.prepareStatement(script);

        PrepareStatement.setString(1, imovel.getNome());
        PrepareStatement.setString(2, imovel.getDescricao());
        PrepareStatement.setString(3, imovel.getTipo());
        PrepareStatement.setBoolean(4, imovel.getDisponivel());
        PrepareStatement.setInt(5, proprietarioId);
        PrepareStatement.executeUpdate();
        PrepareStatement.close();
        con.close();
   }

   public List<Imovel> exibirImovel() throws SQLException, ClassNotFoundException {
       Connection con = MySQLConnection.getConnection();
       List<Imovel> imoveis = new ArrayList<>();
       String script = "SELECT i.*, p.id, p.nome AS proprietario_nome FROM imovel i INNER JOIN proprietario p ON p.id = i.proprietarioId";
       PreparedStatement preparedStatement = con.prepareStatement(script);
       ResultSet rs = preparedStatement.executeQuery();

       while(rs.next()){
           Imovel imovel = new Imovel();
           Proprietario p = new Proprietario();
           p.setId(rs.getInt("proprietarioId"));
           p.setNome(rs.getString("proprietario_nome"));

           imovel.setId(rs.getInt("id"));
           imovel.setNome(rs.getString("nome"));
           imovel.setDescricao(rs.getString("descricao"));
           imovel.setTipo(rs.getString("tipo"));
           imovel.setDisponivel(rs.getBoolean("disponivel"));
           imovel.setProprietario(p);

           imoveis.add(imovel);
       }

       return imoveis;
   }

   public void atualizarImovel(Imovel imovel) throws SQLException, ClassNotFoundException {
       Connection con = MySQLConnection.getConnection();
       String script = "UPDATE imovel SET descricao = ?, disponivel = ? WHERE id = ?";
       PreparedStatement PrepareStatement = con.prepareStatement(script);
       PrepareStatement.setString(1, imovel.getDescricao());
       PrepareStatement.setBoolean(2, imovel.getDisponivel());
       PrepareStatement.setInt(3,imovel.getId());
       PrepareStatement.executeUpdate();
       PrepareStatement.close();
       con.close();
   }

   public boolean deletarImovel(int id ) throws SQLException, ClassNotFoundException {
       Connection con = MySQLConnection.getConnection();
       String script = "DELETE FROM imovel WHERE id = ?";
       PreparedStatement preparedStatement = con.prepareStatement(script);
       preparedStatement.setInt(1, id);
       int linha = preparedStatement.executeUpdate();
       preparedStatement.close();
       con.close();
       return linha > 0;
   }
}
