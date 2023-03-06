package kh.spring.s03.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
			MemberVo vo
			) {

		int result = service.insert(vo);	
		if(result > 0) {
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("redirect:/member/signUp");			
		}
		return mv;
	}
	
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv
			, String id
			) {
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
	public ModelAndView delete(ModelAndView mv) {
//		TODO
		String id ="id등록";
		service.delete(id);
		return mv;
	}
//	TODO ("/info/{id}")
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv, String id) {
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		MemberVo result = service.selectOne(id);
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv){
		List<MemberVo> result = service.selectList();
		return mv;
	}
	
}
