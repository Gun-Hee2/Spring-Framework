package mul.com.a.dao;

import java.util.List;

import mul.com.a.dto.PdsDto;

public interface PdsDao {
	
	List<PdsDto> getPdsList();
	
	boolean uploadPds(PdsDto dto);
	
	PdsDto getPds(int seq);

}
