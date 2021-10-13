package mul.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Namespace;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.com.a.dao.MemberDao;
import mul.com.a.dto.MemberDto;
import mul.com.a.service.MemberService;

@Controller
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		logger.info("MemberController login " + new Date());
		
		return "member/login"; // login.jsp로 이동
		
	}
	
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		logger.info("MemberController regi " + new Date());
		
		return "member/regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
		logger.info("MemberController idcheck " + new Date());
		System.out.println(id);
		
		boolean b = service.idcheck(id);
		System.out.println(b);
		
		if(b == true) {
			return "NO";
		}else {
			return "YES";
		}
	}
	
	
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String addMember(Model model, MemberDto dto) {
		logger.info("MemberController addmember " + new Date());
		System.out.println(dto.toString());
		
		boolean b = service.addMember(dto);
		
		String regi = "YES";
		if(b == false) {
			regi = "NO";
		}	
		
		
		model.addAttribute("regi", regi);
		
		return "message/regimessage";
		
	}
	
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String loginAf(MemberDto mem, HttpSession session) {
		logger.info("MemberController loginAf " + new Date());
		
		MemberDto dto = service.login(mem);
		
		if(dto != null && dto.getId().equals("") == false) {
			//session에 저장
			session.setAttribute("login", dto);
			
			// bbslist.do로 이동
			
			return "redirect:/bbslist.do"; // controller에서 controller로 이동시
		//	return "forward:/bbslist.do"; // 짐을 갖고 controller에서 controller로 이동시
			
		}else {
			return "redirect:/login.do";
		}
	}

}






