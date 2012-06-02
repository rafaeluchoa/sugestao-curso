<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sugestão de Curso - Faculdade Lourenço Filho</title>
<style type="text/css">
	h2 {
		color: #4682B4;
		border-bottom: solid 1px #EFEFEF;
		font-size: 24px;
		margin-bottom: 20px;
	}
	
	table {
		margin-top: 10px;
		margin-bottom: 10px;
		width: 70%;
		background-color: #F8F8FF;
	}
	
	td {
		border-left: solid 1px #EFEFEF;
		border-bottom: solid 1px #EFEFEF;
		padding: 5px;
		font-family: verdana;
		font-size: 12px;
	}
	
	th {
		background-color: #EFEFEF;
		padding: 5px;
		font-family: verdana;
		font-size: 12px;
	}
		
	a {
		background-color: #4682B4;
		border: solid 1px #4682B4;
		color: #EAEAEA;
		text-decoration: none;
		padding: 5px;
		margin: 5px;
	}
	
	div { 
		margin-top: 10px;
		margin-bottom: 10px;
	}
	
	input[type="submit"], input[type="button"] {
		background-color: #4682B4;
		border: solid 1px #4682B4;
		color: #EAEAEA;
		padding: 5px;
		margin: 5px;
	}
	
	input[type="text"], textarea {
		background-color: #EFEFEF;
		border: solid 1px #EAEAEA;
		margin: 2px;
		padding: 5px;
	}
	
	input[type="text"]:focus, textarea:focus {
		background-color: #FFFFFF;
		border: solid 1px #4682B4;
		margin: 2px;
		padding: 5px;
	}
	
	#logo {
		background-image: url(${pageContext.request.contextPath}/imgs/logo.png);
		width: 120px;
		height: 90px;
		float: left;
		vertical-align: middle;
	}
	
	#header {
		color: #4682B4;
		border-bottom: solid 1px #EFEFEF;
		font-size: 36px;
	}
	
	#conteudo {
		margin: 20px;
		clear: both;
	}
	
	#rodape {
		margin-top: 50px;
		color: #4682B4;
		border-top: solid 1px #EFEFEF;
		font-style: italic;
	}
	
	#mensagem {
		color: red;
		font-weight: bold;
	}
	
	#voto {
		background-image: url(${pageContext.request.contextPath}/imgs/voto.png);
		width: 48px;
		height: 48px;
		vertical-align: middle;
	}
</style>
</head>
<body>

	<div id="header"> 
		<div id="logo"> </div>
		Sugestão de Curso
	</div>
	
	<div id="conteudo">
		 
		<c:forEach items="${mensagens}" var="mensagem">
			<div id="mensagem">${mensagem}</div>
		</c:forEach>
		