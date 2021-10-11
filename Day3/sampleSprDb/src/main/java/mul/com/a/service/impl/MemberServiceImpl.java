package mul.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.com.a.dao.MemberDao;
import mul.com.a.dto.MemberDto;
import mul.com.a.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao dao;

	@Override
	public List<MemberDto> allMember() {
		
		return dao.allMember();
	}
	
	
	
	

}
