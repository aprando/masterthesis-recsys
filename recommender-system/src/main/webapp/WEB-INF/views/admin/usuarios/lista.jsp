<div class="container theme-showcase" role="main">
	<div class="row">
		<h3>Usu&aacute;rios</h3>
	</div>
	<br>
	<div class="row">
		<table id="tb_usuarios">
			<thead>
				<tr>
					<th>Nome</th>
					<th>E-mail</th>
					<th>A&ccedil;&otilde;es</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td>${usuario.nome}</td>
					<td>${usuario.email}</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/usuarios/detalhar-dados-sociais?id=${usuario.id}">Dados Sociais</a>
						| <a href="${pageContext.request.contextPath}/admin/usuarios/editar?id=${usuario.id}">Editar</a>
						| <a href="${pageContext.request.contextPath}/admin/usuarios/remover?id=${usuario.id}">Remover</a>
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
		$( "#li_usuarios" ).addClass( "active" );
		
		$( "#tb_usuarios" ).dataTable( {
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