<%@page import="mul.com.a.dto.PdsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
PdsDto pds = (PdsDto)request.getAttribute("pds");
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>자료보기</h2>

<table border="1">
<col width="200"><col width="500">

<tr>
    <th>아이디</th>
    <td><%=pds.getId() %></td>
</tr>

<tr>
    <th>제목</th>
    <td><%=pds.getTitle() %></td>
</tr>

<tr>
    <th>다운로드</th>
    <td>
        <%=pds.getFilename() %>
        <input type="button" value="다운로드" 
            onclick="filedownload('<%=pds.getNewfilename() %>', '<%=pds.getFilename() %>', <%=pds.getSeq() %>)">
    </td>
</tr>

<tr>
    <th>조회수</th>
    <td><%=pds.getReadcount() %></td>
</tr>

<tr>
    <th>다운로드수</th>
    <td><%=pds.getDowncount() %></td>
</tr>

<tr>
    <th>등록일</th>
    <td><%=pds.getRegdate() %></td>
</tr>

<tr>
    <th>내용</th>
    <td>
        <textarea rows="10" cols="50"><%=pds.getContent() %></textarea>
    </td>
</tr>

<!-- 수정하기, 삭제하기 -->

</table>

<script type="text/javascript">
function filedownload(newfilename, filename, seq) {
	location.href = "fileDownload.do?newfilename=" + newfilename + "&filename=" + filename + "&seq=" + seq;
}
</script>

</body>
</html>











