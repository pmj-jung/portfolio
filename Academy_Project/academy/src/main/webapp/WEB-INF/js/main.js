  /* 상담예약 hover 이벤트 */
  var a = document.getElementById("consulting");
  var b = a.childNodes;
  $(a).mouseover(function(){
    $(b).css('color','black');
  });

  $(a).mouseout(function(){
    $(b).css('color','gray');
  });

  /* 전화번호 hover 이벤트 */
  var x = document.getElementById("calling");
  var y = x.childNodes;

  $(x).mouseover(function(){
    $(y).css('color','black');
  });

  $(x).mouseout(function(){
    $(y).css('color','gray');
  });
