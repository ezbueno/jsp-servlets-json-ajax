package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;

public class DaoUpload {

	private Connection connection;
	
	public DaoUpload() {
		connection = SingleConnection.getConnection();
	}

	public void gravarImagem(String fileUpload) throws SQLException {
		String sql = "insert into upload (imagem) values (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, fileUpload);
		statement.execute();
		connection.commit();
	}
}
