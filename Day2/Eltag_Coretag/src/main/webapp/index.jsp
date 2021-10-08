<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.HumanDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--
     EL : Expression Language
             표현       언어
          front end에서 java랑 혼용하지 않고 사용하기 위한 태그
          
     형식: ${ 값(연산식) }     

--%>

<%
    String str = "Hello";

%>

str:<%=str %><br>

<%
    request.setAttribute("_str", str);  // El태그를 사용하려면 request.setAttribute를 해야한다.
%>

str:${_str}<br>
number:${ 2 + 3 } <!-- 연산식 출력, 사용은 잘안함 -->
<br>

<%
HumanDto dto = new HumanDto(1, "홍길동");

request.setAttribute("_dto", dto);
%>

<%
if(dto != null){
	
}
%>

dto = ${empty _dto}  <!-- dto == null -> dto가 null값일때 -->
<br>
!dto = ${not empty _dto}
<br>

number:<%=dto.getNumber() %><br>
name:<%=dto.getName() %><br>
<br>
number:${_dto.number }
<br>
name:${_dto.name }

<%--
       EL : Value
       Core : 제어문

 --%>
 
<%-- if --%>

<%
   request.setAttribute("count", "10");
%>


<%
   String s_count = (String)request.getAttribute("count");
   int count = Integer.parseInt(s_count);
   if(count >= 10){
	   %>
	   <p>count:<%=count %></p>
	   <%
   }
%>   

<%-- coretag --%>
<c:if test="${count >= 10 }"> <%-- core태그는 문자열의 숫자도 인식을한다. --%>
    <p>count:${count}</p>
</c:if> 

<%
   request.setAttribute("name", "홍길동");
%>

<%
   String name = (String)request.getAttribute("name");
   if(name.equals("홍길동")){
	   %>
	   <p>이름:<%=name %></p>
	   <% 
   }
%>

<%-- <c:if test="${name == '홍길동' }"> --%>
<c:if test="${name eq '홍길동' }"> <%-- ==대신 eq를 사용 --%>
    <p>이름:${name}</p>
</c:if>

<%-- for --%>
<%
   for(int i = 0; i < 10; i++){
	   %>
	   <p><%=i %></p>
	   <% 	   
   }
%>

<c:forEach begin="0" end="9" step="1" varStatus="i">
    <p>${i.index}</p>
</c:forEach>

<%
    List<String> list = new ArrayList<String>();

    list.add(new String("tigers"));
    list.add(new String("lions"));
    list.add(new String("twins"));
    
    request.setAttribute("mylist", list);
    
    /*
    for(String item : list){
    	
    }
    */
%>

<c:forEach begin="0" end="2" var="item" items="${mylist}" varStatus="i">
    <p>index:${i.index} data:${item}</p>
</c:forEach>

</body>
</html>







