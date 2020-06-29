<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendário</title>

<link href='../scripts/fullcalendar.min.css' rel='stylesheet' />
<link href='../scripts/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='../scripts/moment.min.js'></script>
<script src='../scripts/jquery.min.js'></script>
<script src='../scripts/fullcalendar.min.js'></script>

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
	<div id='calendar'></div>
	
<script type="text/javascript">
$(document).ready(function() {
	
	$.get( "buscarCalendarioDatas", function(response) { // Início do Ajax
		
		var datas = JSON.parse(response);
		
		//datas = {title: 'All Day Event', start: '2017-02-01'};
		
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
</body>
</html>