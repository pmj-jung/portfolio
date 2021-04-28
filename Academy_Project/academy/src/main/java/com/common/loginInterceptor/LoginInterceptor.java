package com.common.loginInterceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		
		String path = request.getRequestURI();
		String query = request.getQueryString();
		if( query != null ) {
			path = path + "?" + query;
		}
		
		logger.info("[LoginInterceptor] path = " + path);
		
		if( session.getAttribute("userName") == null ) {
			PrintWriter out = response.getWriter();
			out.append("<script>alert('로그인이 필요한 서비스입니다');");
			out.append("location.href='login';");
			out.append("</script>").flush();
			out.close();
			return false;
		}
		
		return true;
		
	}

}
