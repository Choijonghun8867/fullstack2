package edu.springStudy.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import edu.springStudy.service.UserService;
import edu.springStudy.vo.BoardVO;
import edu.springStudy.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController
{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", "반갑습니다." );
		//request.setAttribute()와 동일한 역할( = model.addAttribute)
		
		return "home";
	}
	
	@RequestMapping(value="/sample.do", method = RequestMethod.GET)
	public String sample(Model model)
	{
		String name = "홍길동";
		int age = 20;
		String addr = "전주 덕진구";
		String phone = "010-1111-1111";
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("addr", addr);
		model.addAttribute("phone", phone);
		
		return "ex01";
	}
	
	@RequestMapping(value="/sample.do",method=RequestMethod.POST)
	public String sample1(UserVO userVO) 
	{
		
		System.out.println(userVO.getName());
		System.out.println(userVO.getAge());
		System.out.println(userVO.getAddr());
		System.out.println(userVO.getPhone());
		
		return "redirect:/user/list.do";
	}
	
	@RequestMapping(value="/fileupload.do", method=RequestMethod.GET)
	public String fileupload()
	{
		return "fileupload";
	}
	
	@RequestMapping(value="/fileupload.do", method=RequestMethod.POST)
	public String fileupload(MultipartFile uploadFile
			,String t1
			, String t2
			,HttpServletRequest req) throws Exception{
		
		String realPath = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		System.out.println("realPath::::"+realPath);
		
		System.out.println("t1::::"+t1);
		System.out.println("t2::::"+t2);
		String path = "D:/CJH/sts-workspace/fullstack2/springStudy/src/main/webapp/resources/upload";
		//업로드 위치 폴더 존재 확인
		File dir = new File(realPath);
		if(!dir.exists()) 
		{
			dir.mkdirs();
		}
		
		if(!uploadFile.getOriginalFilename().isEmpty()) 
		{
			
			String fileNM = uploadFile.getOriginalFilename();
			
			String fileNMArray[] = fileNM.split("\\.");
			String etc =  fileNMArray[fileNMArray.length-1];
			
			long timeMilis = System.currentTimeMillis();
			
			//test.jpg
			//start index : 0 , end index :4 => 8- 4
			String newFileNM 
			= fileNM.substring(0,fileNM.length()-etc.length()-1)+timeMilis+"."+etc;
			
			uploadFile.transferTo(new File(realPath,newFileNM));
			
			return "redirect:/?fileNM="+newFileNM;
			
		}
		
		return "redirect:/";
	}
}