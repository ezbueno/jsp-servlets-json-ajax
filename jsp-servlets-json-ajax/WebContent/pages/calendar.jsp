<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendário</title>

<link href='../scripts/fullcalendar.min.css' rel='stylesheet' />
<link href='../scripts/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src="../scripts/jquery-3.5.1.min.js"></script>
<script src='../scripts/moment.min.js'></script>
<script src='../scripts/jquery.min.js'></script>
<script src='../scripts/fullcalendar.min.js'></script>
<script src="../scripts/jquery.mask.js"></script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
</head>
<body>
	<h1>Calendário</h1>
		
	<form action="buscarCalendarioDatas" method="post">
		<table>
			<tr>
				<td>Descrição:</td>
				<td><input type="text" id="descricao" name="descricao"/></td>
				<th/>
				<td>Data:</td>
				<td><input type="text" id="data" name="data"/></td>
				<td><input type="submit" id="enviar" value="Enviar"/></td>
			</tr>
		</table>
	</form>
	<br/>
	
	<div id='calendar'></div>
	
<script type="text/javascript">
$(document).ready(function() {
	
	$.get( "buscarCalendarioDatas", function(response) { // Início do Ajax
		
		var datas = JSON.parse(response);
				
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,basicWeek,basicDay'
			},
			defaultDate: '2017-02-12',
			navLinks: true, // can click day/week names to navigate views
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events:
				datas
		});
	});	// Fim do Ajax
});
</script>

<script type="text/javascript">
	function validarCampos() {
		var descricao = document.getElementById("descricao").value;
		var data = document.getElementById("data").value;
		
		if (descricao == "" && data == "") {
			alert("Os campos descrição e data não podem estar em branco! ");
			return false;
		} else if (descricao == "" && data != "") {
			alert("O campo descrição não pode estar em branco!");
			return false;
		} else if (descricao != "" && data == "") {
			alert("O campo data não pode estar em branco!");
			return false;
		}
		return true;
	}
</script>

<script>
	$(document).ready(function(){
		$('#data').mask('00/00/0000');
	});
</script>
</body>
</html>