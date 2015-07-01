<div class="container bs-docs-container">
	<div class="row">
		<div class="col-md-12">

			<div class="bs-docs-section">
				<h1 id="type" class="page-header">Facebook</h1>
				<h2 id="type" class="page-header">Dados Pessoais</h2>
				<div class="row">
					<label class="col-md-3">Nome:</label>
					<div class="col-md-9">${usuarioFacebook.user.name}</div>
				</div>
				<div class="row">
					<label class="col-md-3">E-mail:</label>
					<div class="col-md-9">${usuarioFacebook.user.email}</div>
				</div>
				<c:if test="${!empty usuarioFacebook.user.birthday}">
					<div class="row">
						<label class="col-md-3">Data nascimento:</label>
						<div class="col-md-9">${usuarioFacebook.user.birthday}</div>
					</div>		
				</c:if>
				<c:if test="${!empty usuarioFacebook.user.favoriteTeams}">
					<div class="row">
						<label class="col-md-3">Times favoritos:</label>
						<div class="col-md-9">
							<c:forEach var="team" items="${usuarioFacebook.user.favoriteTeams}">
								${team.name}<br>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty usuarioFacebook.user.favoriteAthletes}">
					<div class="row ">
						<label class="col-md-3">Atletas favoritos:</label>
						<div class="col-md-9">
							<c:forEach var="athlete" items="${usuarioFacebook.user.favoriteAthletes}">
								${athlete.name}<br>
							</c:forEach>
						</div>
					</div>
				</c:if>
				
				
				<c:if test="${!empty usuarioFacebook.user.education}">
					<div class="row">
						<label class="col-md-3">Educa&#231;&#227;o:</label>
						<div class="col-md-9">
							<c:forEach var="edu" items="${usuarioFacebook.user.education}">
								${edu.school.name}
							</c:forEach>
						</div>
					</div>
				</c:if>
			
				<h2 id="type" class="page-header">Likes</h2>
				<h2 id="type" class="page-header">Posts</h2>
				<h2 id="type" class="page-header">Status</h2>
			</div>				
		</div>
	</div>		
</div>		