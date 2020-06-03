package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import bean.Usuario;
import dao.DaoUsuario;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public FileUpload() {
        super();        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao == null || acao.isEmpty()) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/upload.jsp");
				dispatcher.forward(request, response);
				
			} else if (acao != null && acao.equalsIgnoreCase("carregar")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/upload.jsp");
				request.setAttribute("listaUserImagem", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
				
			} else if (acao != null && acao.equalsIgnoreCase("download")) {
				
				String userid = request.getParameter("userid");
				Usuario imagem = daoUsuario.buscarImagem(userid);
				
					
				if (imagem != null && imagem.getContenttype().equalsIgnoreCase("pdf")) {
										
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + imagem.getContenttype());
					
					//Pega somente a imagem pura
					String imagemPura = imagem.getImagem().split(",")[1];
					
					new Base64();
					
					//Converte base 64 para bytes
					byte[] imagemBytes = Base64.decodeBase64(imagemPura);
					
					//Coloca os bytes em um objeto de entrada para processar
					InputStream inputStream = new ByteArrayInputStream(imagemBytes);
					
					// Início - Escrever dados da resposta
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream outputStream = response.getOutputStream();
					
					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
										
					outputStream.flush();
					outputStream.close();						
					// Fim - Escrever dados da resposta	
				} else if (imagem != null && imagem.getContenttype().equalsIgnoreCase("jpeg") ||imagem.getContenttype().equalsIgnoreCase("png")) {
					response.setHeader("Content-Disposition", "attachment;filename=imagem." + imagem.getContenttype());
					
					//Pega somente a imagem pura
					String imagemPura = imagem.getImagem().split(",")[1];
					
					new Base64();
					
					//Converte base 64 para bytes
					byte[] imagemBytes = Base64.decodeBase64(imagemPura);
					
					//Coloca os bytes em um objeto de entrada para processar
					InputStream inputStream = new ByteArrayInputStream(imagemBytes);
					
					// Início - Escrever dados da resposta
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream outputStream = response.getOutputStream();
					
					while ((read = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
										
					outputStream.flush();
					outputStream.close();						
					// Fim - Escrever dados da resposta	
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/upload.jsp");
					request.setAttribute("listaUserImagem", daoUsuario.listarUsuarios());
					dispatcher.forward(request, response);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String fileUpload = request.getParameter("fileUpload");
			
			daoUsuario.gravarImagem(fileUpload);
			
			response.getWriter().write("Upload realizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Erro ao tentar fazer o Upload da imagem!");
		}
	}

}
