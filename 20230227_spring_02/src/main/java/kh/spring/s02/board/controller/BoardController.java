package kh.spring.s02.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard(ModelAndView mv) {
		
		mv.addObject("boardlist", service.selectList());
		mv.setViewName("board/list");
		return mv;
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void viewUpdateBoard() {
		
	}
	@GetMapping("/delete")
	public void viewDeleteBoard() {
		//TODO
		int boardNum = 10;
		int result = service.delete(boardNum);
		
	}
	
	@GetMapping("/read")
	public void viewReadBoard() {
		//TODO
		int boardNum = 1;
		String writer = "user11";
		BoardVo vo = service.selectOne(boardNum, writer);
	}
	
	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(
			ModelAndView mv
		  , HttpServletRequest req
		  , HttpSession session
		  , BoardVo vo
		  ) {
		
		mv.setViewName("board/insert");		
		return mv;
	}
	
	//뷰페이지를 갔다가 post로 들어올거야	
//	@PostMapping("/insert")
	//TODO :
	@GetMapping("/insertPostTest")
	public ModelAndView doInsertBoard(ModelAndView mv
			, BoardVo vo
			) {
		vo.setBoardContent("임시내용");
		vo.setBoardTitle("임시제목");
		vo.setBoardWriter("user22");
		int result = service.insert(vo);
		
		return mv;
	}

//	GET,POST 구분없이 모든 값을 받아올때는 아래와같이 사용할 수 있다.
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {
		
		return mv;
	}
	
}
