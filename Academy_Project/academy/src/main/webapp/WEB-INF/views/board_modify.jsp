<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_tbl.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<body>
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box flex justify_c_between">
    <%@ include file="/WEB-INF/views/include/MENU.jsp" %>
    <div class="w_100 p_10">
      <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/articleModify.do" autocomplete="off">
      	<!-- 수정전 글 정보 -->
        <input type="hidden" name="articleWriter" value="${sessionScope.uid}"/>
        <input type="hidden" name="aid" value="${article.aid}"/>
        <!-- 수정전 글 정보 -->
        <div class="write_top">
          <h2>게시글 수정</h2>
          <p class="gray font_14 p_t5">게시판 위치는 수정하실 수 없습니다. 수정을 원하신다면 해당 글을 삭제후 새로 작성해주세요.</p>
          <div class="flex justify_c_between m_tb10 w_100">
            <select class="sel_68" id="boardCode" name="boardCode" onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
              <c:forEach var="boardList" items="${boardList}">
              	<c:if test="${sessionScope.userAuth >= boardList.boardRead && sessionScope.userAuth >= boardList.boardWrite}">
              		<option value="${boardList.boardCode}" <c:if test="${boardList.boardCode ==  boardCode}">selected</c:if>>${boardList.boardName}</option>
              	</c:if>
              </c:forEach>
            </select>
            <select class="sel_31" id="articleCategory" name="articleCategory">
              <option value="0" <c:if test="${article.articleCategory eq 0}">selected</c:if> >일반</option>
              <option value="1" <c:if test="${article.articleCategory eq 1}">selected</c:if> >공지</option>
            </select>
          </div>
          <div class="m_b10 flex justify_c_between">
            <input type="text" class="input_box w_80" id="articleSubject" name="articleSubject" value="${article.articleSubject}" />
            <select name="articleSecret" id="articleSecret" class="sel_19" style="display: inline-block;">
              <option value="0" <c:if test="${article.articleSecret eq 0}">selected</c:if>>공개</option>
              <option value="1" <c:if test="${article.articleSecret eq 1}">selected</c:if>>비공개</option>
            </select>
          </div>
          <div class="m_b10 input_box">
            <input type="file" class="w_100" name="files" />
          </div>
          <c:if test="${not empty article.articleFileName}">
	          <div class="m_b10 input_box">
		          <a href="${pageContext.request.contextPath}/articleDownload.do?filename=${article.articleFileName}">
		        		<span>${article.articleFileOrigin}</span>
		       		</a>
		       		<span class="gray font_14">새로운 첨부파일 업로드 할 경우, 이전의 첨부파일은 삭제됩니다.</span>
	       		</div>
       		</c:if>
        </div>
        <textarea class="form-control" id="board_content" name="articleContent">${article.articleContent}</textarea>
        <div class="btn_wrap m_t10 flex justify_c_between">
          <div>
            <button type="button" class="btn_reset" id="btn_back" onClick="history.go(-1);">이전으로</button>
          </div>
          <div>
            <button type="reset" class="btn_reset">취소</button>
            <button type="submit" class="btn_submit">등록</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/js/nav.js"></script>
<script type="text/javascript">
  CKEDITOR.replace('board_content' , {height: 380});
</script>
</html>