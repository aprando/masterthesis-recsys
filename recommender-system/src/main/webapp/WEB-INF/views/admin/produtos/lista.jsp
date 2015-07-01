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
										<th>Nome</th>
										<th>Descricao</th>		
										<th>A&ccedil;&otilde;es</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach var="produto" items="${produtos}">
										<tr>
											<td>${produto.nome}</td>
											<td>${produto.descricaoCurta}</td>
											<td>
												<a href="${pageContext.request.contextPath}/admin/produtos/detalhar?id=${produto.id}">Detalhar</a>
												| <a href="${pageContext.request.contextPath}/admin/produtos/remover?id=${produto.id}">Remover</a>
												| <a href="${pageContext.request.contextPath}/admin/produtos/kmeans-grupo?id=${produto.id}">KMEANS</a>
												</td>
										</tr>
										
									</c:forEach>
									</tbody>
									<tfoot>
									<tr>
										<td colspan="6">
											<div class="btn-group pull-right">
												<!-- FIXME: Paginacao zuada!!! -->
												<c:forEach var="i" begin="0" end="10" step="1" varStatus="status">
													<button class="btn btn-default" id="pagina${pagina + status.index}" onclick="javascript:listarProdutos(${pagina + status.index});"><span>${pagina + status.index}</span></button>
												</c:forEach>
											</div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</c:if>			
			</tbody>
		</table>
	</div>
</div>	

<form role="form" action="${pageContext.request.contextPath}/admin/produtos/listar" id="buscarProdutosForm" method="get">
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