package mul.com.a.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mul.com.a.dao.BbsDao;
import mul.com.a.dto.BbsDto;
import mul.com.a.dto.BbsParam;
import mul.com.a.service.BbsService;

@Controller
public class BbsController {
	
	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	BbsService service;
	
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model, BbsParam param) {
		logger.info("BbsController bbslist " + new Date());
		
		// 현재 페이지
		int pageNumber = 0;
		if(param != null) {
			pageNumber = param.getPageNumber();
		}
		model.addAttribute("pageNumber", pageNumber);
		
		int start, end;
		start = 1 + 10 * pageNumber;
		end = 10 + 10 * pageNumber;
		
		param.setStart(start);
		param.setEnd(end);
		
		
		List<BbsDto> list = service.getBbsList(param);
		
		model.addAttribute("bbslist", list);
		
		model.addAttribute("choice", param.getChoice());
		model.addAttribute("search", param.getSearch());
		
		// 총 글의 수 갖고오기
		int allbbs = service.getAllBbs(param);
		
		// 총 페이지 수
		Integer bbsPage = allbbs / 10;
		if((allbbs % 10) > 0) {
			bbsPage = bbsPage + 1;
		}
		
		model.addAttribute("bbsPage", bbsPage);
		
		
		return "bbs/bbslist";
		
	}
	
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String bbswrite() {
		logger.info("BbsController bbswrite " + new Date());
		
		return "bbs/bbswrite";
		
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.POST)
	public String bbswriteAf(BbsDto bbs) {
		logger.info("BbsController bbswriteAf " + new Date());
		
		boolean b = service.writeBbs(bbs);
		
		if(b == true) {
			return "redirect:/bbslist.do";
		}
			
		return "redirect:/bbslist.do";
		
	}
	
	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(String seq, Model model) {
		logger.info("BbsController bbsdetail " + new Date());
		
		int sseq = Integer.parseInt(seq);
		
		service.readcount(sseq); // BbsDao의 readcount()메소드에 seq를 넣고 실행하여 readcount를 증가

		BbsDto dto = service.getBbs(sseq); // seq로 dto취득
		
		model.addAttribute("seq", seq);
		
		model.addAttribute("bbs", dto);
		
		return "bbs/bbsdetail";
		
	}
	
	@RequestMapping(value = "bbsanswer.do", method = RequestMethod.GET)
	public String bbsanswer(String seq, Model model) {
		logger.info("BbsController bbsanswer " + new Date());
		int sseq = Integer.parseInt(seq);
		
		BbsDto bbs = service.getBbs(sseq);
		
		model.addAttribute("seq", sseq);
		model.addAttribute("bbs", bbs);
		
		
		return "bbs/bbsanswer";
		
	}
	
	@RequestMapping(value = "bbsanswerAf.do", method = RequestMethod.POST)
	public String bbsanswerAf(int seq, String id, String title, String content, BbsDto dto, Model model) {
		logger.info("BbsController bbsanswerAf " + new Date());
		
		System.out.println(seq);
		System.out.println(id);
		System.out.println(title);
		System.out.println(content);
		System.out.println(dto);
	//	int sseq = Integer.parseInt(seq);
		
	//	BbsDto dto = new BbsDto(id, title, content); 
		
		
        boolean up = service.answerup(dto);
        System.out.println(up);
		
		boolean in = service.answerin(dto);
		
		
		String answer = "YES";
		if(in == false) {
			answer = "NO";
		}
		
		model.addAttribute("answer", answer);
		
		return "message/ansmessage";
		
		
	}
	
	@RequestMapping(value = "bbsupdate.do", method = RequestMethod.GET)
	public String bbsupdate(String seq, Model model) {
		logger.info("BbsController bbsupdate " + new Date());
		
		int sseq = Integer.parseInt(seq);
		
		service.readcount(sseq); // BbsDao의 readcount()메소드에 seq를 넣고 실행하여 readcount를 증가

		BbsDto dto = service.getBbs(sseq); // seq로 dto취득
		
		model.addAttribute("seq", seq);
		
		model.addAttribute("bbs", dto);
		
		return "bbs/bbsupdate";
		
	}
	
	@RequestMapping(value = "bbsupdateAf.do", method = RequestMethod.POST)
	public String bbsupdateAf(String updatetitle, String updatecontent, int seq, Model model) {
		logger.info("BbsController bbsupdateAf " + new Date());
		
		BbsDto updto = new BbsDto(seq, updatetitle, updatecontent);
		System.out.println(updto);
		
		boolean updateBbs = service.updateBbs(updto);
		
		String update = "YES";
		if(updateBbs == false) {
			update = "NO";
		}
		
		model.addAttribute("update", update);
		
		return "message/updatemessage";
	}
	
	@RequestMapping(value = "bbsdelete.do", method = RequestMethod.GET)
	public String bbsdelete(String seq, Model model) {
		logger.info("BbsController bbsdelete " + new Date());
		System.out.println(seq);
		
		int sseq = Integer.parseInt(seq);
		
		boolean deleteBbs = service.deleteBbs(sseq);
		System.out.println(deleteBbs);
		
		String delete = "YES";
		if(deleteBbs == false) {
			delete = "NO";
		}
		
		model.addAttribute("delete", delete);
		
		return "bbs/bbsdelete";
		
	}
	
	
	
	
	
}
