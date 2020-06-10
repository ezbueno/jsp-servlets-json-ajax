package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CarregarDadosDataTable() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "{"+
				  "\"draw\": 1, "+
				  "\"recordsTotal\": 57, "+
				  "\"recordsFiltered\": 57, "+
				  "\"data\": ["+
				    "["+
				      "\"Airi\", "+
				      "\"Satou\", "+
				      "\"Accountant\", "+
				      "\"Tokyo\", "+
				      "\"28th Nov 08\", "+
				      "\"$162.700\" "+
				    "],"+
				    "["+
				      "\"Cedric\", "+
				      "\"Kelly\", "+
				      "\"Senior Javascript Developer\", "+
				      "\"Edinburgh\", "+
				      "\"29th Mar 12\", "+
				      "\"$433.060\" "+
				    "]"+
				   "]"+
				"}";
		
		
		response.setStatus(200);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
