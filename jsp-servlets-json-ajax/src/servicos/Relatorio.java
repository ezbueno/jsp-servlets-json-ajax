package servicos;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("deprecation")
public class Relatorio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUB_REPORT_DIR = "SUB_REPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;
	
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio, String nomeRelatorioJasper, 
			String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
		
		// Cria a lista de CollectionDataSource para carregar os dados para o relatório
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		// Fornece o caminho físico até a pasta que contêm os relatórios .jasper
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		// caminho para imagens
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		// caminho completo até o relatório compilado
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		// Faz o carregamento do relatório
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
		
		// seta para parametrosRelatorio, o caminho físico para SUB_REPORT_DIR
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUB_REPORT_DIR, caminhoSubReport_Dir);
		
		// Carrega o arquivo
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		exporter = new JRPdfExporter();
		
		// caminho do relatório exportado
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";
		
		// cria novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		// prepara a impressão
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		// executa a exportação
		exporter.exportReport();
		
		// remove o arquivo do servidor após ser realizado o download
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}

}
