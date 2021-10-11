package mul.com.a;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(value = "hello.do", method = RequestMethod.GET)
	public String hello(Model model) {
		logger.info("HelloController hello() " + new Date());
		
		return "hello"; // jsp 파일명
	}
	
	@ResponseBody // <- Ajax를 사용할 때 반드시 필요로한다.
	@RequestMapping(value = "idcheck.do", method = { RequestMethod.GET,RequestMethod.POST }, 
			        produces = "application/String; charset=utf-8")
	public String idcheck(String id) {
		logger.info("HelloController idcheck() " + new Date());
		System.out.println("id:" + id);
		
		return "OK";
	}
	
	@ResponseBody // <- Ajax를 사용할 때 반드시 필요로한다.
	@RequestMapping(value = "account.do", method = { RequestMethod.GET,RequestMethod.POST })
	public List<MemberDto> account(MemberDto mem){
		logger.info("HelloController account() " + new Date());
		System.out.println(mem.toString());
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		list.add(new MemberDto("aaa", "111", "AAA", "Aaa", 0));
		list.add(new MemberDto("bbb", "222", "BBB", "Bbb", 0));
		list.add(new MemberDto("ccc", "333", "CCC", "Ccc", 0));
		
		return list;
	}
	
	@ResponseBody // <- Ajax를 사용할 때 반드시 필요로한다.
	@RequestMapping(value = "read.do", method = { RequestMethod.GET,RequestMethod.POST })
	public Map<String, Object> read(){
		logger.info("HelloController read() " + new Date());
		
		String name = "박정현";
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		list.add(new MemberDto("abc", "123", "성춘향", "abc@naver.com", 0));
		list.add(new MemberDto("bcd", "234", "정수동", "bcd@naver.com", 0));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("list", list);
		
		return map;
		
	}

}






