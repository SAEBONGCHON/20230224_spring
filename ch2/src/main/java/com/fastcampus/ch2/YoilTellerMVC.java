package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//년월일을 입력하면 해당 요일을 알려주는 프로그
@Controller
public class YoilTellerMVC { //http://localhost:8090/ch2/getYoilMVC?year=2023&month=3&day=1
//	public static void main(String[] args) {
	@RequestMapping("/getYoilMVC")
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}

	public ModelAndView main(@RequestParam(required=true) int year
							,@RequestParam(required=true) int month
							,@RequestParam(required=true) int day) throws IOException{

		ModelAndView mv = new ModelAndView();
		mv.setViewName("yoilError");
		//1.유효성 검사
		if(!isValid(year, month, day))
			return mv;
		//2. 요일 계산
		char yoil = getYoil(year, month, day);
		//3. 계산된 결과를 model에 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		//4. 결과를 보여줄 view를 지정
		mv.setViewName("yoil");
		
		return mv;
	}

	private boolean isValid(int year, int month, int day) {

		return true;
	}	

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//1:일요일 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}
