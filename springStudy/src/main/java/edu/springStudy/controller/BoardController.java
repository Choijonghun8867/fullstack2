package edu.springStudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.springStudy.service.BoardService;
import edu.springStudy.vo.BoardVO;
import edu.springStudy.vo.SearchVO;
import edu.springStudy.vo.UserVO;

@RequestMapping(value="/board")
@Controller
public class BoardController 
{
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list.do")
	public String list(Model model, SearchVO searchVO)
	{
/*		List<String> list = new ArrayList<String>();
		
		list.add("첫번째 게시물입니다");
		list.add("두번째 게시물입니다");
		list.add("세번째 게시물입니다");
		list.add("네번째 게시물입니다");
*/
		List<BoardVO> list = boardService.list(searchVO);
		model.addAttribute("list", list);
		return "/board/list";
	}
	
	@RequestMapping(value="/view.do")
	public String view(int bidx, Model model)
	{
		BoardVO vo = boardService.selectOneByBidx(bidx);
		model.addAttribute("vo", vo);
		return "/board/view";
	}
	
	@RequestMapping(value="/write.do", method = RequestMethod.GET)
	public String write(HttpServletRequest req)
	{
			HttpSession session = req.getSession();
		
		UserVO loginVO = (UserVO)session.getAttribute("login");
		if(loginVO == null)
		{
			return "redirect:list.do";
		}
		return "/board/write";
	}
	
	@RequestMapping(value="/write.do", method = RequestMethod.POST)
	public String write(BoardVO boardVO, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		
		UserVO loginVO = (UserVO)session.getAttribute("login");
		
		//System.out.println(boardVo.toString()); toString 이용한 방법
		if(loginVO == null)
		{
			return "redirect:list.do";
		}
		boardVO.setId(loginVO.getId());//아이디를 받아온후 insert메소드 호출
		
		int result = boardService.insert(boardVO);
		
		System.out.println(boardVO.toString());
		
		return "redirect:view.do?bidx="+boardVO.getBidx();
	}
	
	@RequestMapping(value="/modify.do", method = RequestMethod.GET)
	public String modify(int bidx, Model model)
	{
		BoardVO vo = boardService.selectOneByBidx(bidx);
		model.addAttribute("vo", vo);
		return "/board/modify";
	}
	
	@RequestMapping(value="/modify.do", method = RequestMethod.POST)
	public String modify(BoardVO vo)
	{
		int result = boardService.update(vo);
		
		if(result > 0)	//수정성공
		{
			return "redirect:view.do?bidx="+vo.getBidx();
		}
		else	//수정실패
		{
			return "redirect:list.do";
		}
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public void delete(int bidx, HttpServletResponse res) throws IOException
	{
		int result = boardService.delete(bidx);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		
		if(result > 0)
		{
			pw.append("<script>alert('삭제되었습니다.');location.href='list.do';</script>");
		}
		else
		{
			pw.append("<script>alert('삭제되지 않았습니다.');location.href='list.do';</script>");
		}
		
		pw.flush();
	}
}
