package mul.com.a.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.com.a.dao.BbsDao;
import mul.com.a.dto.BbsDto;
import mul.com.a.dto.BbsParam;
import mul.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	
	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> getBbsList(BbsParam param) {
		
		return dao.getBbsList(param);
	}

	@Override
	public int getAllBbs(BbsParam param) {
		
		return dao.getAllBbs(param);
	}

	@Override
	public boolean writeBbs(BbsDto bbs) {
		
		return dao.writeBbs(bbs);
	}

	@Override
	public void readcount(int sseq) {
		
		dao.readcount(sseq);
		
	}

	@Override
	public BbsDto getBbs(int sseq) {
		
		return dao.getBbs(sseq);
	}

	@Override
	public boolean answerup(BbsDto dto) {
		
		return dao.answerup(dto);
	}

	@Override
	public boolean answerin(BbsDto dto) {
		
		return dao.answerin(dto);
	}

	@Override
	public boolean updateBbs(BbsDto dto) {
		
		return dao.updateBbs(dto);
	}

	@Override
	public boolean deleteBbs(int sseq) {
		
		return dao.deleteBbs(sseq);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
