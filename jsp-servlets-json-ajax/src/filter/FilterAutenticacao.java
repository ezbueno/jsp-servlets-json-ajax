package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Usuario;

@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter {
	
	// faz alguma coisa quando a aplica��o � derrubada
	@Override
	public void destroy() {
		Filter.super.destroy();
	}
	
	// intercepta todas as requisi��es
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpServletRequest.getSession();
		
		String urlAutenticacao = httpServletRequest.getServletPath();
		
		// retorna null caso o usu�rio n�o esteja logado
		Usuario usuarioLogado =  (Usuario) httpSession.getAttribute("usuario");
		
		if (usuarioLogado == null && !urlAutenticacao.equalsIgnoreCase("/pages/Autenticacao")) { // usu�rio n�o logado
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url=" + urlAutenticacao);
			dispatcher.forward(request, response);
			return; // retorna para o processo para ser feito o redirecionamento
		}
		
		// executa as a��es do request e response
		chain.doFilter(request, response);	
	}
	
	// executa alguma coisa quando a aplica��o � iniciada
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
