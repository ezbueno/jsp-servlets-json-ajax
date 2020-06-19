package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.DaoUsuario;
import servicos.Relatorio;

@WebServlet("/pages/DownloadArquivo")
public class DownloadArquivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Relatorio relatorio = new Relatorio();
	private DaoUsuario daoUsuario = new DaoUsuario();

	public DownloadArquivo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tipoExportado = request.getParameter("tipoExportado");
		ServletContext context = request.getServletContext();

		try {
			List<Usuario> usuarios = daoUsuario.listarUsuarios();

			String fileURL = relatorio.gerarRelatorio(usuarios, new HashMap(), "relatorio_usuario", "relatorio_usuario", context);
			
			// constrói o caminho completo e absoluto do arquivo
			File downloadArquivo = new File(fileURL);
			FileInputStream fileInputStream = new FileInputStream(downloadArquivo);
			
			// obtém o tipo MIME do arquivo
			String tipoMime = context.getMimeType(fileURL);
			
			// define com tipo binário se o tipo MIME não for encontrado
			if (tipoMime == null) {
				tipoMime = "application/octet-stream";
			}
			
			// define atributos para resposta
			response.setContentType(tipoMime);
			response.setContentLength((int) downloadArquivo.length());
			
			// define os cabeçalhos para a resposta
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadArquivo.getName());			
			response.setHeader(headerKey, headerValue);
			
			// obtém o fluxo de saída da resposta
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[4096];
			int bytesLidos = -1;
			
			// escreve os bytes lidos a partir do fluxo de entrada para o fluxo de saída
			while ((bytesLidos = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesLidos);
			}
			
			fileInputStream.close();
			outputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
