<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>
<html>
<head>
	<title>Home</title>
</head>
<body>
<script>
	var msg= "${alertMsg}";
	//false : "", null, NAN
	if(msg){
		alert(msg);
	}
	var msg= "${msg}";
	if(msg){
		alert(msg2);
	}
</script>



<h1>
	Hello world!  
</h1>
20230228 잘되니? <br>
20230227
<P>  The time on the server is ${serverTime}. </P>
		 <textarea name="editor1" placeholder="내용"></textarea>
         <script>
              CKEDITOR.replace('editor1');
              $('#btnCheck').click(function(){
            	  console.log($("[name=editor1]").val());
            	  console.log($("[name=editor1]").html());
            	  console.log(CKEDITOR.instances.editor1.getData());
              })
         </script>
</body>

</html>
