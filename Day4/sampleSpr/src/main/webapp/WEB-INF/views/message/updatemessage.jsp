<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String update = (String)request.getAttribute("update");
%>      
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(update.equals("YES")){
%>
  <script type="text/javascript">
  alert("글 수정 성공!");
  location.href = 'bbslist.do';
  </script>
<%	
}else if(update.equals("NO")){
%>
  <script type="text/javascript">
  alert("글 수정 실패. 다시 수정해 주세요.");
  location.href = 'bbslist.do';
  </script>
<%
}
%>

</body>
</html>