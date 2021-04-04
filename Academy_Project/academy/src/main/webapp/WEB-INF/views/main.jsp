<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/HEAD.jsp" %>
<body>
  <%@ include file="/WEB-INF/views/include/HEADER.jsp" %>
  <section>
    <div class="img_box" style="background-image: url('${pageContext.request.contextPath}/img/main.jpg');">
      <span class="desc_box">
        <p class="white font_50">메인글1</p>
        <p class="white font_20">메인글2</p>
      </span>
    </div>
    <div class="sec_btm p_30 flex justify_c_between">
      <div class="btm_content">
        <a href="#">
          <div class="main_link" id="consulting">
            <i class="far fa-calendar-alt font_50 p_10 gray"></i>
            <p class="font_20 weight_700 gray">상담예약</p>
          </div>
        </a>
      </div>
      <div class="btm_content">
        <a href="#">
        	<div class="main_link" id="calling">
	        	<i class="fas fa-headset font_50 p_10 gray"></i>
	        	<p class="font_20 weight_700 gray">1588-####</p>
        	</div>
        </a>
      </div>
    </div>
  </section>
  <%@ include file="/WEB-INF/views/include/FOOTER.jsp" %>
</body>
<script src='${pageContext.request.contextPath}/js/main.js'></script>

</html>