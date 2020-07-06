package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Projeto;
import beans.Series;
import dao.DaoGanttChart;

@WebServlet("/pages/buscarDatasPlanejamento")
public class BuscarDatasPlanejamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoGanttChart daoGanttChart = new DaoGanttChart();

	public BuscarDatasPlanejamento() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			List<Projeto> projetos = daoGanttChart.getProjetos();

			if (!projetos.isEmpty()) {
				String grantJson = new Gson().toJson(projetos);

				response.setStatus(200);
				response.getWriter().write(grantJson);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		 // Início do teste de impressão no console para checar se as informações do request estão sendo recebidas
		System.out.println(request.getParameter("start"));
		System.out.println(request.getParameter("end"));
		System.out.println(request.getParameter("serie"));
		System.out.println(request.getParameter("projeto"));
		// Fim do teste de impressão

		try {
			Series seriesUpdate = new Series();

			seriesUpdate.setId(Long.parseLong(request.getParameter("serie")));
			seriesUpdate.setProjeto(Long.parseLong(request.getParameter("projeto")));
			seriesUpdate.setDatainicial(request.getParameter("start"));
			seriesUpdate.setDatafinal(request.getParameter("end"));

			daoGanttChart.atualizar(seriesUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
