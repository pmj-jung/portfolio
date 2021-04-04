<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<oi>
 	<c:if test="${paging.nowPage != paging.startPage}">
 		<a href="${pageContext.request.contextPath}/board?boardCode=${board.boardCode}&nowPage=1&cntPerPage=${paging.cntPerPage}">
 			<li><i class="fas fa-angle-double-left"></i></li>
 		</a>
 	</c:if>
 	<c:if test="${paging.startPage != 1}">
   	<a href="${pageContext.request.contextPath}/board?boardCode=${board.boardCode}&nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">
   		<li><i class="fas fa-angle-left"></i></i></li>
   	</a>
   </c:if>
   <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="page">
   	<c:choose>
   		<c:when test="${page == paging.nowPage}">
   				<li class="page_active">${page}</li>
   		</c:when>
   		<c:when test="${page != paging.nowPage}">
   			<a href="${pageContext.request.contextPath}/board?boardCode=${board.boardCode}&nowPage=${page}&cntPerPage=${paging.cntPerPage}">
   				<li>${page}</li>
  				</a>
  			</c:when>
   	</c:choose>
   </c:forEach>
   <c:if test="${paging.endPage != paging.lastPage}">
   	<a href="${pageContext.request.contextPath}/board?boardCode=${board.boardCode}&nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">
   		<li><i class="fas fa-angle-right"></i></li>
   	</a>
   </c:if>
   <c:if test="${paging.nowPage != paging.lastPage && paging.lastPage > 0 }">
   	<a href="${pageContext.request.contextPath}/board?boardCode=${board.boardCode}&nowPage=${paging.lastPage}&cntPerPage=${paging.cntPerPage}">
   		<li><i class="fas fa-angle-double-right"></i></li>
   	</a>
 	</c:if>
 	
 	<input type="hidden" id="nowPage" value="${paging.nowPage}"/>
</oi>