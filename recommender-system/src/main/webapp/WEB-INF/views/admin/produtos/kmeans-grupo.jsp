<div class="container theme-showcase" role="main">
	<div class="row">
		<h3>Produtos</h3>
	</div>
	<br>
	<div class="row">
		<table id="tb_produtos">
					<c:if test="${not empty produtos}">
						<div class="col-md-12">
							<hr>
							<table ng-table="tableParams" show-filter="true" class="table table-striped">
								<thead>
									<tr>
										<th>Imagem</th>
										<th>Nome</th>
										<th>Descricao</th>		
									</tr>
									</thead>
									<tbody>
										<c:forEach var="produto" items="${produtos}">
											<tr>
												<td><img alt="img" src="${produto.imagem}"/></td>
												<td>${produto.nome}</td>
												<td>${produto.descricaoCurta}</td>
											</tr>
											
										</c:forEach>
									</tbody>
									<tfoot>
									</tfoot>
							</table>
						</div>
					</c:if>			
			</tbody>
		</table>
	</div>
</div>	

<form role="form" action="${pageContext.request.contextPath}/admin/produtos/kmeans-grupo" id="buscarProdutosForm" method="get">
	<input type="hidden" name="pagina" value="${pagina}">
</form>


<content tag="local_script">
<script>
  $(function() {
		$( "li" ).removeClass( "active" )
		$( "#li_produtos" ).addClass( "active" );
		
		$( "#tb_produtos" ).dataTable( {
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

  function listarProdutos(pagina) {
  	$('input[name="pagina"]').val(pagina);
  	$('form#buscarProdutosForm').submit();
  }
  
</script>
</content>