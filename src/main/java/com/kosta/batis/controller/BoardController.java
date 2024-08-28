package com.kosta.batis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.batis.domain.BoardDTO;
import com.kosta.batis.domain.PageResolver;
import com.kosta.batis.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;	
	
	@GetMapping("/write")
	public String write(Model m) {
		m.addAttribute("mode", "new");	//board.jsp읽기와 쓰기에 사용, 쓰기에 사용할때는 mode=new
		return "board";
	}
	
	@PostMapping("/remove")
	public String remove(Integer bno, Integer page, Integer pageSize,RedirectAttributes rattr,HttpSession session) {
			String writer=(String) session.getAttribute("id");
			String msg="DEL_OK";
			try {
				if(boardService.remove(bno,writer) !=1)
					throw new Exception("Delete failed");
			} catch ( Exception e) {
				e.printStackTrace();
				msg="DEL_ERR";
			}
			
			rattr.addAttribute("page",page);
			rattr.addAttribute("pageSize",page);
//			rattr.addAttribute("msg",msg);
			rattr.addFlashAttribute("msg",msg);
			
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, Integer page, Integer pageSize, Model m) throws Exception{
		try {
			BoardDTO boardDTO=boardService.read(bno);
			m.addAttribute("boardDTO",boardDTO);
			m.addAttribute("page",page);
			m.addAttribute("pageSize",pageSize);
			
			
			// ...
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board/list";
		}
		
		return "/board";
	}
	
	@GetMapping("/list")
	public String list(Integer page,Integer pageSize,Model m, HttpServletRequest request) {
		//로그인 안했으면 로그인 화면으로 이동 
		if(!loginCheck(request))
			return "redirect:/login/login?toURL="+request.getRequestURL();
		
		try {
			if(page==null) page=1;
			if(pageSize==null)pageSize=10;
			
			int totalCnt = boardService.getCount();
			m.addAttribute("totalCnt",totalCnt);
			
			PageResolver pageResolver = new PageResolver(totalCnt,page,pageSize);
			m.addAttribute("pr",pageResolver);
			
			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			List<BoardDTO> list=boardService.getPage(map);
			
			m.addAttribute("list",list);
			
			m.addAttribute("page",page);
			m.addAttribute("pageSize",pageSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "boardList";	//로그인 한 상태이면 게시판  목록 화면으로 이동
	}

	private boolean loginCheck(HttpServletRequest request) {
		//세션을 얻어서 
		HttpSession session=request.getSession();
		//세션에 id가 있는지 확인, 있으면 true 반환
		return session.getAttribute("id")!=null; // true 반환 , null이면 false 반환 
	}
}
