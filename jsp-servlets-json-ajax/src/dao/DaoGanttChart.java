package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Projeto;
import beans.Serie;
import connection.SingleConnection;

public class DaoGanttChart {

	private Connection connection;

	public DaoGanttChart() {
		connection = SingleConnection.getConnection();
	}

	public List<Projeto> getProjetos() throws Exception {
		List<Projeto> projetos = new ArrayList<>();

		String sqlProjeto = "select * from projeto";
		PreparedStatement preparedStatementProjeto = connection.prepareStatement(sqlProjeto);
		ResultSet resultSetProjeto = preparedStatementProjeto.executeQuery();

		while (resultSetProjeto.next()) {
			Projeto projeto = new Projeto();
			projeto.setId(resultSetProjeto.getLong("id"));
			projeto.setNome(resultSetProjeto.getString("nome"));

			String sqlSerie = "select * from serie where projeto = " + resultSetProjeto.getLong("id");
			PreparedStatement preparedStatementSerie = connection.prepareStatement(sqlSerie);
			ResultSet resultSetSerie = preparedStatementSerie.executeQuery();

			List<Serie> series = new ArrayList<>();

			while (resultSetSerie.next()) {
				Serie serie = new Serie();
				serie.setId(resultSetSerie.getLong("id"));
				serie.setNome(resultSetSerie.getString("nome"));
				serie.setDatainicial(resultSetSerie.getString("datainicial"));
				serie.setDatafinal(resultSetSerie.getString("datafinal"));
				serie.setProjeto(resultSetSerie.getLong("id"));

				series.add(serie);
			}

			projeto.setSeries(series);
			projetos.add(projeto);
		}
		return projetos;
	}

	public void atualizar(Serie serie) throws Exception {
		String sql = "update serie set datainicial = '"
				+ serie.getDatainicial() + "' , datafinal = '"
				+ serie.getDatafinal() + "' " + "where id = " + serie.getId()
				+ " and projeto = " + serie.getProjeto();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
	}
}
