package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import br.com.config.BDConfig;
import br.com.rest.entidade.Notas;

public class NotasDao {
	public List<Notas> listarNotas() throws Exception {
        List<Notas> lista = new ArrayList<>();
 
        Connection conexao = BDConfig.getConnection();
 
        String sql = "SELECT * FROM notas";
 
        PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
 
        while (rs.next()) {
            Notas nota = new Notas();
            nota.setId(rs.getInt("id"));
            nota.setTitulo(rs.getString("titulo"));
            nota.setDescricao(rs.getString("descricao"));
 
            lista.add(nota);
        }
 
        return lista;
}
	public Notas buscarNotaPorId(int idNota) throws Exception {
        Notas nota = null;
 
        Connection conexao = BDConfig.getConnection();
 
        String sql = "SELECT * FROM notas WHERE id = ?";
 
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idNota);
        ResultSet rs = statement.executeQuery();
 
        if (rs.next()) {
            nota = new Notas();
            nota.setId(rs.getInt("id"));
            nota.setTitulo(rs.getString("titulo"));
            nota.setDescricao(rs.getString("descricao"));
        }
 
        return nota;
    }
	public int addNota(Notas nota) throws Exception {
        int idGerado = 0;
        Connection conexao = BDConfig.getConnection();
 
        String sql = "INSERT INTO notas(titulo, descricao) VALUES(?, ?)";
 
        PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, nota.getTitulo());
        statement.setString(2, nota.getDescricao());
        statement.execute();
         
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            idGerado = rs.getInt(1);
        }
         
        return idGerado;
    }
     
    public void editarNota(Notas nota, int idNota) throws Exception {
        Connection conexao = BDConfig.getConnection();
 
        String sql = "UPDATE notas SET titulo = ?, descricao = ? WHERE id = ?";
 
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, nota.getTitulo());
        statement.setString(2, nota.getDescricao());
        statement.setInt(3, idNota);
        statement.execute();
    }
     
    public void removerNota(int idNota) throws Exception {
        Connection conexao = BDConfig.getConnection();
 
        String sql = "DELETE FROM notas WHERE id = ?";
 
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setInt(1, idNota);
        statement.execute();
    }
}