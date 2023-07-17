package edu.springStudy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/board")
@Controller
public class BoardController 
{
	@RequestMapping(value="/list.do")
	public String list(Model model)
	{
		List<String> list = new ArrayList<String>();
		
		list.add("첫번째 게시물입니다");
		list.add("두번째 게시물입니다");
		list.add("세번째 게시물입니다");
		list.add("네번째 게시물입니다");
		
		model.addAttribute("list", list);
		return "/board/list";
	}
	
	@RequestMapping(value="/view.do")
	public String view()
	{
		return "/board/view";
	}
	
	@RequestMapping(value="/view.do")
	public String show()
	{
		return "/board/view";
	}
}
