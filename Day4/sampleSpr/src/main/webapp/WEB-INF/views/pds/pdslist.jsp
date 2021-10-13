<%@page import="mul.com.a.dto.PdsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<PdsDto> list = (List<PdsDto>)request.getAttribute("pdslist");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>자료실</h2>

<table border="1" style="width: 85%">
<col width="50"><col width="300"><col width="100">
<col width="50"><col width="50"><col width="100">

<thead>
<tr>
    <th>번호</th><th>제목</th><th>작성자</th>
    <th>조회수</th><th>다운수</th><th>작성일</th>
</tr>

</thead>

<tbody>
<%
   if(list.size() == 0){
	   %>
	   <tr>
	       <td colspan="6" align="center">작성된 자료가 없습니다</td>
	   </tr>
	   <% 
   }
%>


<%
  for(int i = 0; i < list.size(); i++){
	  PdsDto pds =  list.get(i);
  %>
  <tr>
     <th><%=i + 1 %></th>
     <td>
         <a href="pdsdetail.do?seq=<%=pds.getSeq() %>"> 
         <%=pds.getTitle() %>
         </a>
     </td>
     <td><%=pds.getId() %></td>
     <td><%=pds.getReadcount() %></td>
     <td><%=pds.getDowncount() %></td>
     <td><%=pds.getRegdate() %></td>
  </tr>
  
  <% 	  
  }
%>


</tbody>

</table>

<!-- 자료 추가 -->
<button type="button" onclick="pdsAdd()">자료추가</button>

<script type="text/javascript">
function pdsAdd() {
	location.href = "pdswrite.do";
}
</script>


</body>
</html>









