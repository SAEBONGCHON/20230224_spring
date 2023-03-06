package kh.spring.s03;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale
			, Model model
			, HttpSession session
//			, HttpServletRequest req
			) {
//		회원가입 성공해도 F5누르면 계속 회원가입성공이 뜨는 안좋은 방법임
//		String msg = req.getParameter("msg");
//		System.out.println(msg);
//		model.addAttribute("alertMsg", msg);
		
		logger.error("error Welcome home! The client locale is {}.", locale);
		logger.warn("warn Welcome home! The client locale is {}.", locale);
		logger.info("info Welcome home! The client locale is {}.", locale);
		logger.debug("debug Welcome home! The client locale is {}.", locale);
		logger.trace("trace Welcome home! The client locale is {}.", locale);
		
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
