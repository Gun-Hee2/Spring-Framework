<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--    
<%
int seq = Integer.parseInt(request.getParameter("seq"));
%>
--%> 

<%
String delete = (String)request.getAttribute("delete");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if(delete.equals("YES")){
%>
   <script type="text/javascript">
   alert('삭제되었습니다');
   location.href = "bbslist.do";
   </script>
<%
}else{
%>
   <script type="text/javascript">
   alert('삭제가 되지않았습니다');
   location.href = "bbslist.do";
   </script>
<%
}
%>
</body>
</html>







