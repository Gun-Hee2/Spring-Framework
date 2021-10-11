<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h2>hello jsp</h2>

아이디:<input type="text" id="id">
<br><br>
<button type="button" onclick="idcheck()">아이디 체크</button>

<script type="text/javascript">
function idcheck() {
	
	$.ajax({
		url:"idcheck.do",
		type:"post",
		data:{ id:$("#id").val() },
		success:function( resp ){
		//	alert('success');
			alert(resp);
		},
		error:function(){
			alert('error');
		}
	});
}

</script>

<br><br><br>

아이디:<input type="text" id="myid" value="abc"><br>
패스워드:<input type="text" id="pwd" value="123"><br>
이름:<input type="text" id="name" value="홍길동"><br>
이메일:<input type="text" id="email" value="abc@naver.com">
<br><br>
<button type="button" id="account">회원가입</button>

<script type="text/javascript">
$("#account").click(function() {
//	alert('click');

    let member = {
    	id:$("#myid").val(),
    	pwd:$("#pwd").val(),
    	name:$("#name").val(),
    	email:$("#email").val()
    		
    };

    $.ajax({
    	url:"account.do",
    	type:"post",
    	data:member,
    	dataType:'json',
    	success:function( datas ){
    	//	alert('success');
    	//  alert(datas);
    	    alert( JSON.stringify(datas));
    	},
    	error:function(){
    		alert('error');
    	}
    });
});

</script>

<br><br>

<button type="button" onclick="func()">다수의 전송받기</button>
<script type="text/javascript">
function func() {
	$.ajax({
		url:"read.do",
		type:"get",
		success:function( map ){
		//	alert('success');
		//	alert(map);
			alert(map.name);
			alert(JSON.stringify(map.list));
		},
		error:function(){
			alert('error');
		}
	});
}

</script>


</body>
</html>





