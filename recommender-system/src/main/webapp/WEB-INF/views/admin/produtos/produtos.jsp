<div class="jumbotron">
	<h1>Produtos</h1>
	<p>
		<a href="${pageContext.request.contextPath}/admin/produtos/importar-best-buy" 
			class="btn btn-primary btn-lg" 
			role="button">Importar Produtos da BestBuy</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/admin/produtos/listar"
			 class="btn btn-primary btn-lg" 
			 role="button">Listar Produtos</a>
	</p>
	
</div>

<content tag="local_script">
<script>
  $(function() {
		$( "li" ).removeClass( "active" )
		$( "#li_produtos" ).addClass( "active" );
  });
</script>
</content>