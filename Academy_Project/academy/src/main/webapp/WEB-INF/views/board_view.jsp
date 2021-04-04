<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_tbl.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">

<!-- 읽기 권한 처리 -->
<c:choose>
	<c:when test="${(sessionScope.userAuth eq null && board.boardRead > 0) || (sessionScope.userAuth <= board.boardRead) }">
		<script>
			alert("게시판 접근권한이 없습니다.");
			location.href="main";
		</script>
	</c:when>
	<c:when test="${empty article.aid || article.articleSubject eq 'deleted'}">
		<script>
		alert("존재하지 않는 게시글입니다.");
		location.href="board?boardCode=${board.boardCode}";
		</script>
	</c:when>
	<c:when test="${(article.articleSecret eq 1 && sessionScope.uid ne article.articleWriter && sessionScope.userAuth < 3)
									 || (article.articleSecret eq 1 && empty sessionScope.uid)}">
		<script>
			alert("해당 게시글에 대한 접근권한이 없습니다.");
			location.href="main";
		</script>
	</c:when>
</c:choose>
<!-- 읽기 권한 처리 -->
<body>
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box flex justify_c_between">
    <%@ include file="/WEB-INF/views/include/MENU.jsp" %>
    <div class="w_100 p_10">
      <div class="m_b10 content_wrap">
        <div class="btn_container white_s_no p_b10 flex justify_c_between">
          <div class="btn_box1">
            <button type="button" class="btn_all active" onClick="location.href='${pageContext.request.contextPath}/board?boardCode=${board.boardCode}'">목록</button>
            
          </div>
          <div class="btn_box2">
            <button type="button" class="btn_reply" onClick="location.href='${pageContext.request.contextPath}/articleReply?boardCode=${board.boardCode}&aid=${article.aid}';">답글</button>
            <c:if test="${sessionScope.uid eq article.articleWriter}">
            	<button type="button" class="btn_mod" onClick="location.href='${pageContext.request.contextPath}/articleModify?boardCode=${board.boardCode}&aid=${article.aid}'">수정</button>
            </c:if>
            <c:if test="${sessionScope.uid eq article.articleWriter || sessionScope.userAuth >= 3 }">
	            <button type="button" class="btn_del" onClick="delArticleOne('${board.boardCode}','${article.aid}');">삭제</button>
            </c:if>
          </div>
        </div>
        <div class="m_tb10">
          <ul>
            <li class="board_title"><a href="#">${board.boardName}</a></li>
            <li class="font_20">${article.articleSubject}</li>
            <li class="info_wrap">
              <ul>
                <li class="board_writer">${article.articleWriterName}</li>
                <li>추천 <span class="count">${article.articleLikes}</span></li>
                <li>조회 <span class="count">${article.articleHit}</span></li>
                <li>${article.articleRegdate}</li>
                <li id="commentCount"></li>
              </ul>
            </li>
          </ul>
        </div>
        <div style="border:1px solid #ddd;">
          <div class="board_post p_20 flex justify_c_between flex_col">
            <div class="m_b10">${article.articleContent}</div>
            <div class="flex justify_c_between">
              <button type="button" class="btn_recmd" id="btn_recmd" <c:if test="${not empty sessionScope.uid}">onClick="articleLikes();"</c:if>>추천<span class="p_l5 count">${article.articleLikes}</span></button>
              	<c:choose>
	               	<c:when test="${(sessionScope.userAuth >= board.boardDownload || board.boardDownload eq 0) && (not empty article.articleFileName)}">
	               		<div class="file_box">첨부파일 : 
		                	<a href="${pageContext.request.contextPath}/articleDownload?filename=${article.articleFileName}">
		                		<span>${article.articleFileOrigin}</span>
		               		</a>
	               		</div>
	             		</c:when>
	             		<c:when test="${empty article.articleFileName}"> - </c:when>
	             		<c:otherwise>
	             			<span>${article.articleFileOrigin}</span>
             			</c:otherwise>
             		</c:choose>
            </div>
          </div>
          <div class="comment_wrap">
          	<div id="comment_list">
	            
            </div>

            <form class="comment_post m_t20" id="cmntForm" autocomplete="off">
            	<c:choose>
            		<c:when test="${not empty sessionScope.uid}">
            			<input type="hidden" id="userId" name="cmntWriter" value="${sessionScope.uid}" />
            		</c:when>
            		<c:otherwise>
            			<input type="hidden" id="userId" name="cmntWriter" value="0" />
            		</c:otherwise>
            	</c:choose>
            	<input type="hidden" id="articleId" name="articleId" value="${article.aid}"/>
            	<input type="hidden" id="boardCode" name="boardCode" value="${board.boardCode}" />
            	<input type="hidden" id="userAuth" value="${sessionScope.userAuth}"/>
              <textarea id="txt_comment" name="cmntContent" placeholder="댓글을 입력해주세요."></textarea>
              <div class="right">
                <button type="button" class="comment_btn" id="comment_btn">등록</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
  <input type="hidden" id="boardId" value="${board.bid}"/>
</body>
<script src="${pageContext.request.contextPath}/js/nav.js"></script>
<script src="${pageContext.request.contextPath}/js/board_view.js"></script>
</html>