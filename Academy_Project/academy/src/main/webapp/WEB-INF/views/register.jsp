<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/regnlog.css">
<body>
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <div class="content_box">
    <div class="login_wrapper flex flex_col justify_c_center align_i_center w_100 h_100">
      <div class="login_content p_30">
        <h2 class="center m_b20">회원가입</h2>
        <form id="regFrm" method="POST" autocomplete="off">          
          <div class="lgnIpBox flex justify_c_between">
            <span class="must"></span>
            <input class="p_l15" type="text" name="userId" id="userId" placeholder="아이디" tabindex="1"/>
            <button type="button" class="chkBtn white_s_no m_l10 p_lr7" id="chkId">중복확인</button>
          </div>
          <p class="font_12 p_l5 gray m_t5 m_b10">아이디는 4~16자리의 영문, 숫자만 사용하실 수 있습니다.</p>
          
          <div class="lgnIpBox">
            <span class="must"></span>
            <input class="p_l15" type="password" name="userPwd" id="userPwd" placeholder="비밀번호" tabindex="2"/>
          </div>
          <p class="font_12 p_l5 gray m_t5 m_b10">영문, 숫자 포함 10자 이상 20자 이내로 입력해주세요.</p>

          <div class="lgnIpBox">
            <span class="must"></span>
            <input class="p_l15" type="password" id="reUserPwd" placeholder="비밀번호 재확인" tabindex="3"/>
          </div>
          <p class="font_12 p_l5 gray m_t5 m_b10">비밀번호를 다시 한번 입력해주세요.</p>

          <div class="lgnIpBox flex justify_c_between m_b10">
            <span class="must"></span>
            <input class="p_l15" type="text" name="userName" id="userName" placeholder="이름" tabindex="4"/>
            <select class="m_l10" name="userGender" tabindex="5" >
              <option value="m">남자</option>
              <option value="f">여자</option>
            </select>
          </div>

          <div class="lgnIpBox">
            <input class="p_l15" type="text" name="userPhone" id="userPhone" placeholder="전화번호" tabindex="6"/>
          </div>
          <p class="font_12 p_l5 gray m_t5 m_b10">'-'기호 없이 숫자만 입력해주세요.</p>
          
          <div class="lgnIpBox m_b30">
            <input class="p_l15" type="text" name="userEmail" id="userEmail" placeholder="이메일" tabindex="7"/>
          </div>
          
          <p>
            <button class="submit_btn" id="regBtn" type="button" tabindex="8">회원가입</button>
          </p>

        </form>

        <div class="link flex justify_c_between p_tb10">
          <a href="${pageContext.request.contextPath}/login.do" tabindex="9">로그인</a>
          <a href="${pageContext.request.contextPath}/main.do" tabindex="10">메인으로</a>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src='${pageContext.request.contextPath}/js/register.js'></script>
</html>