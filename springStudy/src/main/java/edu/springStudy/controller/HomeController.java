package edu.springStudy.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
