<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/cabecalho.jsp" />

<h2>Sugerir um curso</h2>

<form name="formulario" action="${pageContext.request.contextPath}/curso?cmd=sugerir" method="post">

	<table>
		<tr>
			<td>Nome do curso:<i style="color: red;">*</i></td>
			<td>
				<input type="text" name="nome" value="${param.nome}" size="20" />
				<i>entre 10 e 50 caracteres</i>
			</td>
		</tr>
		<tr>
			<td>Breve descrição:<i style="color: red;">*</i></td>
			<td>
				<input type="text" name="descricao" value="${param.descricao}" size="50" />
				<i>entre 20 e 150 caracteres</i>
			</td>
		</tr>
		<tr>
			<td>E-mail:<i style="color: red;">*</i></td>
			<td>
				<input type="text" name="email" value="${param.email}" size="50" />
				<i>fulano@dominio.com</i>
			</td>
		</tr>
	</table>
	
	<input type="submit" value="Salve" />
	<input type="button" value="Cancelar" onclick="window.location.href = '${pageContext.request.contextPath}/curso';" />
	
	<i style="color: red;">*</i><i>Campos obrigatórios<i>
	
</form>

<c:import url="/rodape.jsp" />