package mul.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.com.a.dao.PdsDao;
import mul.com.a.dto.PdsDto;
import mul.com.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {
	
	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> getPdsList() {
		
		return dao.getPdsList();
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		
		return dao.uploadPds(dto);
	}

	@Override
	public PdsDto getPds(int seq) {
		
		return dao.getPds(seq);
	}
	
	
	
	
	
	

}
