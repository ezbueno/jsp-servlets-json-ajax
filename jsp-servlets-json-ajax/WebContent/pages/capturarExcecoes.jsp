<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Capturando Exceções com JQuery</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>Capturando Exceções com JQuery</h3>
	<input type="text" value="valor informado" id="txtValor" />
	<input type="button" value="Testar Exceção" onclick="testarExcecao()" />
	<br>
	<br>
	<a href="../index.jsp">Voltar</a>

	<script type="text/javascript">
		function testarExcecao() {
			valorInformado = $('#txtValor').val();
			
			$.ajax({
				method: "POST",
				url: "capturarExcecao", // para qual servlet?
				data: {valorParam: valorInformado}
			}).done(function(response) { // resposta ok, nenhum erro
				alert(response);
			}).fail(function(xhr, status, errorThrown) { // resposta falhou - algum problema ocorreu
				alert(xhr.responseText); // exibe a resposta do servidor
			});
		}		
</script>
</body>
</html>