package com.portfolio.academy.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.academy.model.UserVO;
import com.portfolio.academy.service.RegnlogSrv;

@Controller
public class RegnlogCtr {
	
	@Autowired
	RegnlogSrv regnlogSrv;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	// 회원가입 화면 보여주기
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String getRegister() {
		return "register";
	}
	
	// 회원가입 시, 아이디 중복 체크
	@RequestMapping(value = "/checkSameId", method = RequestMethod.POST)
	@ResponseBody
	public String checkSameId(@RequestParam String userId) {
		int count = regnlogSrv.checkSameId(userId);
		String result;
		
		// count : 0=같은아이디없음, 1=같은아이디있음
		if( count > 0 ) {
			result = "failure";
		}else {
			result = "success";
		}
		return result;
	}
	
	// 회원가입 INSERT
	@RequestMapping(value = "/setRegister", method = RequestMethod.POST)
	@ResponseBody
	public String setRegister(@ModelAttribute UserVO uvo) {
		// System.out.println(uvo.getUserId() + uvo.getUserPwd() + uvo.getUserName() + uvo.getUserGender() + uvo.getUserPhone() + uvo.getUserEmail() );
		int count = regnlogSrv.checkSameId(uvo.getUserId()); // 아이디중복확인
		
		// 비밀번호 암호화
		String pwd = pwdEncoder.encode(uvo.getUserPwd());
		uvo.setUserPwd(pwd);
		
		if(count > 0) {
			// 아이디가 중복
			return "failure";
		}else {
			// 아이디 유일 -> 회원가입 가능
			regnlogSrv.setRegister(uvo);
			return "success";
		}
	}

	// 로그인 화면 보여주기
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String getLogin(HttpSession session, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		
		if( session.getAttribute("userName") != null ) {
			out.append("<script>alert('이미 로그인 된 상태입니다');");
			out.append("location.href='main.do';</script>").flush();
		}
		
		return "login";
	}
	
	// 로그인하기
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(@ModelAttribute UserVO uvo,HttpSession session) {
//		System.out.println("입력된 userId : " + uvo.getUserId() + ",입력된 userPwd : " + uvo.getUserPwd());
		ModelAndView mav = new ModelAndView();
		
		String pwd = regnlogSrv.getPassword(uvo);
		boolean pwdMatch = pwdEncoder.matches(uvo.getUserPwd(), pwd);
		
		if( pwd != null && pwdMatch == true  ) {
			uvo.setUserPwd(pwd);
			regnlogSrv.loginCheck(uvo, session);
			mav.setViewName("redirect:/main.do");
		}else {
			mav.addObject("msg", "아이디/비밀번호를 확인하세요.");
			mav.setViewName("/login");
		}
		
		return mav;
	}
	
	// 로그아웃하기
	@RequestMapping("/logout.do")
	public void logout(HttpSession session, HttpServletResponse response) throws Exception {
		regnlogSrv.logout(session);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append("<script>alert('정상적으로 로그아웃 되었습니다.');");
		out.append("location.href='login.do';");
		out.append("</script>").flush();
	}
	
}
