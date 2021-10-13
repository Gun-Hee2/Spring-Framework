package mul.com.a.service;

import java.util.List;

import mul.com.a.dto.PdsDto;

public interface PdsService {
	
	List<PdsDto> getPdsList();
	
	boolean uploadPds(PdsDto dto);
	
	PdsDto getPds(int seq);

}
