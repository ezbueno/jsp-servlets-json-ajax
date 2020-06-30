package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Evento;
import dao.DaoEvento;

@WebServlet("/pages/buscarCalendarioDatas")
public class BuscarCalendarioDatas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoEvento daoEvento = new DaoEvento();

    public BuscarCalendarioDatas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Evento> eventos = daoEvento.listarEventos();
			
			int totalEvento = eventos.size();
			int cont = 1;
			
			if (!eventos.isEmpty()) {
				String datas = "[";
				
				for (Evento ev : eventos) {
					datas += "{\"title\": \""+ ev.getDescricao() +"\", \"start\": \""+ ev.getDataevento() +"\"}";
					
					if (cont < totalEvento) {
						datas += ",";
					}
					cont++;
				}
				
				datas += "]";
				
				response.setStatus(200);
				response.getWriter().write(datas);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String desc = request.getParameter("descricao");
		String dataEvento = request.getParameter("data");

		String inputText;
		String outputText;
		Evento evento = new Evento();
		Date date;
		
		if (desc != null && dataEvento != null || !desc.isEmpty() && !dataEvento.isEmpty()) {
			evento.setDescricao(desc);
						
			DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat outFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
			
			inputText = dataEvento;
			
			try {
				 date = inputFormat.parse(inputText);
				 outputText = outFormat.format(date);
				 evento.setDataevento(outputText);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			daoEvento.inserirEvento(evento);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/calendar.jsp");
		dispatcher.forward(request, response);
	}

}
