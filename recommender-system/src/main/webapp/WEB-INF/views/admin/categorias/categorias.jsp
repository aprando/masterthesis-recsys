<div class="jumbotron">
	<h1>Categorias</h1>
	<p>
		<a href="${pageContext.request.contextPath}/admin/categorias/importar-best-buy" 
			class="btn btn-primary btn-lg" 
			role="button">Importar Categorias da BestBuy</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/admin/categorias/listar"
			 class="btn btn-primary btn-lg" 
			 role="button">Listar Categorias</a>
	</p>
	
</div>

<content tag="local_script">
<script>
  $(function() {
		$( "li" ).removeClass( "active" )
		$( "#li_categorias" ).addClass( "active" );
  });
</script>
</content>