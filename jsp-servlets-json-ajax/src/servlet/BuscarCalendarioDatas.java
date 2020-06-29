package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/buscarCalendarioDatas")
public class BuscarCalendarioDatas extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarCalendarioDatas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String datas = "[{\"title\": \"All Day Event\", \"start\": \"2017-02-01\"}, {\"title\": \"Java Events\", \"start\": \"2017-02-02\"}]";
		
		response.setStatus(200);
		response.getWriter().write(datas);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
