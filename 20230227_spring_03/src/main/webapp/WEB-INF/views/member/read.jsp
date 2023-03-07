<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
	<h1>${board.boardNum } 게시글</h1>
	<div>
	<p>${board.boarTitle }</p>
	</div>
	<div>
	${board.boarContent }
	</div>
	
	<!--계층형 게시판에는 추천하지 않는 답글작성 방법  -->
	<form id="frmReply">
	<fieldset>
		<legend>답글작성</legend>
		<div>제목<input type="text" name="boardTitle"></div>
		<div>내용<input type="text" name="boardContent"></div>
		<input type="hidden" name="boardNum" value="${board.boardNum }">
		<button type="button" class="btn reply">답글작성</button>
		<!-- <button type="reset">초기화</button> -->
	</fieldset>
	</form>
	<hr>
<h1>댓글</h1>
<table border="1" >
	<thead>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${replyList }" var="reply">
	<!--제목을 누르면 글읽기 화면으로 이동  -->
		<tr>
			<td>${reply.boardNum }</td>
			<td><a href="<%=request.getContextPath()%>/board/read?boardNum=${reply.boardNum }">${reply.boardTitle }</a></td>
			<td>${reply.boardWriter }</td>
			<td>${reply.boardDate }</td>
			<td>${reply.boardReadcount }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>	
<!-- 에이작스로 갈 것이다. -->
<script>
	$(".btn.reply").on("click", replyClickHandler );
	
	function replyClickHandler(){
		console.log(this); 		   //this는 원래 DOM 형태이다
		console.log($(this));	   // DOM형태의 this를 제이쿼리로 형변환
		//제이쿼리야 제이쿼리야 에이작스 메소드를 불러와줘
		console.log($("#frmReply").serialize());
		$.ajax({
			url:"<%=request.getContextPath()%>/board/insertReplyAjax"
			, type: "post"
			//contentType : 쿼리스트링일떄는 작성하지 않는다
			, data: $("#frmReply").serialize()
				/*원래 아래와 같이 써줘야하는데 form에 id를 걸어서 위와 같이 작성할 수 있다.!
				boardNum을 가져오기위해 form 태그에 input hidden을 추가해준다. 보안성을 높인다. 조작이 불가능해짐.
				  {boardTitle: $("#a").val(), boardContent: $("#b").val(), boardNum: '${board.boardNum }'} */
			, dataType: "json" //success에 들어오는 데이터가 json 모양일것이고 이것을 js object로 변경해서 return
			, success: function(result){
				console.log(result);
				console.log(result[0]);
				console.log(result[0].boardDate); //0번째의 키이름을 넣으면 밸류가 튀어 나온다.
				/*답글 작성 완료 후 작성된 글 초기화  */
				/* $("#frmReply").eq(0).reset();  */
				frmPeply.reset(); //자바스크립트 방식
				if(result.length > 0){
					alert("댓글 작성 되었습니다.")
				}else{
					alert("댓글작성이 되지 않았습니다. 다시 시도해 주세요.")					
				}
				// 답글 부분 화면 업데이트
				displayReply(result);
			}
			, error: function(){
			}
		}); 			
	}
	// 답글 부분 화면 업데이트
	function displayReply(result){
		console.log(result);

		var htmlVal = '';
		for(i = 0; i < result.length; i++){
			var reply = result[i];
			htmlVal += '<tr>';
			htmlVal += '<td>'+reply.boardNum+'</td>';
			htmlVal += '<td><a href="<%=request.getContextPath()%>/board/read?boardNum='+reply.boardNum+'">'+reply.boardTitle+'</a></td>';
			htmlVal += '<td>'+reply.boardWriter+'</td>';
			htmlVal += '<td>'+reply.boardDate+'</td>';
			htmlVal += '<td>'+reply.boardReadcount+'</td>';
			htmlVal += '</tr>';
		}
		$("tbody").html(htmlVal);
		
		

	}
</script>

</body>
</html>