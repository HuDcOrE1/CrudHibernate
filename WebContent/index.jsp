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
		llamarAjaxObtenerCarreras();
	});
	
	function eliminar(id){
		if(confirm('Estas seguro que deseas realizar la eliminacion?')){
			$.ajax({
				url:'CarrerasController',
				type:'POST',
				data:{
					'id':id,
					'operacion':'eliminar'
				},
				beforeSend:function(e){
					
				},
				success:function(e){
					llamarAjaxObtenerCarreras();
				}
			});
		}
	}
	function editar(id){
		$.ajax({
			url:'CarrerasController',
			type:'POST',
			data:{
				'id':id,
				'operacion':'formulario'
			},
			beforeSend:function(e){
				
			},
			success:function(e){
				$("#contenedor").html(e);
			}
		});
	}
	function editar2(){
	
		var idCarrera = $("#idCarrera").val();
		var nombreCarrera = $("#nombreCarrera").val();
		var creditosCarrera = $("#creditosCarrera").val();
		
		$.ajax({
			url:'CarrerasController',
			type:'POST',
			data:{
				'id':idCarrera,
				'nombre':nombreCarrera,
				'creditos':creditosCarrera,
				'operacion':'editar'
			},
			beforeSend:function(e){
				
			},
			success:function(e){
				llamarAjaxObtenerCarreras();
			}
		});
		
		return false;
	}
	function llamarAjaxObtenerCarreras(){
		$.ajax({
			url:'CarrerasController',
			type:'GET',
			beforeSend:function(e){
				
			},
			success:function(e){
				$("#contenedor").html(e);
			}
		});
	}
</script>
</html>