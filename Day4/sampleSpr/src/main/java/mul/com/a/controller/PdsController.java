package mul.com.a.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mul.com.a.dto.PdsDto;
import mul.com.a.service.PdsService;
import mul.com.a.util.PdsUtil;

@Controller
public class PdsController {

   private static Logger logger = LoggerFactory.getLogger(PdsController.class);
   
   @Autowired
   PdsService service;

   @RequestMapping(value = "pdslist.do", method = RequestMethod.GET)
   public String pdslist(Model model) {
      logger.info("PdsController pdslist " + new Date());
      
      List<PdsDto> list = service.getPdsList();
      model.addAttribute("pdslist", list);
      
      return "pds/pdslist";
   }
   
   @RequestMapping(value = "pdswrite.do", method = RequestMethod.GET)
   public String pdswrite() {
      logger.info("PdsController pdswrite " + new Date());
      
      return "pds/pdswrite";
   }
   
   // upload
   @RequestMapping(value = "pdsupload.do", method = RequestMethod.POST)
   // form field, !form field
   public String pdsupload(PdsDto pdsdto, 
                     @RequestParam(value = "fileload", required = false)
                     MultipartFile fileload,
                     HttpServletRequest req) {
      
      // filename(원본)취득
      String filename = fileload.getOriginalFilename();
      System.out.println(filename);
      
      // upload 경로설정 
      // server(tomcat) <- 진짜!
      String fupload = req.getServletContext().getRealPath("/upload");   // webapp/upload
      
      // 폴더
//      String fupload = "c:\\tmp";
      
      // 경로 출력
      System.out.println("fupload: " + fupload);
      
      // 파일명이 충돌되지 않도록 파일명을 변경
      String newfilename = PdsUtil.setNewFileName(filename);
      System.out.println("newfilename: " + newfilename);
      
      pdsdto.setFilename(filename);
      pdsdto.setNewfilename(newfilename);
      
      // 경로 + 변경된 파일명
      File file = new File(fupload + "/" + newfilename);
      
      try {
    	  // 폴더에 실제 업로드
		FileUtils.writeByteArrayToFile(file, fileload.getBytes());
		
		service.uploadPds(pdsdto);
		
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
      
      return "redirect:pdslist.do";
      
   }
   
   @RequestMapping(value = "pdsdetail.do", method = RequestMethod.GET)
   public String pdsdetail(int seq, Model model) {
	   logger.info("PdsController pdsdetail " + new Date());
	   
	   PdsDto pds = service.getPds(seq);
	   
	   model.addAttribute("pds", pds);
	   
	   
	   return "pds/pdsdetail";
	   
	   
   }
   
   @RequestMapping(value = "fileDownload.do", method = RequestMethod.GET)
   public String fileDownload(String newfilename, String filename, int seq, Model model, HttpServletRequest req) {
	   logger.info("PdsController fileDownload " + new Date());
	   
	   // 경로
	   // server
	   String fupload = req.getServletContext().getRealPath("/upload");
	   
	   // 폴더
    //   String fupload = "c:\\tmp";
	   
	   File downloadFile = new File(fupload + "/" + newfilename);
	   
	   model.addAttribute("downloadFile", downloadFile);
	   model.addAttribute("filename", filename);
	   model.addAttribute("seq", seq);
	   
	   return "downloadView";
	   
   }
   
}











