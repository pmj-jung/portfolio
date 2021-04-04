<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_tbl.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">

<!-- 읽기 권한 처리 -->
<c:choose>
	<c:when test="${sessionScope.userAuth eq null && board.boardRead > 0}">
		<script>
			alert("게시판 접근권한이 없습니다.");
			location.href="main.do"
		</script>
	</c:when>
	<c:when test="${sessionScope.userAuth < board.boardRead}">
		<script>
			alert("게시판 접근권한이 없습니다.");
			location.href="main.do"
		</script>
	</c:when>
</c:choose>
<!-- 읽기 권한 처리 -->

<body>
<input type="hidden" value="${board.boardCode}" id="hiddenCode" />
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box flex justify_c_between">
    <%@ include file="/WEB-INF/views/include/MENU.jsp" %>
    <div class="w_100 p_10">
      <h2>${board.boardName}</h2>
      <p class="gray font_14 p_tb10">${board.boardDesc}</p>
      <div class="flex justify_c_between m_b10">
        <div class="btn_wrap white_s_no flex justify_c_between">
          <a href="${pageContext.request.contextPath}/board.do?boardCode=${board.boardCode}"><button type="button" class="btn_all active">전체글</button></a>
         	<ul class="search_wrap white_s_no p_l2">
            <li>
              <select class="sel_100" id="pageChange" onChange="changePage();">
              	<option value="5" <c:if test="${paging.cntPerPage == 5}">selected</c:if>>5개</option>
                <option value="10" <c:if test="${paging.cntPerPage == 10}">selected</c:if>>10개</option>
                <option value="15" <c:if test="${paging.cntPerPage == 15}">selected</c:if>>15개</option>
              </select>
            </li>
           </ul>
        </div>
        <form method="post" autocomplete="off">
          <ul class="search_wrap white_s_no">
            <li>
              <select class="sel_100" id="searchOpt" name="searchOpt">
                <option value="all" <c:if test="${searchOpt == 'all'}">selected</c:if>>전체</option>
                <option value="article_subject" <c:if test="${searchOpt == 'article_subject'}">selected</c:if>>제목</option>
                <option value="article_content" <c:if test="${searchOpt == 'article_content'}">selected</c:if>>내용</option>
                <option value="user_name" <c:if test="${searchOpt == 'user_name'}">selected</c:if>>글쓴이</option>
              </select>
            </li>
            <li><input type="text" class="input_100" id="searchWords" name="searchWords" placeholder="입력" value="${searchWords}"/></li>
            <li><button type="submit" class="btn_search" id="btn_search" name="btn_search">검색</button></li>
          </ul>
        </form>
      </div>
      <table class="w_100 board_tbl">
        <thead>
          <tr>
          	<c:if test="${sessionScope.userAuth >= 4}">
            	<th class="td_5"><input type="checkbox" id="checkAll"/></th>
            </c:if>
            <th class="td_5">번호</th>
            <th class="td_5">말머리</th>
            <th class="td_30">제목</th>
            <th class="td_10">글쓴이</th>
            <th class="td_10">작성일</th>
            <th class="td_5">조회</th>
            <th class="td_5">추천</th>
          </tr>
        </thead>
        <tbody>
        	<c:if test="${empty article}">
        		<tr class="noAction" style="height:200px;">
        			<td colspan="8" >작성된 게시글이 없습니다.</td>
        		</tr>
        	</c:if>
        	<c:forEach var="article" items="${article}" varStatus="status">
	          <tr>
	          	<c:if test="${sessionScope.userAuth >= 4}">
		            <td>
		              <input type="checkbox" class="chk" data-uid="${article.aid}"/>
		            </td>
	            </c:if>
	            <td>${article.aid}</td>
	            <td>
	            	<c:choose>
	            		<c:when test="${article.articleCategory eq 0}"><span>일반</span></c:when>
	            		<c:otherwise><span style="color:#f00;">공지</span></c:otherwise>
	            	</c:choose>
            	</td>
	            <td class="board_subject left">
	            	<c:choose>
		            	<c:when test="${article.articleSecret eq 0}">
		            		<c:forEach begin="1" end="${article.articleLayer}">
                    	&nbsp;<img src="${pageContext.request.contextPath}/img/icon_reply.gif" />
                    </c:forEach>
                    
		            		<a href="${pageContext.request.contextPath}/articleView.do?boardCode=${board.boardCode}&aid=${article.aid}">
			            		<c:choose>
			            			<c:when test="${article.articleSubject eq 'deleted'}">삭제된 게시글입니다.</c:when>
			            			<c:otherwise>${article.articleSubject}</c:otherwise>
			            		</c:choose>
		            		</a>
		            		
		            		<c:if test="${article.commentCount ne 0}">
		            			<span class="count font_12 m_l2">[${article.commentCount}]</span>
		            		</c:if>
	            		</c:when>
	            		<c:when test="${article.articleSecret eq 1 && article.articleWriter eq sessionScope.uid || sessionScope.userAuth >= 3}">
	            			<a href="${pageContext.request.contextPath}/articleView.do?boardCode=${board.boardCode}&aid=${article.aid}">${article.articleSubject}</a>
	            			<c:if test="${article.commentCount ne 0}">
		            			<span class="count font_12 m_l2">[${article.commentCount}]</span>
		            		</c:if>
	            		</c:when>
	            		<c:otherwise>
	            			<c:forEach begin="1" end="${article.articleLayer}">
                    	&nbsp;<img src="${pageContext.request.contextPath}/images/icon_reply.gif" />
                    </c:forEach>
	            			<span class="gray">비밀글입니다</span>
	            		</c:otherwise>
            		</c:choose>
            		<c:if test="${not empty article.articleFileName}"><i class="fas fa-paperclip font_12"></i></c:if>
            	</td>
	            <td class="board_writer" data-uid="${article.articleWriter}">${article.articleWriterName}</td>
	            <td>${article.articleRegdate}</td>
	            <td>${article.articleHit}</td>
	            <td>${article.articleLikes}</td>
	          </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="tbl_btm m_t10">
      	<c:if test="${sessionScope.userAuth >= 4}">
        	<button type="button" class="btn_delete" id="deleteAll">선택삭제</button>
        </c:if>
        <c:if test="${sessionScope.userAuth >= board.boardWrite}">
	        <button type="button" class="btn_write" id="btn_write" onClick="location.href='articleWrite.do?boardCode=${board.boardCode}';">
	          <i class="fas fa-pencil-alt"></i> 글쓰기</button>
        </c:if>
        <div class="page_grp white_s_no">
          <%@ include file="/WEB-INF/views/include/PAGING.jsp" %>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/js/nav.js"></script>
<script src="${pageContext.request.contextPath}/js/board.js"></script>
</html>