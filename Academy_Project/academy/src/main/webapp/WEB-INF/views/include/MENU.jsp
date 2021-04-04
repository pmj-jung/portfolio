<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <nav>
      <ul>
        <li class="brd_btn">
          <a href="#">
            <i class="fas fa-list-ul"></i>게시판 목록
            <span class="fas fa-caret-down first"></span>
          </a>
          <ul class="brd_show">
          	<c:forEach var="boardList" items="${boardList}" varStatus="status">
          		<c:if test="${sessionScope.userAuth >= boardList.boardRead || boardList.boardRead == 0}">
          			<li><a href="${pageContext.request.contextPath}/board.do?boardCode=${boardList.boardCode}">${boardList.boardName}</a></li>
          		</c:if>
          	</c:forEach>
          	<c:if test="${sessionScope.userAuth >= 4}">
            	<li><a href="${pageContext.request.contextPath}/boardSetting.do">게시판 관리</a></li>
          	</c:if>
          </ul>
        </li>
        <!-- 
        <li class="std_btn">
          <a href="#">
            <i class="fas fa-users"></i>회원관리
            <span class="fas fa-caret-down first"></span>
          </a>
          <ul class="std_show">
            <li><a href="#">회원 목록</a></li>
            <li><a href="#">회원 등록</a></li>
          </ul>
        </li>
         -->
      </ul>
    </nav>