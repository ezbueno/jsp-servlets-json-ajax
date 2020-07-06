<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gantt Chart</title>

<link rel="stylesheet" type="text/css"
	href="../scriptsGanttChart/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptsGanttChart/reset.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptsGanttChart/jquery.ganttView.css" />

<script type="text/javascript" src="../scriptsGanttChart/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../scriptsGanttChart/date.js"></script>
<script type="text/javascript"
	src="../scriptsGanttChart/jquery-ui-1.8.4.js"></script>
<script type="text/javascript"
	src="../scriptsGanttChart/jquery.ganttView.js"></script>

<style type="text/css">
body {
	font-family: tahoma, verdana, helvetica;
	font-size: 0.8em;
	padding: 10px;
}
</style>

</head>
<body>
	<h1>Gantt Chart</h1>
	
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
</body>


<script type="text/javascript">

$(document).ready(function() {
	
	
		$.get("buscarDatasPlanejamento", function(response) {
			
		// Início do processamento dos dados do Gantt Chart	
			var ganttDataResposta = JSON.parse(response);
		
			var ganttData = "";
			 ganttData += "["; 
			 
				$.each(ganttDataResposta, function(index, projeto) { // início do for dos projetos
						
					ganttData += "{ \"id\": \""+projeto.id+"\", \"name\": \""+projeto.nome+"\", \"series\": [";
					
					$.each(projeto.series, function(idx, serie) { // início do for das series
						
						var cores = "#3366FF,#00CC00".split(',');
					
						var cor;
						if (idx === 0) {
							cor = "#CC33CC";
						}else {
						   cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
						}
						
						var datainicial = serie.datainicial.split('-');
						var datafinal = serie.datafinal.split('-');
						
						ganttData +="{ \"name\": \""+serie.nome+"\", \"start\":\""+ new Date(datainicial[0],datainicial[1],datainicial[2])+"\", \"end\": \""+new Date(datafinal[0],datafinal[1],datafinal[2])+"\" , \"color\": \""+cor+"\", \"projeto\": \""+serie.projeto+"\" , \"serie\": \""+serie.id+"\" }";
						
						if (idx < projeto.series.length - 1){
							ganttData +=",";
						}
					}); // fim do for das series
					
				    ganttData +="]}"; // fecha o array JSON das series
				 
				   if (index < ganttDataResposta.length - 1){
					   ganttData +=",";
				   }
					
				}); // fim do for dos projetos
			
			 ganttData += "]";
			
			 ganttData = JSON.parse(ganttData);
			 
			// Fim do processamento dos dados do Gantt Chart

			 $("#ganttChart").ganttView({ 
						data: ganttData,
						slideWidth: 600,
						behavior: {
							onClick: function (data) { 
								var msg = "Você clicou sobre um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
							},
							onResize: function (data) { 
								
								var start = data.start.toString("yyyy-M-d");
								var end = 	data.end.toString("yyyy-M-d");
								$.post("buscarDatasPlanejamento", { start: start, end : end, serie : data.serie, projeto : data.projeto });
								
								var msg = "Você redimensionou um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
								
							},
							onDrag: function (data) { 
								
								var start = data.start.toString("yyyy-M-d");
								var end = 	data.end.toString("yyyy-M-d");
								$.post("buscarDatasPlanejamento", { start: start, end : end, serie : data.serie, projeto : data.projeto });
								
								var msg = "Você arrastou um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
								
							}
						}
					});
        });	
});
</script>
</html>