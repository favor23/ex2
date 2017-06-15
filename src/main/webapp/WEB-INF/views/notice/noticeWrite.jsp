<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>noticeWrite</h2>
	
<form action="notice???" method="post">
	title : <input type="text" name="title" value="${dto.title}"><br>
	contents : <textarea rows="16" cols="20" name="contents">${dto.contents }</textarea>
	<input type="hidden" name="writer" value="${dto.writer}">
	
	<input type="hidden" name="num" value="${dto.num }">
	<p>title : <input type="text" name="title" value="${dto.title}"></p>
	
	
	<button>SUBMIT</button>
</body>
</html>