<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	

</style>
</head>
<body>
<h2>noticeUpdate</h2>
	
<form action="noticeUpdate" method="post">
	<input type="hidden" name="num" value="${dto.num }">
	<p>title : <input type="text" name="title" value="${dto.title}"></p>
	contents : <textarea rows="16" cols="20" name="contents">${dto.contents }</textarea>
	<button>수정완료</button>
</body>
</html>