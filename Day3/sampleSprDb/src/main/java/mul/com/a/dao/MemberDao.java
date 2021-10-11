package mul.com.a.dao;

import java.util.List;

import mul.com.a.dto.MemberDto;

public interface MemberDao {
	
	List<MemberDto> allMember();
	
	boolean idcheck(String id);
	

}
