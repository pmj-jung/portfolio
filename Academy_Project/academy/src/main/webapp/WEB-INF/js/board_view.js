	$(function(){
		commentList();
	});
	
  function articleLikes() {
	  
	  var likesData = {
			  boardId : $("#boardId").val(),
			  articleId : $("#articleId").val(),
			  userId : $("#userId").val(),
			  boardCode : $("#boardCode").val()
	  }
	  
	  $.ajax({
		  url : "/academy/articleLikes.do",
		  type : "POST",
		  data : likesData,
		  success : function(resData){
			  location.reload();
		  },
		  error : function(){
			  alert("추천 기능 ajax 에러");
		  }
	  });
  }
  
  function delArticleOne(boardCode, aid) {
	  
	  var boardCode = $("#boardCode").val();
	  
	  $.ajax({
		  url : "/academy/delArticleOne.do",
		  type : "POST",
		  data : {
			  boardCode : boardCode,
			  aid : aid
		  },
		  success : function(resData){
			  alert("성공적으로 삭제되었습니다");
			 	location.href="board.do?boardCode=" + boardCode;
		  },
		  error : function(){
			  alert("ajax 에러");
		  }
	  });
  }
  
  $("#comment_btn").click(function(){
	  
	  if( $("#userId").val() == '0' ) {
		  alert("비회원은 댓글을 작성할 수 없습니다.");
		  $("#txt_comment").val('');
		  return false;
	  }
	  
	  if( $.trim($("#txt_comment").val()) == '' ){
		  alert("댓글 내용을 작성해주세요.");
		  $("#txt_comment").val('');
		  $("#txt_comment").focus();
		  return false;
	  }
	  
	  if( $.trim($("#txt_comment").val()) == 'deleted' ){
		  alert("잘못된 댓글 내용입니다.");
		  $("#txt_comment").val('');
		  $("#txt_comment").focus();
		  return false;
	  }
	  
		var formData = $("#cmntForm").serialize();
		$.ajax({
			url : "/academy/setComment.do",
			type : "POST",
			data : formData,
			success : function(resData) {
				commentList();
				$("#txt_comment").val('');
			},
			error : function(){
				alert("댓글 입력 오류발생");
			}
		});
  });
  
  function commentList(){
	  var formData = $("#cmntForm").serialize();
		
	  $.ajax({
		  url : "/academy/getCommentList.do",
		  type : "POST",
		  data : formData,
		  success : function(resData) {
			  var a = '';
			  a += '<p>댓글 <span class="count">'+ resData.count + '</span></p>';
				
			  var uid = $("#userId").val();
			  var userAuth = $("#userAuth").val();
			  
			  var b = '';
			  $.each(resData.list, function(key,value) {
				  
				  var writer = String(value.cmntWriter);
				  if( userAuth >= 3 && uid != writer ) {
					  
					  b += '<div class="comment_section p_tb10">';
					  	b += '<div class="flex justify_c_between">';
					  	
					  		b += '<div class="white_s_no">';
					  			for(var i=0;i < value.cmntLayer ;i++ ) {
					  				b += '<img src="/academy/img/icon_reply.gif" style="display:inline-block;"/>';
					  			}
                b += '</div>';
					  		
						  	b += '<div class="w_100">';
			          	b += '<div class="cmt_head m_b5">';
				            b += '<span class="p_r2">'+ value.cmntWriterName +'</span>';
				            b += '<span class="txt_regdate">'+ value.cmntRegdate +'</span>';
				            b += '<span class="a_wrap">';
				              b += '<a class="p_r5" href="javascript:void(0);" onClick="commentReply('+ value.cid +');">답글</a>';
				              b += '<a href="javascript:void(0);" onClick="commentDelete('+ value.cid +');">삭제</a>';
				            b += '</span>';
			          	b += '</div>';
	       		 			if( value.cmntContent == 'deleted' ) {
	          				b += '<p>삭제된 댓글입니다.</p>';
	          			}else {
	          				b += '<p>'+ value.cmntContent +'</p>';
	          			}
	     		 			b += '</div>';
     		 			b += '</div>';
       		 		b += '<div class="commentMod'+ value.cid +'"></div>';
		        b += '</div>';
		        
				  } else if( uid != writer ){
					  
						  b += '<div class="comment_section p_tb10">';
						  	b += '<div class="flex justify_c_between">';
					  			b += '<div class="white_s_no">';
						  			for(var i=0;i < value.cmntLayer ;i++ ) {
						  				b += '<img src="/academy/img/icon_reply.gif" style="display:inline-block;"/>';
						  			}
              		b += '</div>';
						  		b += '<div class="w_100">';
				          	b += '<div class="cmt_head m_b5">';
				            	b += '<span class="p_r2">'+ value.cmntWriterName +'</span>';
				            	b += '<span class="txt_regdate">'+ value.cmntRegdate +'</span>';
				            	b += '<span class="a_wrap">';
				              	b += '<a class="p_r5" href="javascript:void(0);" onClick="commentReply('+ value.cid +');">답글</a>';
				            	b += '</span>';
				          	b += '</div>';
			          		if( value.cmntContent == 'deleted' ) {
		          				b += '<p>삭제된 댓글입니다.</p>';
		          			}else {
		          				b += '<p>'+ value.cmntContent +'</p>';
		          			}
		          		b += '</div>';
   		 					b += '</div>';
			          b += '<div class="commentMod'+ value.cid +'"></div>';
			        b += '</div>';
				  } else {
					  
					  b += '<div class="comment_section p_tb10">';
					  	b += '<div class="flex justify_c_between">';
					  		b += '<div class="white_s_no">';
						  		for(var i=0;i < value.cmntLayer ;i++ ) {
					  				b += '<img src="/academy/img/icon_reply.gif" style="display:inline-block;"/>';
					  			}
      					b += '</div>';
      					b += '<div class="w_100">';
      						b += '<div class="cmt_head m_b5">';
				            b += '<span class="p_r2">'+ value.cmntWriterName +'</span>';
				            b += '<span class="txt_regdate">'+ value.cmntRegdate +'</span>';
				            b += '<span class="a_wrap">';
				              b += '<a class="p_r5" href="javascript:void(0);" onClick="commentReply('+ value.cid +');">답글</a>';
			              	b += '<a class="p_r5" href="javascript:void(0);" onClick="commentModify('+ value.cid +',\''+ value.cmntContent +'\')">수정</a>';
			              	b += '<a href="javascript:void(0);" onClick="commentDelete('+ value.cid +');">삭제</a>';
				            b += '</span>';
	          			b += '</div>';
	          			if( value.cmntContent == 'deleted' ) {
	          				b += '<p>삭제된 댓글입니다.</p>';
	          			}else {
	          				b += '<p>'+ value.cmntContent +'</p>';
	          			}
	         	 		b += '</div>';
	         	 	b += '</div>';
	          	b += '<div class="commentMod'+ value.cid +'"></div>';
		        b += '</div>';
				  }
				  
			  });
        
			  var c = '';
			  c += '댓글<span class="m_l3 count">'+ resData.count +'</span>';
        $("#comment_list").html(a+b);
        $("#commentCount").html(c);
		  },
		  error : function (){
			  alert("댓글 목록 불러오기 실패");
		  }
	  });
  
  }
  
  function commentDelete(cid) {
	  var formData = {
			  boardCode : $("#boardCode").val(),
				cid : cid
	  }

	  $.ajax({
		  url : "/academy/commentDelete.do",
		  type : "POST",
		  data : formData,
		  success : function(resData) {
			  commentList();
		  },
		  error : function(){
			  alert("댓글 삭제 중 오류가 발생했습니다.\n관리자에게 문의하세요.");
		  }
	  });
  }
  
  function commentModify(cid, comment) {
	  var a = '';
	  
	  a += '<form class="comment_post m_t20" autocomplete="off">';
      a += '<textarea name="comment_'+ cid +'" placeholder="댓글을 입력해주세요.">'+ comment +'</textarea>';
      a += '<div class="right">';
        a += '<button type="button" class="comment_btn" onClick="commentModifyProc('+ cid +')">수정</button>';
      a += '</div>';
    a += '</form>';
    $('.commentMod' + cid).html(a);
  }
  
  function commentModifyProc(cid){
	  var modifyContent = $('[name=comment_' + cid + ']').val();
	  
	  if( $.trim(modifyContent) == 'deleted' ) {
	  	alert("잘못된 댓글 내용입니다.");
	  	$('[name=comment_' + cid + ']').val('');
	  	$('[name=comment_' + cid + ']').focus();
	  	return false;
	  }
	  
	  var formData = {
			  cid : cid,
			  boardCode : $("#boardCode").val(),
			  cmntContent : modifyContent
	  };
	  
	  $.ajax({
		  url : "/academy/modifyComment.do",
		  type : "POST",
		  data : formData,
		  success : function(resData) {
			  commentList();
		  },
		  error : function(){
			  alert("댓글 수정 중 오류가 발생했습니다.\n관리자에게 문의하세요.");
		  }
	  });
  
  }
  
  function commentReply(cid){
		var a = '';
	  
	  a += '<form class="comment_post m_t20" autocomplete="off">';
      a += '<textarea name="commentReply_'+ cid +'" placeholder="내용을 입력해주세요."></textarea>';
      a += '<div class="right">';
        a += '<button type="button" class="comment_btn" onClick="commentReplyProc('+ cid +')">등록</button>';
      a += '</div>';
    a += '</form>';
    $('.commentMod' + cid).html(a);
    
  }
  
  function commentReplyProc(cid) {
	  var replyContent = $('[name=commentReply_' + cid + ']').val();
	  
	  if( $.trim(replyContent) == '' ) {
			alert('내용을 입력해주세요.');
			$('[name=commentReply_' + cid + ']').val('');
			$('[name=commentReply_' + cid + ']').focus();
	  }
	  
	  var formData = {
			  cid : cid,
			  articleId : $("#articleId").val(),
			  cmntWriter : $("#userId").val(),
			  boardCode : $("#boardCode").val(),
			  cmntContent : replyContent
	  };
	  
	  $.ajax({
	  	url : "/academy/commentReply.do",
		  type : "POST",
		  data : formData,
		  success : function(resData) {
			 	commentList();
		  },
		  error : function(){
			  alert("답글 등록 중 오류가 발생했습니다.\n관리자에게 문의하세요.");
		  }
	  });
	  
  }