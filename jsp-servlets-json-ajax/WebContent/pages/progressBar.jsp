<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Barra de Progresso</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
#myProgress {
	position: relative;
	width: 100%;
	height: 30px;
	background-color: #C0C0C0;
	/* Cor cinza do fundo da barra de progresso */
}

#myBar {
	position: absolute;
	width: 0;
	height: 100%;
	background-color: #4CAF50; /* Cor verde da barra de progresso */
}

#label {
	text-align: center;
	line-height: 30px;
	color: white;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: absolute;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px 0 #fff;
}
</style>
</head>
<body>
	<h3>Barra de Progresso com JavaScript</h3>
	<div id="myProgress">
		<div id="myBar">
			<div id="label">0%</div>
		</div>
	</div>
	<br>
	<button onclick="iniciarBarra()">Iniciar Barra de Progresso</button>
	<br>
	<br>
	<h3>Barra de Progresso com JQuery</h3>
	<div class="ui-progressbar">
		<div id="progressbar">
			<div class="progress-label">Aguarde...</div>
		</div>
	</div>
	<br>
	<br>
	<a href="../index.jsp">Voltar</a>

	<script type="text/javascript">
		//Função da Barra de Progresso com JQuery
		$(function() {
			var progressbar = $("#progressbar"), progressLabel = $(".progress-label");

			progressbar.progressbar({
				value : false,
				change : function() {
					progressLabel.text(progressbar.progressbar("value") + "%");
				},
				complete : function() {
					progressLabel.text("Carregado");
				}
			});

			function progress() {
				var val = progressbar.progressbar("value") || 0;

				progressbar.progressbar("value", val + 1);

				if (val < 99) {
					setTimeout(progress, 100);
				}
			}

			setTimeout(progress, 3000);
		});

		// Função da Barra de Progresso com JavaScript
		function iniciarBarra() {
			var barra = document.getElementById("myBar");
			var width = 1;
			var id = setInterval(frame, 100);

			function frame() {
				if (width >= 100) {
					clearInterval(id);
				} else {
					width++;
					barra.style.width = width + "%";
					document.getElementById("label").innerHTML = width * 1 + '%';
				}
			}
		}
	</script>
</body>
</html>