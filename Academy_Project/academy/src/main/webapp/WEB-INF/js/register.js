
	function checkId(){
		var userid = $("#userId").val();
		var idReg = /^(?=.*[a-z])(?=.*[0-9]).{4,16}$/;
		if(!idReg.test(userid)){
		  alert("아이디는 4~16자 영문자와 숫자 조합이어야 합니다.");
		  $("#userId").val("");
		  $("#userId").focus();
		  return false;
		} else{
			return true;
		}
	}
	
	function checkPwd(){
		var userPwd = $("#userPwd").val();
		var pwdReg = /^(?=.*[a-z])(?=.*[0-9]).{10,20}$/;
		if(!pwdReg.test(userPwd)){
		  alert("비밀번호는 10~20자의 영문자와 숫자 조합이어야 합니다.");
		  $("#userPwd").val("");
		  $("#userPwd").focus();
		  return false;
		}else{
			return true;
		}
	}
	
	function checkRePwd(){
		var userPwd = $("#userPwd").val();
		var reUserPwd = $("#reUserPwd").val();
		if( userPwd != reUserPwd ){
			alert("비밀번호가 일치하지 않습니다.");
			$("#reUserPwd").val("");
			$("#reUserPwd").focus();
			return false;
		}else{
			return true;
		}
	}
	
	function checkName(){
		var nameReg = /^[가-힣a-zA-Z]{2,10}$/;
		var userName = $("#userName").val();
		if(!nameReg.test(userName)){
			alert("이름은 2~10자의 영문자,한글만 사용가능합니다.");
			$("#userName").val("");
			$("#userName").focus();
			return false;
		}else{
			return true;
		}
	}
	
	function checkPhone(){
		var phoneReg = /^01[0179][0-9]{7,8}$/; //01로 시작하여 그 다음은  0,1,7,9 중 하나와 매칭되는지 체크한뒤 7~8자리인지 검사
		var userPhone = $("#userPhone").val();
		if(!phoneReg.test(userPhone)){
			alert("유효하지 않은 전화번호입니다.");
			$("#userPhone").val("");
			$("#userPhone").focus();
			return false;
		}else{
			return true;
		}
	}
	
	function checkEmail(){
		var emailReg = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		// 이메일형식(aa11@aa.aa)
		var userEmail = $("#userEmail").val();
		if(!emailReg.test(userEmail)){
			alert("유효하지 않은 이메일주소입니다.");
			$("#userEmail").val("");
			$("#userEmail").focus();
			return false;
		}else{
			return true;
		}
	}
	
	$("#regBtn").click(function(){
		
		if( checkId() && checkPwd() && checkRePwd() && checkName() && checkPhone() && checkEmail()  ){
			$.ajax({
			  url   : "/academy/setRegister",
			  type  : "POST",
			  data  : $("#regFrm").serialize(),
			  success : function(resData){
				if(resData == "success"){
					alert("회원가입이 완료되었습니다.\n로그인 후 이용해주세요.");
					window.location.href ="/academy/login.do";
				}else{
					alert("이미 존재하는 아이디입니다.\n아이디를 변경해주세요.");
					$("#userId").val("");
					$("#userId").focus();
				}
			  },
			  error : function(){
			    alert("회원가입 에러 발생\n관리자에게 문의하세요.");
			    return false;
			  }
		 	});
		}
	});
	
	
	$("#chkId").click(function () {
      if (checkId() == true) {
        $.ajax({
          url: "/academy/checkSameId",
          type: "POST",
          data: { userId : $("#userId").val() },
          success: function (resData) {
            if (resData == "success") {
              alert("사용할 수 있는 아이디입니다.");
            } else {
              alert("이미 존재하는 아이디입니다.");
              $("#userId").val("");
              $("#userId").focus();
            }
          },
          error: function () {
            alert("아이디 중복체크 에러발생\n관리자에게 문의하세요");
          }
        });
      }
    });
