# Day2

## Spring - DispatcherServlet과 web.xml 설정

**DispatcherServlet이란?**

<br>

스프링 MVC에서 DispatcherServlet은 다른 웹 프레임워크의 프론트 컨트롤러처럼 클라이언트의 request를 컨트롤러에 전달할 뿐만 아니라, 스프링 Ioc 컨테이너와 통합하여 스프링의 모든 기능을 제공한다.

<br>

* web.xml를 이용한 DispatcherServlet의 생성 과정

```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" version="4.0">
  <display-name>sprSample1</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
<!-- Servlet을 묶어주는 기능(DispatcherServlet)  --> 
<servlet>
    <servlet-name>dispatcherServlet</servlet-name> 
    <!-- 이름이 dispatcherServlet인 서블릿 생성 -->
    <servlet-class>
        org.springframework.web.servlet.DispatcherServlet
        <!-- org.springframework.web.servlet.DispatcherServlet의 인스턴스이다. -->
    </servlet-class>  
    
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <!-- contextConfigLocation에 위치한 파일들과 함께 초기화 된다 -->
        <param-value>
            /WEB-INF/spring/dispatcherServlet.xml 
            <!-- bean정보를 담고 있는 설정 파일의 경로 지정 -->
        </param-value>
    </init-param>
    
    <load-on-startup>1</load-on-startup>
    <!-- load-on-startup은 여러가지 서블렛이 로드될 때 순서를 정의하는 것이다.  
         정수값이 작은 서블릿부터 먼저 로드된다. -->

    
</servlet>  

<servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <!-- <url-pattern>/</url-pattern> -->
    <url-pattern>/home</url-pattern>
    <!-- /home으로 들어오는 클라이언트의 요청을 dispatcherServlet이 처리 하도록 설정 -->
   
</servlet-mapping> 
</web-app>
```

web.xml을 알기 쉽게 이해하기 위해서 짧게 요약하자면, <br>

위 파일의 코드를 기준으로 맵핑값이 home인 모든 요청을 dispatcherServlet이란 이름을 가진 서블릿으로 넘긴다는  의미이다. <br>

그 후에, dispatcherServlet에서 /WEB-INF/spring/ 경로의 dispatcherServlet.xml 파일을 찾는다.

<br><br>

* dispatcherServlet.xml

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
    
    <!-- spring MVC annotation(주석문, 지시어)을 사용하기 위한 설정 -->
    <context:annotation-config/>
    
    <!-- 사용자의 view의 위치와 확장명을 설정 -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!-- InternalResourceViewResolver는 Controller에서 return된 ModelAndView 객체에서 선언된 view page를 지정해주는 class이다. -->
    <!-- 실질적인 jsp를 호출하는데 필요한 친구이다. -->
        <property name="prefix" value="/WEB-INF/views/"></property> <!-- views(jsp, html)의 경로 -->
        <property name="suffix" value=".jsp"></property>  <!-- 확장자명은 jsp -->
    </bean>
    <!-- prefix는 접두사, suffix는 접미사
         prefix는 컨트롤러에서 jsp파일을 찾을 때 기준이 되는 폴더
         suffix는 컨트롤러에서 설정한 뷰페이지명 끝에 붙여주는 확장자명 -->
    <!-- InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
         viewResolver.prefix = "/WEB-INF/views";
     -->
     
     <context:component-scan base-package="mul.com.a"/>
    <!-- 패키지명에 해당되는 경로에서 컨트롤러를 스캔 -->

</beans>
```

<br><br>

### Controller 파일 생성

* HomeController.java

```java
package mul.com.a;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller     // <- annotation
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("home") // 맵핑값이 home으로 들어왔을 때 다음의 메소드를 수행
	public String helloMethod(Model model) { // Model <- 짐을 포장하기 위한 interface
	//	System.out.println("HomeController helloMethod()");
		logger.info("HomeController helloMethod() " + new Date());
		
         String name = "홍길동";
		
		model.addAttribute("name", name); // 짐싸! == setAttribute
		
		return "home";  // <- retrun값이 "home"이면 뷰페이지명이 home인 home.jsp로 가라!
        // home.jsp로 가는 이유 -> 위에서 설정한 dispatcherServlet.xml의 bean설정때문
	}
	
	@RequestMapping(value = "home.do", method = RequestMethod.GET)
    // hello.jsp 뷰페이지에서 값을 주고받음
	public String home(Model model, Human human) {
		logger.info("HomeController home() " + new Date());
		
         // hello.jsp에서 입력한 human의 정보를 받아서 다음과 같이 출력
		System.out.println("name:" + human.getName());
		System.out.println("age:" + human.getAge());
		
         // hello.jsp에서 입력해서 받아온 human의 정보를 다시 짐을 싸서 hello.jsp로 이동
		model.addAttribute("human", human);
		return "hello";
	}
}

```

<br>

### View 페이지 생성

* index.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="home">home으로 이동</a>

<a href="hello.do">hello.do로 이동</a>

</body>
</html>
```

<br>

* home.jsp

```jsp
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
// HomeController.java에서 보내준 name값을 받아온다.    
String name = (String)request.getAttribute("name"); 
%>

<!-- 받아온 이름값인 name을 출력 -->    
이름:<%=name %><br><br>

</body>
</html>
```

<br>

* hello.jsp

```jsp
<%@page import="mul.com.a.Human"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Hello JSP</h2>

<%
// HomeController.java에서 보내준 name값을 받아온다.    
String name = (String)request.getAttribute("name");
%>

<!-- 받아온 이름값인 name을 출력 --> 
이름:<%=name %><br><br>

<!-- 받아온 이름값인 name을 출력 --> 
이름:${name}     <!-- EL tag(jsp에서 사용가능), Core tag -->

<br><br>

<!-- 입력한 이름값과 나이값을 가지고 home.do(HomeController.java)로 이동 -->
<form action="home.do" method="get">

이름:<input type="text" name="name">
<br>
나이:<input type="text" name="age">
<br>
<input type="submit" value="전송">
</form>
    
<%
// 전송했던 이름값과 나이값을 home.do로부터 Human의 객체로 다시 받아옴     
Human hu = (Human)request.getAttribute("human"); 
if(hu == null){ 
    // 처음 hello.jsp로 왔을 때 입력한 값을 전송한 적이 없기 때문에 hu는 null이다.
	hu = new Human("김영희", 22); // 초기값.
}
%>

이름:<input type="text" value="<%=hu.getName() %>">
<!--초기값인 "김영희"가 최초로 value값으로 나타나며, 입력 후 전송한 데이터가 있다면 입력한
    이름의 값이 value값으로 나타난다.-->    
<br>
나이:<input type="text" value="<%=hu.getAge() %>">
<!--위의 이름과 동일 -->    
<br><br>

이름:<input type="text" value="${human.name }">
<!-- el tag 사용 시에는 컨트롤러에서 받아오는 dto의 데이터값을 ${}안에 바로 적는다.  -->    
<br>
나이:<input type="text" value="${human.age }">

</body>
</html>
```

<br>

* Human.java

```java
package mul.com.a;

import java.io.Serializable;

public class Human implements Serializable{
	
	private String name;
	private int age;
	
	public Human() {
		
	}

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}
}
```

<br>

### 총 이동 과정 순서 

index.jsp -> web.xml -> dispatcherServlet.xml -> HomeController.java -> home.jsp / hello.jsp

<br><br>

## EL Tag / Core Tag

* EL Tag

EL , Expression Language의 줄임말로 JSP 2.0에 추가되어<br>

<%=%> 와 같은 jsp에 쓰는 java 코드를 간결하게 사용하는 태그를 말한다.<br>

즉, web부분에서 java와 혼용하지 않기 위한 태그이다.

<br><br>

* Core Tag

Core Tag는 변수나 제어문을 사용할 수 있게 해주는 태그이다.<br>

Core Tag를 사용하기 위해서 먼저 jstl-1.2.jar파일과 standard-1.1.2.jar파일이 다음의 경로에 있어야 한다.

![제목 없음](https://user-images.githubusercontent.com/87796075/136689459-25a489ed-326e-4808-9c06-883a6e37044b.png)

<br>

추가적으로 Core Tag는 필히 page지시문에 jstl선언해주어야한다.

![제목 없음1](https://user-images.githubusercontent.com/87796075/136689469-1ce18963-0611-44a8-b3c6-a51072d186bf.png)

<br>

* EL / Core Tag와 JSP Tag를 비교한 예시

```jsp
<!-- EL Tag --%>
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

<!-- Core Tag --%>
 
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
```



## MyBatis

### MyBatis란?

객체 지향 언어인 자바의 관계형 데이터베이스 프로그래밍을 좀 더 쉽게 할 수 있게 도와 주는 개발 프레임 워크로서,<br> 

JDBC를 통해 데이터베이스에 엑세스하는 작업을 캡슐화하고 일반 SQL 쿼리, 저장 프로 시저 및 고급 매핑을 지원하며 모든 JDBC 코드 및 매개 변수의 중복작업을 제거한다. <br>

Mybatis에서는 프로그램에 있는 SQL쿼리들을 한 구성파일에 구성하여 프로그램 코드와 SQL을 분리할 수 있는 장점을 가지고 있다.<br>

### MyBatis 환경 설정

우선적으로 Sts에서  MyBatis를 사용하기 위해서 mybatis-3.5.6.jar파일과 ojdbc6.jar파일을 필요로 한다.<br>

다음의 경로에 두 파일을 추가해 준다.<br>

![제목 없음2](https://user-images.githubusercontent.com/87796075/136689478-6728fe0d-5628-40d4-ab23-833ba59eeb6c.png)

<br>

### DB설정과 DB의 정보가 담긴 config.xml 생성

* config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
<!-- db 설정, 정보 -->  
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            
            <!-- Connection Pool -->
            <dataSource type="POOLED">
            
                <!-- oracle 드라이버 -->
                <property name="driver" value="oracle.jdbc.driver.OracleDriver"/>
                
                <!--접속할 DB의 Connection URL과 오라클 계정의 아이디와 비밀번호. -->
                <property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
                <property name="username" value="HR"/>
                <property name="password" value="HR"/>
            
            </dataSource>
        
        </environment>
    </environments>
    
    <mappers>
        <!-- <mappers>의 하위 엘리먼트 <mapper>의 resource 속성으로 매퍼 파일의 경로를 지정해주며, 
        매퍼 파일이 늘어날 때마다 추가해줘야 한다. -->
        <mapper resource="mybatis/mapper.xml" />
    </mappers>

</configuration>
```

<br>

### DB의 데이터를 담아두기 위한 Dto 생성

* UserDto.java

```java
package dto;

public class UserDto {
	
	private String id;
	private String name;
	private int age;
	private String joindate;
	
	public UserDto() {
		
	}

	public UserDto(String id, String name, int age, String joindate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.joindate = joindate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", age=" + age + ", joindate=" + joindate + "]";
	}
}
```

<br>

* BbsParam.java

```java
package dto;

public class BbsParam {
	
	private String choice;
	private String search;
	
	public BbsParam() {
		
	}

	public BbsParam(String choice, String search) {
		super();
		this.choice = choice;
		this.search = search;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
```

<br>

* BbsDto.java

```java
package dto;

import java.io.Serializable;

public class BbsDto implements Serializable {

	private int seq;
	private String id;		// 작성자
	
	private int ref;		// 그룹번호		답글용
	private int step;		// 행번호
	private int depth;		// 깊이
	
	private String title;	// 제목
	private String content;	// 내용
	private String wdate;	// 작성일	
	
	private int del;		// 삭제여부
	private int readcount;	// 조회수
	
	public BbsDto() {
	}

	public BbsDto(int seq, String id, int ref, int step, int depth, String title, String content, String wdate, int del,
			int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
	}

	public BbsDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "BbsDto [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", title="
				+ title + ", content=" + content + ", wdate=" + wdate + ", del=" + del + ", readcount=" + readcount
				+ "]";
	}
	
}
```

<br>



### SQL 쿼리문을 담아두는 mapper.xml 생성

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace는 mapper를 구분하는 식별자이다. -->  
<mapper namespace="mybatis.mapper">
    
<!-- 
    <insert parameterType="Object" resultType="Object">
        INSERT INTO HUMAN(ID, PWD, NAME)
        VALUES(#{id}, #{pwd}, #{name})
    </insert>
    
    <delete
    
    <select
    
    <update

 -->    

<!-- parameterType은 데이터를 담아두는 객체 타입을 결정함 -->
<!-- id는 쿼리문을 사용하는 클래스에서 mapper.xml의 쿼리문을 사용하기 위해 설정하는 키값 -->
<insert id="add" parameterType="dto.UserDto">
    INSERT INTO USERDTO(ID, NAME, AGE, JOINDATE)
    <!-- UserDto의 데이터들이 VALUE값으로 -->
    VALUES(#{id}, #{name}, #{age}, SYSDATE)
</insert>    

<update id="update" parameterType="dto.UserDto">
    UPDATE USERDTO 
    SET AGE=#{age} 
    WHERE NAME=#{name}
</update>

<!-- resultType은 select의 경우 쿼리 조회값의 데이터 객체 타입을 설정해주기 위해 필요하다. -->
<select id="search" parameterType="String" resultType="dto.UserDto">
    SELECT *
    FROM USERDTO
    WHERE ID=#{id}
</select>

<select id="dataAll" resultType="dto.UserDto">
    SELECT *
    FROM USERDTO
</select>

<select id="bbslist" parameterType="dto.BbsParam" resultType="dto.BbsDto">
    SELECT *
    FROM BBS
    <!-- MainClass에서 선택한 BbsParam의 choice의 값에 따라 조회하는 쿼리가 달라짐   -->
    <if test="choice == 'title'">
        WHERE TITLE LIKE '%' || #{search} || '%'
    </if>
    <if test="choice == 'content'">
        WHERE CONTENT LIKE '%' || #{search} || '%'
    </if>
    <if test="choice == 'id'">
        WHERE ID=#{search}
    </if>
    ORDER BY REF DESC, STEP ASC
    
</select>
</mapper>
```

<br>

### mapper.xml의 쿼리문을 실행해 DB의 데이터를 받아와서 출력하는 MainClass 생성

* MainClass.java

```java
package main;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dto.BbsDto;
import dto.BbsParam;
import dto.UserDto;

public class MainClass {

	public static void main(String[] args)throws Exception {
		
		// mybatis의 설정파일을 읽어 들인다.
		InputStream is = Resources.getResourceAsStream("mybatis/config.xml");
		
		// sqlSessionFactory 객체를 갖고온다.
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		
		// SqlSession 객체를 취득
		SqlSession session = factory.openSession();
		
		/*
		// insert
		UserDto dto = new UserDto("ggg", "소녀시대", 20, "");
		
		int count = session.insert("add", dto);
		if(count > 0) {
			session.commit();
			System.out.println("추가 성공!");
		}else {
			session.rollback();
			System.out.println("추가 실패.");
		}
		*/
		
		/*
		// update
		UserDto dto = new UserDto("", "소녀시대", 30, "");
		
		int count = session.update("update", dto);
		if(count > 0) {
			session.commit();
			System.out.println("수정 성공!");
		}else {
			session.rollback();
			System.out.println("수정 실패.");
		}
		*/
		
		/*
		// select 1개
		String id = "ggg";
		UserDto user = session.selectOne("search", id);
		System.out.println(user.toString());
		
		// select 다수
		List<UserDto> list = session.selectList("dataAll");
		for (UserDto u : list) {
			System.out.println(u.toString());
		}
		*/
		
		// BBS
		BbsParam param = new BbsParam("title", "토트넘");
		
		List<BbsDto> bbslist = session.selectList("bbslist", param);
		
		for (BbsDto bbs : bbslist) {
			System.out.println(bbs.toString());
		}
	}
}
```
