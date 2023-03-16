package kh.spring.s03.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s03.board.model.service.BoardService;
import kh.spring.s03.board.model.vo.BoardVo;
import kh.spring.s03.common.file.FileUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	@Autowired 
	@Qualifier("fileUtil")
	private FileUtil fileUtil;
	
	private final static int BOARD_LIMIT = 5;
	private final static int PAGE_LIMIT = 3;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard(ModelAndView mv) {
		// TODO
		String searchWord = "답";
		int currentPage = 1;
		int totalCnt = service.selectOneCount(searchWord);
		int totalPage = (totalCnt%BOARD_LIMIT ==0)
						?(totalCnt /BOARD_LIMIT)
						:(totalCnt /BOARD_LIMIT)+1;
		int startPage = (totalCnt%BOARD_LIMIT ==0)
						?(currentPage/PAGE_LIMIT-1)*PAGE_LIMIT + 1
						:(currentPage/PAGE_LIMIT  )*PAGE_LIMIT + 1;
		int endPage =   (startPage + PAGE_LIMIT > totalPage)
					    ? totalPage :(startPage + PAGE_LIMIT);
		//잘 사용하진않지만 Map으로 묶어가기도함
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		mv.addObject("pageInfo", map);
		
//		mv.addObject("totalPage", totalPage);
//		mv.addObject("startPage", startPage);
//		mv.addObject("endPage", endPage);
//		mv.addObject("currentPage", currentPage);
		
		//TODO
		//검색단어는 제목, 내용, 작성자에서 포함되어 있으면 찾기
		//null 또는 ''은 검색하지 않음
//		String searchWord= "null";
//		String searchWord= "";
		
		mv.addObject("boardlist", service.selectList(currentPage, BOARD_LIMIT, searchWord));
		mv.setViewName("board/list");
		return mv;
	}
	//업데이트
	@GetMapping("/update")
	public void viewUpdateBoard() {
		
	}
	//업데이트
//	@PostMapping("/update")
	@GetMapping("/updatePostTest")
	public void updateBoard() {
//		TODO:
		int boardNum = 1;
		String boardTitle = "수정제목";
		String boardContent = "수정내용";
		String boardOriginalFilename = "";  //""파일없음(약속임)
		String boardRenameFilename = "";	//""파일없음(약속임)
		//기존의 내용을 알아오는게 쉽지않아서? 업데이트 할 수 있는 항목은 전부 업데이트한다.. 
		
		BoardVo vo = new BoardVo();
		vo.setBoardTitle(boardTitle);
		vo.setBoardContent(boardContent);
		vo.setBoardOriginalFilename(boardOriginalFilename);
		vo.setBoardRenameFilename(boardRenameFilename);
		int result = service.update(vo);
	}
	
	@GetMapping("/delete")
	public void viewDeleteBoard() {
		//TODO
		int boardNum = 10;
		int result = service.delete(boardNum);
		
	}
	
	
	// URL (반드시 쿼리스트링으로 표기해야할까?)
	// 1. /board/read?boardNum=27&replyPage=3
	//     location.href="board/read?boardNum=${elboardnum}&reply=${elboardnum}"
	// 2. /board/read/27/3
	//     location.href="board/read/${elboardnum}/${elboardnum}"
	
	
	//글 상세 읽기 화면
	@GetMapping("/read/{boardNum}")
	public ModelAndView viewReadBoard(
			ModelAndView mv
//			, @PathVariable int replyPage
			, @PathVariable int boardNum
			//, @RequestParam("boardNum") int boardNum
			) {
		//TODO
		String writer = "user11";
		
		BoardVo vo = service.selectOne(boardNum, writer);
		mv.addObject("board", vo);
		
		List<BoardVo> replyList = service.selectReplyList(boardNum);
		mv.addObject("replyList", replyList);
		
		mv.setViewName("board/read");
		return mv;
	}
	
	// 원글 작성페이지 이동
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
	
	//원글 작성
	//뷰페이지를 갔다가 post로 들어올거야	
	@PostMapping("/insert")
	public ModelAndView doInsertBoard(
			  MultipartHttpServletRequest multiReq
			, @RequestParam(name ="report", required = false) MultipartFile multi
			, HttpServletRequest request
			, ModelAndView mv
			, BoardVo vo
			) {
		Map<String, String> filePath;
		List<Map<String, String>> fileListPath;
		try {
//			fileListPath = fileUtil.saveFileList(multiReq, request, null);
			filePath = fileUtil.saveFile(multi, request, null);
			vo.setBoardOriginalFilename(filePath.get("original"));  
			vo.setBoardRenameFilename(filePath.get("original"));  
		} catch (Exception e) {
			e.printStackTrace();
		}
			vo.setBoardWriter("user22"); //TODO: 로그인한 계정
			int result = service.insert(vo);
			return mv;
	}
	/*
	 * 답글 작성 페이지이동
	 * 이때, 몇번 글에 답글을 쓸건지 글번호를 가지고 가야한다.
	 * 
	 */
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv
			, int boardNum //몇번글의 답글인지 들고가야함
			) {
		
		return mv;
	}
	
	/*
	 * 답글 작성
	 * 작성순서 : 화면으로부터 넘어올 수 있는건 TODO로 남기고 
	 * 
	 */
//	@PostMapping("/insertReply")
	@GetMapping("/insertReplyPostTest")
	public ModelAndView viewInsertReply(ModelAndView mv
			, BoardVo vo
		) {
		//TODO
		int boardNum = 4;
		vo.setBoardNum(boardNum);
		
		vo.setBoardContent("임시답내용");
		vo.setBoardTitle("임시답제목");
		vo.setBoardWriter("user22");
		
		service.insert(vo);
		
		
		return mv;
	}
	//Ajax : 왔던 곳으로 돌아온다. 다른페이지 이동 ㄴㄴ 기본임
	@PostMapping("/insertReplyAjax")
	@ResponseBody
	public String insertReplyAjax(BoardVo vo, MultipartFile report) {
		if(report != null) {
			System.out.println("파일없음");
		}
		System.out.println("~#@@##@#");
		System.out.println(vo);
		
//		int boardNum = 4;
//		vo.setBoardNum(boardNum);
		//		vo.setBoardContent("임시답내용");
//		vo.setBoardTitle("임시답제목");
		vo.setBoardWriter("user22");
		
		//답글 작성
		service.insert(vo);
		// 연관 답글들 조회해서 ajax로 return 해야함.
		List<BoardVo> replyList = service.selectReplyList(vo.getBoardNum());
//		mv.addObject("")
		
		
		return "ok";
	}
	
	
//	GET,POST 구분없이 모든 값을 받아올때는 아래와같이 사용할 수 있다.
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {
		
		return mv;
	}
	
//	@ExceptionHandler
}
