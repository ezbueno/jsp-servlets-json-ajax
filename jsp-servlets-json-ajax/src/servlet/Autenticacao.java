package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Usuario;
import dao.DaoLogin;

@WebServlet("/pages/Autenticacao")
public class Autenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoLogin daoLogin = new DaoLogin();

    public Autenticacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (Boolean.parseBoolean(request.getParameter("logout"))) {
			HttpServletRequest httpServletRequest = request;
			HttpSession httpSession = httpServletRequest.getSession();
			httpSession.invalidate();
			
			// redireciona para a tela de login novamente
			response.sendRedirect("../index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
					
			try {
				if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
					if (daoLogin.validarLogin(login, senha)) {
						
						Usuario usuario = new Usuario();
						
						// adiciona usuário logado na sessão
						HttpServletRequest httpServletRequest = request;
						HttpSession httpSession = httpServletRequest.getSession();
						httpSession.setAttribute("usuario", usuario);		
						
						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
						request.setAttribute("msg", "Login e/ou Senha inválidos!");
						dispatcher.forward(request, response);
					}
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
					request.setAttribute("msg", "Favor informar o Login e/ou a Senha!");
					dispatcher.forward(request, response);
				}								
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
