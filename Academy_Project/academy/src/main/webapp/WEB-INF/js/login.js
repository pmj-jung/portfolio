  // 쿠키 설정
  // 쿠키이름과 값, 유효날짜를 파라미터로 받아서 쿠키를 설정
  function setCookie(cookieName, value, exdays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null)? "" : ";expires=" + exdate.toLocaleString());
    document.cookie = cookieName + "=" + cookieValue;
  }

  // 쿠키 삭제
  // 쿠키이름만 받아서 그 쿠키의 유효기간을 이용하여 유효하지 않게 만들어서 삭제함
  function deleteCookie(cookieName) {
    var expireDate= new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "=" + ";expires=" + expireDate.toLocaleString();
  }

  // 쿠키 얻기
  // 쿠키이름을 받아서 그 쿠키에 들어있는 값을 반환
  function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1) {
      start += cookieName.length;
      var end = cookieData.indexOf(';',start);
      if(end == -1)end = cookieData.length;
      cookieValue = cookieData.substring(start,end);
    }
    return unescape(cookieValue);
  }
  
  function showTest() {
	  alert("관리자 : admin \n관리자 : auth4 \n직원 : auth3 \n수강생 : auth2\n대기회원 : auth1\n비밀번호는 아이디와 동일합니다.");
  }

  $(function() {
    var userInputId = getCookie("userInputId");
    
    $("input[name='userId']").val(userInputId);

    if( $("input[name='userId']").val() != "" ) {
      $("#chk_save").attr("checked", true);
    }

    $("#chk_save").change(function() {
      if( $("#chk_save").is(":checked")) {
        var userInputId = $("input[name='userId']").val();
        setCookie("userInputId", userInputId, 7);
      } else {
        deleteCookie("userInputId");
      }
    });

    $("input[name='userId']").keyup(function() {
      if( $("#chk_save").is(":checked") ){
        var userInputId = $("input[name='userId']").val();
        setCookie("userInputId", userInputId, 7);
      }
    });
  });