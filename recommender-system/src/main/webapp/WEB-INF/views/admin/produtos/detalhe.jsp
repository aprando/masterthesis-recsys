<div class="jumbotron">
	<h1>${produto.nome}</h1>
	
	<p align="center"><img alt="img" src="${produto.imagem}"/></p>
	<p align="center"><img alt="img" src="${produto.imagemGrande}"/></p>
	<p>${produto.descricaoLongaHtml}</p>
	<p>
		<a href="javascript:window.history.go(1)." class="btn btn-primary btn-lg" role="button">Voltar</a>
	</p>
</div>


<c:if test="${not empty produto.detalhes}">
	<div  class="jumbotron">
		<h1> Detalhes </h1>
		<c:forEach items="${produto.detalhes}" var="entry">
			<p><strong>${entry.key}:</strong> ${entry.value}</p>
		</c:forEach>		
	</div>
</c:if>

<div  class="jumbotron">
	<h1> Dados Gerais</h1>
	<c:if test="${not empty produto.preco}">
		<p>Preco: ${produto.preco}</p>
	</c:if>
	<c:if test="${not empty produto.preco}">
		<p>Descricao: ${produto.descricaoCurtaHtml}</p>
	</c:if>
	
</div>

<c:if test="${not empty produto.categorias}">
	<div  class="jumbotron">
		<h1> Categorias </h1>
		<c:forEach items="${produto.categorias}" var="cat">
			<p>${cat.nome}</p>
		</c:forEach>
	</div>
</c:if>

<c:if test="${not empty produto.caracteristicas}">
	<div  class="jumbotron">
		<h1> Caracteristicas </h1>
		<c:forEach items="${produto.caracteristicas}" var="cat">
			<p>${cat}</p>
		</c:forEach>
	</div>
</c:if>


<div>
</div>	

<content tag="local_script">
<script>
  $(function() {
	$( "li" ).removeClass( "active" )
	$( "#li_produtos" ).addClass( "active" );
  });
</script>
</content>