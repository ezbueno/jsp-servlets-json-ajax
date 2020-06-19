<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<img alt="Imagem" src="" id="target" width="200" height="200">
	<br />
	<input type="file" id="file" name="file" onchange="uploadFile()" />
	<br />
	<br />

	<a href="fileUpload?acao=carregar">Carregar Arquivos</a>
	<br />
	<br />

	<table>
		<tr>
			<th>ID</th>
			<th>Usuário</th>
			<th>Arquivo</th>
		</tr>

		<c:forEach items="${listaUserImagem}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>

				<c:if test="${user.imagem != null}">
					<td><a href="fileUpload?acao=download&userid=${user.id}">Download</a></td>
				</c:if>

				<c:if test="${user.imagem == null}">
					<td>
						<% out.print("-------------"); %>
					</td>
				</c:if>

			</tr>
		</c:forEach>
	</table>

	<br />
	<br />

	<a href="../index.jsp">Voltar</a>
	<br />
	<br />

	<script type="text/javascript">
	function uploadFile() {
		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];
		
		var reader = new FileReader();
			reader.onloadend = function () {
				target.src = reader.result;
				
				$.ajax({
					method: "POST",
					url: "fileUpload", // para qual servlet?
					data: {fileUpload: reader.result}
				}).done(function(response) { // resposta ok, nenhum erro
					alert(response);
				}).fail(function(xhr, status, errorThrown) { // resposta falhou - algum problema ocorreu
					alert(xhr.responseText); // exibe a resposta do servidor
				});
			};
		
		if (file) {
			reader.readAsDataURL(file);
		} else {
			target.src = "";
		}
	}
</script>
</body>
</html>