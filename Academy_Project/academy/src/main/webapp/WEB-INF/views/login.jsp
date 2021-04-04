<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/regnlog.css">
<body>
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box">
    <div class="login_wrapper flex flex_col justify_c_center align_i_center w_100 h_100">
      <div class="login_content p_30">
        <h2 class="center m_b20">로그인</h2>
        <form method="POST" id="login_frm" action="${pageContext.request.contextPath}/login.do" autocomplete="off">
          <div style="color:#f00;font-size:14px;text-align:center;margin:10px 0;font-weight:700;">${msg}</div>
          <div class="lgnIpBox m_b10">
            <input class="p_l5" type="text" id="userId" name="userId" placeholder="아이디" />
          </div>
          <div class="lgnIpBox m_b20">
            <input class="p_l5" type="password" id="userPwd" name="userPwd" placeholder="비밀번호" />
          </div>
          <p>
            <button class="submit_btn" type="submit" id="submit_btn" name="submit_btn">로그인</button>
          </p>
          <div class="lgn_save m_tb10">
            <input type="checkbox" id="chk_save"/>
            <label for="chk_save">로그인 저장</label>
            <a href="${pageContext.request.contextPath}/register.do">회원가입</a>
          </div>
        </form>
        <div class="etc_text font_12 center m_t20">
          (학원이름) 웹 사이트 입니다.<br/>
          회원가입시 등록한 아이디와 비밀번호를 통해<br/>
          로그인 후 서비스 이용이 가능합니다.<br/><br/>
          <span>학원 관련문의 : </span>(관리자 연락처 또는 이메일)
        </div>
        <div class="center m_t10">
          <button class="btn_test" onClick="showTest();">테스트 계정</button>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src='${pageContext.request.contextPath}/js/login.js'></script>
</html>