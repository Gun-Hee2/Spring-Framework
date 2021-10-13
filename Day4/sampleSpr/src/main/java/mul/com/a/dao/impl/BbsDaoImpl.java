package mul.com.a.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.com.a.dao.BbsDao;
import mul.com.a.dto.BbsDto;
import mul.com.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {

	@Autowired
	SqlSession session;
	
	String ns = "Bbs.";
	

	@Override
	public List<BbsDto> getBbsList(BbsParam param) {		
		return session.selectList(ns + "bbslist", param);
	}


	@Override
	public int getAllBbs(BbsParam param) {
		
		return session.selectOne(ns + "AllBbs", param);
	}


	@Override
	public boolean writeBbs(BbsDto bbs) {
		int n = session.insert(ns + "writeBbs", bbs);
		return n>0?true:false;
	}


	@Override
	public void readcount(int sseq) {
		session.update(ns + "readcount", sseq);
		
	}


	@Override
	public BbsDto getBbs(int sseq) {
		
		return session.selectOne(ns + "getBbs", sseq);
	}


	@Override
	public boolean answerup(BbsDto dto) {
		int n = session.update(ns + "answerup", dto);
		return n>0?true:false;
	}


	@Override
	public boolean answerin(BbsDto dto) {
		int n = session.insert(ns + "answerin", dto);
		return n>0?true:false;
	}


	@Override
	public boolean updateBbs(BbsDto dto) {
		int n = session.update(ns + "updateBbs", dto);
		return n>0?true:false;
	}


	@Override
	public boolean deleteBbs(int sseq) {
		int n = session.update(ns + "deleteBbs", sseq);
		return n>0?true:false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}








