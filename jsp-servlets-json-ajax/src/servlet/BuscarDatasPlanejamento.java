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
import beans.Serie;
import dao.DaoGanttChart;

@WebServlet("/pages/buscarDatasPlanejamento")
public class BuscarDatasPlanejamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoGanttChart daoGanttChart = new DaoGanttChart();

    public BuscarDatasPlanejamento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Projeto> projetos = daoGanttChart.getProjetos();
			
			if (!projetos.isEmpty()) {
				
				String granttJson = new Gson().toJson(projetos);
				
				response.setStatus(200);
				response.getWriter().write(granttJson);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Serie serieUpdate = new Serie();
			serieUpdate.setId(Long.parseLong(request.getParameter("serie")));
			serieUpdate.setProjeto(Long.parseLong(request.getParameter("projeto")));
			serieUpdate.setDatainicial(request.getParameter("start"));
			serieUpdate.setDatafinal(request.getParameter("end"));
			
			daoGanttChart.atualizar(serieUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
