<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/cabecalho.jsp" />

<h2>Sugestões de Curso</h2>

<a href="${pageContext.request.contextPath}/curso?cmd=mostrarFormSugerir">Sugerir um curso</a>
<a href="${pageContext.request.contextPath}/curso">Atualizar</a>

<table class="lista">
	<thead>
		<tr>
			<th>Nome do curso</th>
			<th>Descrição</th>
			<th>Sugerido por</th>
			<th>Qtde de votos</th>
			<th>Ação</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${cursos}" var="curso">
		<tr>
			<td>
				${curso.nome}
			</td>
			<td>
				${curso.descricao}
			</td>
			<td>
				${curso.email}
			</td>
			<td align="center">
				${curso.qtdeVotos}
			</td>
			<td align="center">
				<a href="${pageContext.request.contextPath}/curso?cmd=mostrarFormVotar&nome=${curso.nome}">
					Votar
				</a>
			</td>
		</tr>
	</c:forEach>
	<tbody>
</table>

<c:import url="/rodape.jsp" />