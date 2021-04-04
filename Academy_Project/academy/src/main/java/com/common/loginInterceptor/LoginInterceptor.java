package com.common.loginInterceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	// private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
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
		
		// logger.info("[LoginInterceptor] path = " + path);
				
		if(path.contains("/main") || path.contains("/login") || path.contains("/register")) {
			return true;
		} else if( path.contains("/board") || path.contains("/articleView") || path.contains("/articleWrite") || path.contains("/articleDownload")
				 || path.contains("/getCommentList") ) {
			// 게시판의 (읽기/쓰기/댓글/다운로드) 권한이 비회원(0)이면 볼 수 있어야 한다.
			// 그러므로 로그인인터셉터를 통과시킨후, 뷰단에서 session값으로 통제한다.
			return true;
		} else if (session.getAttribute("userName") == null) {
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
