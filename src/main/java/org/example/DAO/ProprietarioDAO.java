package org.example.DAO;

import org.example.models.Proprietario;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProprietarioDAO {
    MySQLConnection mysqlconnection;

    public void criarProprietario(Proprietario proprietario) throws SQLException, ClassNotFoundException {
        Connection con = mysqlconnection.getConnection();
        String script = "INSERT INTO proprietario (" +
                "id, " +
                "nome, " +
                "idade, " +
                "CPF, " +
                "email, " +
                "telefone) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(script);

        preparedStatement.setInt(1, proprietario.getId());
        preparedStatement.setString(2, proprietario.getNome());
        preparedStatement.setInt(3, proprietario.getIdade());
        preparedStatement.setString(4, proprietario.getCpf());
        preparedStatement.setString(5, proprietario.getEmail());
        preparedStatement.setString(6, proprietario.getTelefone());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        con.close();
    }

    public Proprietario exibirProprietariosPorId(Proprietario proprietario) throws SQLException, ClassNotFoundException {
        Connection con = mysqlconnection.getConnection();
        String script = "SELECT * FROM proprietario WHERE id = ? ";
        PreparedStatement preparedStatement = con.prepareStatement(script);
        preparedStatement.setInt(1, proprietario.getId());
        ResultSet rs = preparedStatement.executeQuery();

        return proprietario;
    }
}
