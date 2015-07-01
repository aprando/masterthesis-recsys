<div class="container theme-showcase" role="main">
	<div class="row">
		<h3>Categorias</h3>
	</div>
	<br>
	<div class="row">
		<table id="tb_categorias">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Categoria Pai</th>
					<th>Fonte</th>
					<th>A&ccedil;&otilde;es</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="cat" items="${categorias}">
				<tr>
					<td>${cat.nome}</td>
					<td>${cat.nomeCategoriaPai}</td>
					<td>${cat.fonte}</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/categorias/editar?id=${usuario.id}">Editar</a>
						| <a href="${pageContext.request.contextPath}/admin/categorias/remover?id=${usuario.id}">Remover</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>	

<content tag="local_script">
<script>
  $(function() {
		$( "li" ).removeClass( "active" )
		$( "#li_categorias" ).addClass( "active" );
		
		$( "#tb_categorias" ).dataTable( {
	        "language": {
	            "lengthMenu": "Apresentando _MENU_ registros por p&aacute;gina",
	            "zeroRecords": "Nenhum registro encontrado",
	            "info": "Apresentando p&aacute;gina _PAGE_ de _PAGES_",
	            "infoEmpty": "Nenhum registro encontrado",
	            "infoFiltered": "(filtrado de _MAX_ registros totais)",
	            "search":"Filtrar por:"
	        }
		});
  });
</script>
</content>