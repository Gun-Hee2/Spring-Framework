<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
String answer = (String)request.getAttribute("answer");    
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
if(answer.equals("YES")){
%>
  <script type="text/javascript">
  alert("답글 작성 완료!");
  location.href = 'bbslist.do';
  </script>
<%
}else if(answer.equals("NO")){
%>
  <script type="text/javascript">
  alert("답글 작성 실패.");
  location.href = 'bbslist.do';
  </script>
<%
}
%>

</body>
</html>