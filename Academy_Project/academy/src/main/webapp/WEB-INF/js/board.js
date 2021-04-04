
  /*
  	글쓴이 누르면 그 글쓴이의 정보를 볼 수 있게.. 
  $(".board_writer").click(function(){
	  var articleWriter = $(this).attr('data-uid');
  });
  */
  
  $('#checkAll').click(function() {   
    if(this.checked) {
      $(':checkbox').each(function() {
          this.checked = true;
      });
    } else {
      $(':checkbox').each(function() {
          this.checked = false;
      });
    }
	});
  
	//체크박스 이용한 다중삭제
	$("#deleteAll").click(function(){
		var msg = "선택하신 게시글이 삭제됩니다. \n삭제하시겠습니까?";
		if(confirm(msg)){
			var chkArray = new Array();
			$(".chk:checked").each(function(){
				chkArray.push($(this).attr("data-uid"));
			});
			
			var boardCode = $("#hiddenCode").val();
			
			if( chkArray.length == 0 ) {
				alert("삭제할 게시글이 없습니다.");
				return false;
			}
			
			$.ajax({
				url		: "/academy/deleteArticle.do",
				type	: "POST",
				data	: {
					boardCode : boardCode,
					chkArr : chkArray
				},
				success	: function(resData){
					alert("선택하신 게시글이 삭제되었습니다.");
					window.location.reload();
				},
				error	: function(){
					alert("시스템 에러");
				}
			});
		}
	});
	
	function changePage() {
		var boardCode = $("#hiddenCode").val();
		var nowPage = $("#nowPage").val();
		var sel = document.getElementById('pageChange').value;
		location.href="board.do?boardCode=" + boardCode + "&nowPage="+ nowPage + "&cntPerPage="+sel;
	}