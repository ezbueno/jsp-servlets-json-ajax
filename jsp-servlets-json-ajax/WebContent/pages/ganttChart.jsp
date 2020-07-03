<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="../scriptsGanttChart/jquery-ui-1.8.4.css"/>
<link rel="stylesheet" type="text/css" href="../scriptsGanttChart/reset.css"/>
<link rel="stylesheet" type="text/css" href="../scriptsGanttChart/jquery.ganttView.css"/>

<script type="text/javascript" src="../scriptsGanttChart/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../scriptsGanttChart/date.js"></script>
<script type="text/javascript" src="../scriptsGanttChart/jquery-ui-1.8.4.js"></script>
<script type="text/javascript" src="../scriptsGanttChart/jquery.ganttView.js"></script>

<style type="text/css">
	body {
		font-family: tahoma, verdana, helvetica;
		font-size: 0.8em;
		padding: 10px;
	}
</style>

<title>Gantt Chart</title>
</head>
<body>
	<h1>Gantt Chart</h1>
	<br/>
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
			
<script type="text/javascript">
	
$(document).ready(function() {
	
	$.get( "buscarDatasPlanejamento", function(response) {
		
		//var ganttData = JSON.parse(response);
		
		var ganttData = [
							{
								id: 2, name: "Projeto Java Web", series: [
									{ name: "Planejado", start: new Date(2020,00,05), end: new Date(2020,00,20) },
									{ name: "Atual", start: new Date(2020,00,06), end: new Date(2020,00,17), color: "#f0f0f0" },
									{ name: "Projetado", start: new Date(2020,00,06), end: new Date(2020,00,17), color: "#e0e0e0" }
								]
							}
						];
	
		$("#ganttChart").ganttView({ 
			data: ganttData,
			slideWidth: 1100,
			behavior: {
				onClick: function (data) { 
					var msg = "Você clicou sobre um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
					$("#eventMessage").text(msg);
				},
				onResize: function (data) { 
					var msg = "Você redimensionou um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
					$("#eventMessage").text(msg);
				},
				onDrag: function (data) { 
					var msg = "Você arrastou um evento: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
					$("#eventMessage").text(msg);
				}
			}
		});
	});
});
</script>
</body>
</html>