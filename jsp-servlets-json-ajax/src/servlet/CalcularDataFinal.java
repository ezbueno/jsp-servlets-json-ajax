package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;

@WebServlet("/pages/calcularDataFinal")
public class CalcularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoCalculaDataFinal daoCalculaDataFinal = new DaoCalculaDataFinal();

    public CalcularDataFinal() {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 08h às 12h e 13h30 às 17h30 */
		/* 1 dia é igual a 8h */
		
		try {
			int horaDia = 8;
			Date dataCalculada = null;
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			Double totalDias = 0.0;
			
			if (tempo <= horaDia) {
				Date dataInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar calendario = Calendar.getInstance();	
				calendario.setTime(dataInformada);
				calendario.add(Calendar.DATE, 1);
				
				dataCalculada = calendario.getTime();
				totalDias = 1.0;
			} else {
				totalDias = (double) (tempo / horaDia);
				
				if (totalDias <= 1) {
					dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} else {
					Date dataInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
					Calendar calendario = Calendar.getInstance();	
					calendario.setTime(dataInformada);
					calendario.add(Calendar.DATE, totalDias.intValue());
					
					dataCalculada = calendario.getTime();
				}
			}
			daoCalculaDataFinal.gravarDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/datas.jsp");
			request.setAttribute("dataFinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			request.setAttribute("dias", totalDias);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
