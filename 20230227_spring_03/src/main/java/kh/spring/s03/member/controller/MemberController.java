package kh.spring.s03.member.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.s03.member.model.service.MemberService;
import kh.spring.s03.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv, MemberVo vo) {
		mv.setViewName("member.signUp");
		return mv;
	}
	
	@PostMapping("/signUp")
	public ModelAndView insert(
			ModelAndView mv,
			MemberVo vo,
			RedirectAttributes rttr //방법 3.
			) throws Exception{
		int result = -1;
//		try {
			result = service.insert(vo);
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		} 
		if(result > 0) {
//			회원가입 성공, redirect는 데이터를 싣고 갈 수 없으니까 쿼리스트링으로 표시해준다.
//			방법 1. (좋지않은 예, 사용불가)
//			mv.setViewName("redirect:/?msg=회원가입성공");
//			방법 2. (가장 기본적인 방법)
//			mv.addObject("msg", "회원가입성공");
//			mv.setViewName("error/errorFailure");
//			방법 3. Spring에서만 1회성으로 싣고 가지만 컨트롤러에서 꺼낼 수 없다. 컨트롤로->JSP로 데이터 전달이 기본이다.
			rttr.addFlashAttribute("msg", "회원가입성공2");
			mv.setViewName("redirect:/");
		} else {
//			회원가입 실패
//			방법 3 - Spring
			rttr.addFlashAttribute("msg", "회원가입실패");
			mv.setViewName("redirect:/member/signUp");			
		}
		return mv;
	}
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv
			// @RequestParam은 생략해도 된다. Spring이 알아서 해주니까, 아이디를 반드시 들고들어와야 하면 사용
			//@RequestParam("id") String id: id 라는 이름의 Parameter 꼭 있어야함. require=true 임
			, @RequestParam("id") String id // String id = request.getParameter("id");
//			, @RequestParam("aaa") int bbb  // String bbb = request.getParameter("aaa"); 해줄 경우 꼭 써줘야 한다.
//			, @RequestParam(name="ccc", required= false, defaultValue = "100") int ccc  // String ccc = request.getParameter("ccc");
			)throws Exception {
		MemberVo vo = service.selectOne(id);
		mv.addObject("membervo", vo);
		mv.setViewName("/member/update");
		return mv;
	}
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv) {
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv
			, String id
			)throws Exception {
		service.delete(id);
		return mv;
	}
//	TODO ("/info/{id}")
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv, String id)throws Exception {
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		MemberVo result = service.selectOne(id);
		mv.addObject("membervo",result);
		mv.setViewName("member/info");
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv)throws Exception{
		List<MemberVo> result = service.selectList();
		mv.addObject("memberlist", result);
		return mv;
	}
	
	//에러 종류별로 만들어 줄 수 있다. 안정화된 코드 상태에선 사용하기 좋지만, 현재 우리 상태에 적용하기엔 안좋음
	//프로젝트 중 후반에 사용하길 권장하고 그 전에는 위의 주석처리한 try-catch로 처리 하기.
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView memberNullPointExceptionHandler(NullPointerException e) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+"오류가 발생했습니다. 다시 시도해 주세요");
		mv.setViewName("error.error500");
		return mv;
	}
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView memberNumberFormatExceptionHandler(NumberFormatException e) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+"오류가 발생했습니다. 다시 시도해 주세요");
		mv.setViewName("error.error500");
		return mv;
	}
	@ExceptionHandler(SQLException.class)
	public ModelAndView memberSQLExceptionHandler(SQLException e) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+"오류가 발생했습니다. 다시 시도해 주세요");
		mv.setViewName("error.error500");
		return mv;
	}	
	@ExceptionHandler(Exception.class)
	public ModelAndView memberExceptionHandler(Exception e) {
		//오류 발생함. ModelAndView mv 
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+"오류가 발생했습니다. 다시 시도해 주세요");
		mv.setViewName("error.error500");
		return mv;
	}
}
