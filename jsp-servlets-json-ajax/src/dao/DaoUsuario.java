package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Usuario;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public List<Usuario> listarUsuarios () throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			
			usuarios.add(usuario);
		}
		return usuarios;
	}

	public void gravarImagem(String fileUpload) throws SQLException {
		String tipoDados = fileUpload.split(",")[0].split(";")[0].split("/")[1];
		String sql = "insert into usuario (imagem, contenttype) values (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, fileUpload);
		statement.setString(2, tipoDados);
		statement.execute();
		connection.commit();
	}

	public Usuario buscarImagem(String userid) throws SQLException {
		String sql = "select * from usuario where id = " + userid;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			usuario.setContenttype(resultSet.getString("contenttype"));
			
			return usuario;	
		}
		
		return null;
	}
}
