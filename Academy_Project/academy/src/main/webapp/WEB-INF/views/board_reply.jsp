<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_tbl.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<!-- 비회원 글쓰기 방지 -->
<c:choose>
	<c:when test="${empty sessionScope.uid}">
		<script>
			alert("게시글 작성권한이 없습니다.");
			location.href="main"
		</script>
	</c:when>
</c:choose>
<!-- 비회원 글쓰기 방지 -->
<body>
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box flex justify_c_between">
    <%@ include file="/WEB-INF/views/include/MENU.jsp" %>
    <div class="w_100 p_10">
      <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/articleReply" autocomplete="off">
      	<input type="hidden" name="articleRef" value="${article.articleRef}" />
      	<input type="hidden" name="articleLevel" value="${article.articleLevel}" />
      	<input type="hidden" name="articleLayer" value="${article.articleLayer}" />
      	<div class="write_top">
          <h2>게시글 작성</h2>
          <div class="flex justify_c_between m_tb10 w_100">
            <select class="sel_68" id="boardCode" name="boardCode">
              <c:forEach var="boardList" items="${boardList}">
              	<c:if test="${(sessionScope.userAuth >= boardList.boardRead && sessionScope.userAuth >= boardList.boardWrite) || (boardList.boardRead eq 0 && boardList.boardWrite eq 0)}">
              		<option value="${boardList.boardCode}" <c:if test="${boardList.boardCode ==  boardCode}">selected</c:if>>${boardList.boardName}</option>
              	</c:if>
              </c:forEach>
            </select>
            <select class="sel_31" id="articleCategory" name="articleCategory">
              <option value="0">일반</option>
              <option value="1">공지</option>
            </select>
          </div>
          <div class="m_b10 flex justify_c_between">
            <input type="text" class="input_box w_80" id="articleSubject" name="articleSubject" value="[답글] ${article.articleSubject}" />
            <select class="sel_19" name="articleSecret" style="display: inline-block;">
              <option value="0">공개</option>
              <option value="1">비공개</option>
            </select>
          </div>
          
         	<c:choose>
		     		<c:when test="${empty sessionScope.uid}">
		     			<div class="m_b10 flex justify_c_between">
		     				<input type="file" class="input_box w_80" name="files" />
		     				<input type="text" class="input_box w_19" name="articleWriter" placeholder="비회원이름" required/>
		     			</div>
		     		</c:when>
		     		<c:otherwise>
		     			<div class="m_b10">
		     				<input type="file" class="input_box w_100" name="files" />
		     				<input type="hidden" name="articleWriter" value="${sessionScope.uid}"/>
		     			</div>
		     		</c:otherwise>
		     	</c:choose>
        </div>
        <textarea class="form-control" id="articleContent" name="articleContent"></textarea>
        <div class="btn_wrap m_t10 flex justify_c_between">
          <div>
            <button type="button" class="btn_reset" onclick="history.go(-1);">이전으로</button>
          </div>
          <div>
            <button type="submit" class="btn_submit" id="btn_submit">등록</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/js/nav.js"></script>
<script type="text/javascript">
  CKEDITOR.replace('articleContent' , {height: 380});
</script>
</html>