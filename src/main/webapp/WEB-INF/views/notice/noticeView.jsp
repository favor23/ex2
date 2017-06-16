<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>Notice View</h1>
   
   num : ${dto.num } <br>
   writer : ${dto.writer } <br>
   title : ${dto.title } <br>
   contents : ${dto.contents } <br>
   hit : ${dto.hit }<br>
   <a href="noticeList?curPage=1">noticeList</a><br>
   <a href="noticeDelete?num=${dto.num}">Delete</a>
   <a href="noticeUpdate?num=${dto.num}">Update</a>
</body>
</html>