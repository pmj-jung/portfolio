<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<header class="flex justify_c_between align_i_center p_lr10">
  <span class="logo">
    <a href="${pageContext.request.contextPath}/main">학원이름</a>
  </span>
  <div class="flex justify_c_between">
  	<c:if test="${not empty sessionScope.userName}">
  		<span class="m_r15 font_15">${sessionScope.authName} ${sessionScope.userName} 님, 환영합니다</span>
	  </c:if>
	  <ul class="menu flex justify_c_between">
	  	<li class="m_r15"><a href="${pageContext.request.contextPath}/board?boardCode=community">커뮤니티</a></li>
	    <li class="m_r15"><a href="${pageContext.request.contextPath}/board?boardCode=studyroom">스터디룸</a></li>
	    <c:choose>
	    	<c:when test="${empty sessionScope.userName}">
				<li class="m_r15"><a href="${pageContext.request.contextPath}/login">로그인</a></li>
	    		<li><a href="${pageContext.request.contextPath}/register">회원가입</a></li>      	
	    	</c:when>
	    	<c:when test="${not empty sessionScope.userName}">
	    		<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
	    	</c:when>
	    </c:choose>
	  </ul>
  </div>
</header>