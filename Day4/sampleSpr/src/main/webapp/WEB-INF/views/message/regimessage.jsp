<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String regi = (String)request.getAttribute("regi");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(regi.equals("YES")){
%>
  <script type="text/javascript">
  alert("성공적으로 회원가입이 되셨습니다!");
  location.href = 'login.do';
  </script>
<%	
}else if(regi.equals("NO")){
%>
  <script type="text/javascript">
  alert("회원가입 실패. 다시 작성해 주세요.");
  location.href = 'regi.do';
  </script>
<%
}
%>




</body>
</html>




