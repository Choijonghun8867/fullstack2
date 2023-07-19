package edu.springStudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.springStudy.service.BoardService;
import edu.springStudy.vo.BoardVO;

@RequestMapping(value="/board")
@Controller
public class BoardController 
{
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list.do")
	public String list(Model model)
	{
/*		List<String> list = new ArrayList<String>();
		
		list.add("ù��° �Խù��Դϴ�");
		list.add("�ι�° �Խù��Դϴ�");
		list.add("����° �Խù��Դϴ�");
		list.add("�׹�° �Խù��Դϴ�");
*/
		List<BoardVO> list = boardService.list();
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
	public String write()
	{
		return "/board/write";
	}
	
	@RequestMapping(value="/write.do", method = RequestMethod.POST)
	public String write(BoardVO boardVo)
	{
		System.out.println(boardVo.getBidx());
		System.out.println(boardVo.getBody());
		System.out.println(boardVo.getHit());
		System.out.println(boardVo.getId());
		System.out.println(boardVo.getTitle());
		System.out.println(boardVo.getWdate());
		
		//System.out.println(boardVo.toString()); toString �̿��� ���
		
		return "redirect:/board/list.do";
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
		
		if(result > 0)	//��������
		{
			return "redirect:view.do?bidx="+vo.getBidx();
		}
		else	//��������
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
			pw.append("<script>alert('�����Ǿ����ϴ�.');location.href='list.do';</script>");
		}
		else
		{
			pw.append("<script>alert('�������� �ʾҽ��ϴ�.');location.href='list.do';</script>");
		}
		
		pw.flush();
	}
}
