<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/cabecalho.jsp" />

<h2>Votar</h2>

<form name="formulario" action="${pageContext.request.contextPath}/curso?cmd=votar" method="post">

	<table>
		<tr>
			<td>Nome do curso:</td>
			<td>
				${curso.nome}
				<input type="hidden" name="nome" value="${curso.nome}" />
			</td>
		</tr>
		<tr>
			<td>Breve descrição:</td>
			<td>${curso.descricao}</td>
		</tr>
		<tr>
			<td>E-mail:<i style="color: red;">*</i></td>
			<td>
				<input type="text" name="email" size="50" value="${param.email}" />
				<i>fulano@dominio.com</i>
			</td>
		</tr>
	</table>
	
	<input type="submit" value="Salve" />
	<input type="button" value="Cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/curso';" />
	
	<i style="color: red;">*</i><i>Campos obrigatórios<i>
	
</form>

<c:import url="/rodape.jsp" />