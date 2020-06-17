package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.DaoUsuario;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();

    public CarregarDadosDataTable() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Usuario> usuarios = daoUsuario.listarUsuarios();
			
			if (!usuarios.isEmpty()) {
				
				String data = "";
				int totalUsuarios = usuarios.size();
				int contador = 1;
				
				for (Usuario usuario : usuarios) {
						data +=	"["+
							      "\" " + usuario.getId() + "\", " +
							      "\" " + usuario.getLogin() + "\" " +
							    "]";
						if (contador < totalUsuarios) {
							data += ",";
						}
						contador++;
				}
				
				String json = "{" +
								  "\"draw\": 1, " +
								  "\"recordsTotal\": " + usuarios.size() + ", "	+
								  "\"recordsFiltered\": " + usuarios.size() + ", "	+
								  "\"data\": [" + data + "]" + // fechamento do data
							  "}"; // fechamento do JSON
				
				response.setStatus(200);
				response.getWriter().write(json);
			}


		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
