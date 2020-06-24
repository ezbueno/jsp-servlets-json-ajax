package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnection;

public class DaoCalculaDataFinal {
	
	private Connection connection;
	
	public DaoCalculaDataFinal() {
		connection = SingleConnection.getConnection();
	}
	
	public void gravarDataFinal(String date) throws Exception {
		String sql = "insert into calculardatafinal(datafinal) values(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, date);
		statement.execute();
		connection.commit();
	}
}
