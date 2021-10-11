package mul.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.com.a.dao.MemberDao;
import mul.com.a.dto.MemberDto;

@Repository //저장소
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	SqlSession sqlSession; // IOC(제어의 반전), DI(의존성)
	
	String namespace = "Member.";

	@Override
	public List<MemberDto> allMember() {
		List<MemberDto> list = sqlSession.selectList(namespace + "idcheck");
		return list;
	}
	
	@Override
	public boolean idcheck(String id) {
		int count = sqlSession.selectOne("idcheck", id);
		return count>0?true:false;
	}
	
	

}
