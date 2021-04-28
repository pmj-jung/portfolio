$('#checkAll').click(function() {   
    if(this.checked) {
      $(':checkbox:not("input[name=unchk]")').each(function() {
          this.checked = true;
      });
    } else {
      $(':checkbox:not("input[name=unchk]")').each(function() {
          this.checked = false;
      });
    }
	});
	
	// 체크박스 이용한 다중삭제
	$("#deleteAll").click(function(){
		var msg = "선택하신 게시판의 모든 내용이 삭제됩니다. \n삭제하시겠습니까?";
		if(confirm(msg)){
			var chkArray = new Array();
			$(".chk:checked").each(function(){
				chkArray.push($(this).attr("data-uid"));
			});
			
			if( chkArray.length == 0 ) {
				alert("삭제할 게시판이 없습니다.");
				return false;
			}

			$.ajax({
				url		: "/academy/deleteBoard",
				type	: "POST",
				data	: {chkArr : chkArray},
				success	: function(resData){
					alert("선택하신 게시판이 삭제되었습니다.");
					window.location.reload();
				},
				error	: function(){
					alert("시스템 에러");
				}
			});
		}
	});

	$("#btn_submit").click(function() {
		
		// 1. 게시판코드 유효성검사
		var codeReg = /^[A-Za-z0-9+]{1,12}$/; // 영문대소문자+숫자 1~12자
		
		if( !codeReg.test( document.getElementById('boardCode').value ) ) {
			alert("게시판코드는 영문과 숫자를 사용하여 1~12자 사이여야 합니다.");
			$("#boardCode").val('');
			$("#boardCode").focus();
			return false;
		}
		
		// 2. 공백제거 후, 아무것도 쓰여있지않으면 ajax로 넘기지않음		
		if( $.trim($("#boardCode").val()) == '' ) {
			$("#boardCode").val();
			$("#boardCode").focus();
			return false;
		}
		
		if( $.trim($("#boardName").val()) == '' ) {
			$("#boardName").val();
			$("#boardName").focus();
			return false;
		}
		
		if( $.trim($("#boardDesc").val()) == '' ) {
			$("#boardDesc").val();
			$("#boardDesc").focus();
			return false;
		}
		
		$.ajax({
		  url : "/academy/setBoard",
		  type : "POST",
		  data : $("#board_frm").serialize(),
		  success : function(resData) {
			  if( resData == 'failure' ) {
				  alert( $("#boardCode").val() + " 게시판코드가 중복되었습니다.\n게시판코드를 변경해주세요.");
				  $("#boardCode").val('');
				  $("#boardCode").focus();
				  return false;
			  }else {
				  alert( $("#boardCode").val() + "게시판 & 댓글게시판이 생성되었습니다.");
					window.location.href = 	"/academy/boardSetting";
			  }
		  },
		  error : function(){
			  alert("ajax error!");
		  }
	  });
	});
  
  $(".btn_mod").click(function(){
	  $(".modal-wrapper").toggleClass("open");
  });

  function getBoardOne(boardCode){
	  
	  $.ajax({
		  url : "/academy/getBoardOne",
		  type : "POST",
			data : {"boardCode" : boardCode},
			success : function(resData) {
				$("#mBoardCode").val(resData.boardCode);
				$("#mBoardName").val(resData.boardName);
				$("#mBoardDesc").val(resData.boardDesc);
				$("#mBoardRead").val(resData.boardRead);
				$("#mBoardWrite").val(resData.boardWrite);
				$("#mBoardComment").val(resData.boardComment);
				$("#mBoardDownload").val(resData.boardDownload);
			},
			error : function() {
				alert("error!");
			}
	  });
  }
  
  $("#modal_reset").click(function(){
	  var boardCode = $("#mBoardCode").val();
	  getBoardOne(boardCode);
  });
  
  $("#modal_submit").click(function(){
	  var mBoardCode = $("#mBoardCode").val();
	  var mBoardRead = $("#mBoardRead").val();
	  if( mBoardCode == 'community') {
		  mBoardRead = 0;
		  alert("community 게시판의 읽기권한은 수정되지 않습니다.");
	  }
	  
	  if( mBoardCode == 'studyroom' ) {
		  mBoardRead = 2;
		  alert("studyroom 게시판의 읽기권한은 수정되지 않습니다.");
	  }
	  
	  var modalData = {
			"boardCode" : mBoardCode,
			"boardName" : $("#mBoardName").val(),
			"boardDesc" : $("#mBoardDesc").val(),
			"boardRead" : mBoardRead,
			"boardWrite" : $("#mBoardWrite").val(),
			"boardComment" : $("#mBoardComment").val(),
			"boardDownload" : $("#mBoardDownload").val()
	  };
	  
	  $.ajax({
		  url : "/academy/modifyBoard",
		  type : "POST",
		  data : modalData,
		  success : function(resData) {
			  if(resData == 'success') {
				  alert("게시판 설정을 성공적으로 변경하였습니다.");
				  window.location.href = '/academy/boardSetting';
			  }
		  },
		  error : function(){
			  alert("error");
		  }
	  });
	  
  });
  