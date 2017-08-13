<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div id="contenedor">
		<jsp:include page="Carreras/Formulario.jsp"></jsp:include>
	</div>
	<a id="mostraC">Mostrar Carreras</a>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$("#mostraC").click(function(e){
		$.ajax({
			url:'CarrerasController',
			type:'GET',
			beforeSend:function(e){
				
			},
			success:function(e){
				$("#contenedor").html(e);
			}
		});
	});
</script>
</html>