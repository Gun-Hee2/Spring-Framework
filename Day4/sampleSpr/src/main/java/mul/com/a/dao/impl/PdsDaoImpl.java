package mul.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.com.a.dao.PdsDao;
import mul.com.a.dto.PdsDto;

@Repository
public class PdsDaoImpl implements PdsDao {
	
	@Autowired
	
	SqlSession session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> getPdsList() {
		
		return session.selectList(ns + "pdslist");
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		int n = session.insert(ns + "uploadPds", dto);
		return n>0?true:false;
	}

	@Override
	public PdsDto getPds(int seq) {
		
		return session.selectOne(ns + "getPds", seq);
	}
	
	
	
	
	

}
