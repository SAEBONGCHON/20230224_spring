<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
	<!--폼태그는 유니크해야 해서 class를 사용하지 않는다  -->
	<form id="frmInfo" method="get">
		<input name="id" value="${membervo.id }" type="text" readonly="readonly"><br>
		<input value="${membervo.passwd }" type="password"  placeholder="pass"><br>
		<input value="${membervo.name }" type="text"  readonly="readonly"><br>
		<input value="${membervo.email }" type="text"  placeholder="name"><br>
		<!--submit과 onclick은 동시에 사용하지 않는 것을 권장 HTML태그에 의한 온클릭 동작임.-->
		<!--HTML 입장에서 발행하는 것과 javascript 입장에서 발행하는 this의 의미가 다르다
		HTML : window
		javascript : event 발생한 element, 상당히 많은 곳에 활용이 가능하다.
		-->
		<button type="button" onclick="javascript:frmSubmit('update');">수정</button>
		<button type="button" onclick="frmSubmit('delete');">탈퇴</button>
		
		<button type="button">수 정</button>
		<button type="button">탈 퇴</button>
	</form>
	
	<script>
		//eq(제이쿼리) 와 get(자바스크립트)는 자료형의 차이
		console.log($("button").eq(2).text());
		console.log($("button").get(2).innerText);
		//방법2 first는 누군가의 첫재 자식인가를 묻는다. 그래서 first는 사용하지못하고 버튼들 중 첫번째 꺼내달라해야함
		$("button").eq(2).click('update' , frmSubmit2);
		$("button").eq(3).click('delete' , frmSubmit2);
		function frmSubmit2(event){ //이벤트 객체 모양으로 파라미터가 들어온다.
			console.log(this); // this: click이 발생한 시점에 element
			frmInfo.action = event.data;
			frmInfo.submit();
		}
		//방법1
		function frmSubmit(targetEle){
			console.log(this); // this: window 객체
			//form.action = targetEle; //단 이 방법은 한페이지에 form 태그가 한개 여야함
			frmInfo.action = targetEle;
			remInfo.submit();
			//위의 코드는 원래 아래와 같이 적어야하는데 줄여서 적을 수 있다.
			//document.getElementById("frmInfo").action = targetEle;
			//ducument.querySelector(".aaa.bbb[type=text]").action = targetEle;
			//ducument.querySelectorAll(".aaa")[0].action = targetEle;
			//$("#frmInfo").attr("action", targetEle);
			//$("#frmInfo").submit(); 
		}
	</script>
</body>
</html>