<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Home JSP</h2>

<%
String name = (String)request.getAttribute("name");
%>

이름:<%=name %><br><br>

</body>
</html>