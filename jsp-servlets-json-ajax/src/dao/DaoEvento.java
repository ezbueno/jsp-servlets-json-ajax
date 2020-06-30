package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Evento;
import connection.SingleConnection;

public class DaoEvento {
	
	private Connection connection;
	
	public DaoEvento() {
		connection = SingleConnection.getConnection();
	}
	
	public void inserirEvento(Evento evento) {
		try {
			String sql = "insert into evento(descricao, dataevento) values (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, evento.getDescricao());
			statement.setString(2, evento.getDataevento());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<Evento> listarEventos() throws Exception {
		List<Evento> eventos = new ArrayList<>();
		
		String sql = "select * from evento";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Evento evento = new Evento();
			evento.setId(resultSet.getLong("id"));
			evento.setDescricao(resultSet.getString("descricao"));
			evento.setDataevento(resultSet.getString("dataevento"));
			
			eventos.add(evento);
		}
		return eventos;
	}
}
