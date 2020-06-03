<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticação</title>
</head>
<body>
	<h1>Autenticar usuário</h1>
	<h3 style="color: red;">${msg}</h3>
	<form action="Autenticacao" method="post">
		<table>
		<tr>
			<td><input type="hidden" id="url" name="url" value="<%= request.getParameter("url") %>" /></td>
		</tr>
		
		<tr>
			<td>Login:</td>
			<td><input type="text" id="login" name="login" /></td>
		</tr>
		
		<tr>
			<td>Senha:</td>
			<td><input type="password" id="senha" name="senha" /></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit" id="acessar" name="acessar" value="Acessar" style="width: 65px" />
				<a href="../index.jsp"><input type="button" value="Voltar" style="width: 65px" /></a>
			</td>		
		</tr>
	</table>
	</form>
</body>
</html>