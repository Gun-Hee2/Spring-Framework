package mul.com.a.service;

import java.util.HashMap;
import java.util.List;

import mul.com.a.dao.BbsDao;
import mul.com.a.dto.BbsDto;
import mul.com.a.dto.BbsParam;

public interface BbsService {

	List<BbsDto> getBbsList(BbsParam param);
	
	int getAllBbs(BbsParam param);
	
	boolean writeBbs(BbsDto bbs);
	
	void readcount(int sseq);
	
	BbsDto getBbs(int sseq);
	
    boolean answerup(BbsDto dto);
	
	boolean answerin(BbsDto dto);
	
	boolean updateBbs(BbsDto dto);
	
	boolean deleteBbs(int sseq);
	
	
}
