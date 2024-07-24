<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Create Form</title>
</head>
<body>
    <h2>Product Create Form</h2>
    <form action="<c:url value='/product/create'/>" 
    		method="post" enctype="multipart/form-data">
    		
    	상품명: <input type="text" name="name" /><br />
    	설명: <input type="text" name="description" /><br />
    	가격: <input type="text" name="unitPrice" /><br />
        파일 1: <input type="file" name="files" /><br />
        파일 2: <input type="file" name="files" /><br />
        파일 3: <input type="file" name="files" /><br />
        파일 4: <input type="file" name="files" /><br />
        파일 5: <input type="file" name="files" /><br />
        <input type="submit" value="저장" />
    </form>
</body>
</html>
