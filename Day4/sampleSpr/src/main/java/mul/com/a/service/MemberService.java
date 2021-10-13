package mul.com.a.service;

import java.util.List;

import mul.com.a.dto.MemberDto;

public interface MemberService {
	
	List<MemberDto> allMember();
	
	boolean idcheck(String id);
	
	boolean addMember(MemberDto mem);
	
	MemberDto login(MemberDto mem);

}
