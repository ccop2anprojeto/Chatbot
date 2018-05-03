<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
  <html lang="pt-br">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<h3>Chatbot</h3>
	<form action="controller.do" method="post">
		Pergunta: <input type="text" name="pergunta"><br>
		<button class="btn btn-primary" type="submit" name="command" value="newChatBot">Enviar</button>
	</form>
	
	<c:if test="${not empty resp}">
	<div>
		Resposta 1: ${resp.get(0).getResp()}
	</div>
	<c:if test="${resp.size() > 1}">
	<div>
		Resposta 2: ${resp.get(1).getResp()}
	</div>	
	</c:if>
	<c:if test="${resp.size() > 2}">
	<div>
		Resposta 3: ${resp.get(2).getResp()}
	</div>
	</c:if>
	</c:if>

</body>
</html>