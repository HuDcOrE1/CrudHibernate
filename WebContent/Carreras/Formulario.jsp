<div class="col-md-6 col-md-offset-3">
	<form action="./CarrerasController" method="POST">
	<input type="hidden" name="operacion" value="insertar" />
	<div cass="form-group">
		<input type="text" placeholder="Nombre..." name="nombre" class="form-control" />
	</div>
	<div cass="form-group">
		<input type="number" placeholder="Credito" name="creditos" class="form-control"/>
	</div>
	<input type="submit" value="guardar" />
</form>
</div>