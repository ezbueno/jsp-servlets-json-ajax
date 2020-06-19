<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página Pai</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h1>Página Pai - Load com JQuery</h1>
	<table>
		<tr>
			<td><input type="button" id="carregar" name="carregar"
				value="Carregar Página" onclick="carregarPagina()" /><br>
			<br></td>
		</tr>
	</table>
	<div id="carregarPaginaFilha"></div>
	<!--  Local de carregamento da página Filha -->
	<a href="../index.jsp">Voltar</a>

	<script type="text/javascript">
	function carregarPagina() {
		$("#carregarPaginaFilha").load('paginaFilha.jsp');
	}
</script>
</body>
</html>