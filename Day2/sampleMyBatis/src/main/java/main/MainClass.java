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






