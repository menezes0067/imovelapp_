package org.example.DAO;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.example.exceptions.ProprietarioJaExiste;
import org.example.models.Proprietario;
import org.example.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProprietarioDAO {
    public boolean verificarProprietario(String cpf) throws SQLException, ClassNotFoundException{
        Connection con = MySQLConnection.getConnection();
        String script = "SELECT cpf FROM proprietario WHERE cpf = ?";
        PreparedStatement prepareStatement = con.prepareStatement(script);
        prepareStatement.setString(1, cpf);
        ResultSet rs = prepareStatement.executeQuery();
        return rs.next();
    }

    public void criarProprietario(Proprietario proprietario) throws SQLException, ClassNotFoundException, ProprietarioJaExiste {
        Connection con = MySQLConnection.getConnection();

        if(verificarProprietario(proprietario.getCpf())) {
            throw new ProprietarioJaExiste("Cpf já utilizado");
        }

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

    public List<Proprietario> exibirProprietarios() throws SQLException, ClassNotFoundException {
        Connection con = MySQLConnection.getConnection();
        List<Proprietario> proprietarios = new ArrayList<>();
        String script = "SELECT * FROM proprietario";
        PreparedStatement preparedStatement = con.prepareStatement(script);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()) {
            Proprietario proprietario = new Proprietario();
            proprietario.setId(rs.getInt("id"));
            proprietario.setNome(rs.getString("nome"));
            proprietario.setIdade(rs.getInt("idade"));
            proprietario.setCpf(rs.getString("cpf"));
            proprietario.setEmail(rs.getString("email"));
            proprietario.setTelefone(rs.getString("telefone"));
            proprietarios.add(proprietario);
        }

        return proprietarios;
    }

    public void atualizarProprietario(Proprietario proprietario) throws SQLException, ClassNotFoundException {
        Connection con = MySQLConnection.getConnection();
        String script = "UPDATE proprietario set idade = ?, email = ?, telefone = ? WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(script);
        preparedStatement.setInt(1, proprietario.getIdade());
        preparedStatement.setString(2, proprietario.getEmail());
        preparedStatement.setString(3, proprietario.getTelefone());
        preparedStatement.setInt(4, proprietario.getId());
        preparedStatement.executeUpdate();
        con.close();
    }

    public boolean deletarProprietario(int id) throws SQLException, ClassNotFoundException{
            Connection con = MySQLConnection.getConnection();
            String script = "DELETE FROM proprietario WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(script);
            preparedStatement.setInt(1, id);
            int linha = preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
            return linha > 0;
    }

}
