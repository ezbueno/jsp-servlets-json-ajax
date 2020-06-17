<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DataTable JQuery</title>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<table id="dataTable" class="display" style="width:100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
        </thead>
	</table>
<script type="text/javascript">
	$(document).ready(function() {
	    $('#dataTable').DataTable( {
	        "processing": true,
	        "serverSide": false, // alterado para false, para que o campo Search faça a consulta com os dados já mostrados na tela
	        "ajax": "carregarDadosDataTable"
	    } );
	} );
</script>
</body>
</html>