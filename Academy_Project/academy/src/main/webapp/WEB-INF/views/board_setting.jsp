<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common_tbl.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_setting.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
<body>
<!-- ***************** 모달 ***************** -->
  <div class="modal-wrapper">
    <div class="modal flex flex-wrap" style="align-content: flex-start;">
    	<div class="flex justify_c_between w_100">
	    	<div class="contentHead">
	        <h2>게시판 설정 수정</h2>
	      </div>
	      <a href="" class="popup right font_20">
	        <i class="fas fa-window-close"> 창닫기</i>
	      </a>
    	</div>
      <div class="table-wrap m-t20 flex" style="width:100%;height:80vh;flex-wrap: nowrap; overflow-y: auto;">
        <form class="board_frm" id="modal_form" method="post" autocomplete="off">
          <table class="opt_tbl m_t10">
            <tr>
              <td class="td_title">게시판코드</td>
              <td class="td_opt">
              	<input type="text" class="input_100" id="mBoardCode" readonly placeholder="영문+숫자" /></td>
              <td class="td_title">게시판이름</td>
              <td class="td_opt" colspan="5">
              	<input type="text" class="input_100" id="mBoardName" placeholder="게시판 이름을 정해주세요." /></td>
            </tr>
            <tr>
              <td class="td_title">게시판설명</td>
              <td class="td_opt" colspan="7">
              	<input type="text" class="input_100" id="mBoardDesc" placeholder="게시판에 대한 설명을 적어주세요." /></td>
            </tr>
            <tr>
              <td class="td_title">읽기권한</td>
              <td class="td_15 td_opt">
                <select class="sel_100" id="mBoardRead">
                  <c:forEach var="userAuth" items="${userAuth}">
                    <option value="${userAuth.uaid}">${userAuth.authName}</option>
                  </c:forEach>
                </select>
              </td>
              <td class="td_title">쓰기권한</td>
              <td class="td_15 td_opt">
                <select class="sel_100" id="mBoardWrite">
                  <c:forEach var="userAuth" items="${userAuth}">
                    <option value="${userAuth.uaid}">${userAuth.authName}</option>
                  </c:forEach>
                </select>
              </td>
              <td class="td_title">댓글권한</td>
              <td class="td_15 td_opt">
                <select class="sel_100" id="mBoardComment">
                  <c:forEach var="userAuth" items="${userAuth}">
                    <option value="${userAuth.uaid}">${userAuth.authName}</option>
                  </c:forEach>
                </select>
              </td>
              <td class="td_title">다운권한</td>
              <td class="td_15 td_opt">
                <select class="sel_100" id="mBoardDownload">
                  <c:forEach var="userAuth" items="${userAuth}">
                    <option value="${userAuth.uaid}">${userAuth.authName}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </table>
          <div class="p_tb10 center">
            <button type="button" class="btn_reset" id="modal_reset">취소</button>
            <button type="button" class="btn_submit" id="modal_submit">등록</button>
          </div>
        </form>
      </div>
    </div>
  </div>
<!-- ***************** 모달 ***************** -->

  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box flex justify_c_between">
    <%@ include file="/WEB-INF/views/include/MENU.jsp" %>
    <div class="w_100 p_10">
      <h2>게시판 설정</h2>
      <form class="board_frm" id="board_frm" method="post" autocomplete="off">
        <table class="opt_tbl m_t10">
          <tr>
            <td class="td_title">게시판코드</td>
            <td class="td_opt">
            	<input type="text" class="input_100" id="boardCode" name="boardCode" placeholder="영문+숫자" />
           	</td>
            <td class="td_title">게시판이름</td>
            <td class="td_opt" colspan="5">
            	<input type="text" class="input_100" id="boardName" name="boardName" placeholder="게시판 이름을 정해주세요." />
           	</td>
          </tr>
          <tr>
            <td class="td_title">게시판설명</td>
            <td class="td_opt" colspan="7"><input type="text" class="input_100" id="boardDesc" name="boardDesc" placeholder="게시판에 대한 설명을 적어주세요." /></td>
          </tr>
          <tr>
            <td class="td_title">읽기권한</td>
            <td class="td_15 td_opt">
              <select id="boardRead" name="boardRead" class="sel_100">
                <c:forEach var="userAuth" items="${userAuth}">
                	<option value="${userAuth.uaid}">${userAuth.authName}</option>
                </c:forEach>
              </select>
            </td>
            <td class="td_title">쓰기권한</td>
            <td class="td_15 td_opt">
              <select id="boardWrite" name="boardWrite" class="sel_100">
                <c:forEach var="userAuth" items="${userAuth}">
                	<option value="${userAuth.uaid}">${userAuth.authName}</option>
                </c:forEach>
              </select>
            </td>
            <td class="td_title">댓글권한</td>
            <td class="td_15 td_opt">
              <select id="boardComment" name="boardComment" class="sel_100">
                <c:forEach var="userAuth" items="${userAuth}">
                	<option value="${userAuth.uaid}">${userAuth.authName}</option>
                </c:forEach>
              </select>
            </td>
            <td class="td_title">다운권한</td>
            <td class="td_15 td_opt">
            	
              <select id="boardDownload" name="boardDownload" class="sel_100">
                <c:forEach var="userAuth" items="${userAuth}">
                	<option value="${userAuth.uaid}">${userAuth.authName}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
        </table>
        <div class="p_tb10 center">
          <button type="reset" class="btn_reset">취소</button>
          <button type="button" class="btn_submit" id="btn_submit" name="btn_submit">등록</button>
        </div>
      </form>
      <div class="m_b10">
      	<h2>게시판 목록</h2>
      </div>
      <p class="white_s_no">게시판코드는 변경불가합니다. 기본으로 제공되는 community와 studyroom은 삭제하실 수 없고 읽기권한을 변경할 수도 없습니다.</p>
      <table class="w_100 board_list m_t10">
        <thead>
          <tr>
            <th><input type="checkbox" id="checkAll"/></th>
            <th>게시판코드</th>
            <th>게시판이름</th>
            <th>읽기</th>
            <th>쓰기</th>
            <th>댓글</th>
            <th>다운</th>
            <th>관리</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="board" items="${boardList}" varStatus="status">
	          <tr>
	            <td>
	            	<input type="checkbox" class="chk" id="chk" data-uid="${board.boardCode}" 
	            	<c:if test="${board.boardCode eq 'community' || board.boardCode eq 'studyroom' }">name="unchk" onClick="return false;"</c:if>/>
           		</td>
	            <td>${board.boardCode}</td>
	            <td>${board.boardName}</td>
	            <td>
	            	<select class="sel_100" onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
	                <c:forEach var="userAuth" items="${userAuth}">
	                	<option value="${userAuth.uaid}" <c:if test="${board.boardRead eq userAuth.uaid}">selected</c:if>>${userAuth.authName}</option>
	                </c:forEach>
              	</select>
            	</td>
	            <td>
	            	<select class="sel_100" onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
	                <c:forEach var="userAuth" items="${userAuth}">
	                	<option value="${userAuth.uaid}" <c:if test="${board.boardWrite eq userAuth.uaid}">selected</c:if>>${userAuth.authName}</option>
	                </c:forEach>
              	</select>
	            </td>
	            <td>
	            	<select class="sel_100" onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
	                <c:forEach var="userAuth" items="${userAuth}">
	                	<option value="${userAuth.uaid}" <c:if test="${board.boardComment eq userAuth.uaid}">selected</c:if>>${userAuth.authName}</option>
	                </c:forEach>
              	</select>
	            </td>
	            <td>
	            	<select class="sel_100" onFocus="this.initialSelect = this.selectedIndex;" onChange="this.selectedIndex = this.initialSelect;">
	                <c:forEach var="userAuth" items="${userAuth}">
	                	<option value="${userAuth.uaid}" <c:if test="${board.boardDownload eq userAuth.uaid}">selected</c:if>>${userAuth.authName}</option>
	                </c:forEach>
              	</select>
	            </td>
	            <td><button type="button" class="btn_mod" onClick="getBoardOne('${board.boardCode}');">수정</button></td>
	          </tr>
          </c:forEach>
        </tbody>
      </table>
      <button type="button" class="m_t10 btn_del" id="deleteAll">선택삭제</button>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/js/nav.js"></script>
<script src="${pageContext.request.contextPath}/js/board_setting.js"></script>
</html>