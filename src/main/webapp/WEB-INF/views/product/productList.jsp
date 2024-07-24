<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<!-- 부트스트랩 CSS 추가 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Product List</h2>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>순번</th>
                <th>상품명</th>
                <th>단가</th>
                <th>입고일</th>
                <th>상품경로</th>
                <th>상품파일명</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="productVo" items="${productList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                     <td><a href="<c:url value='/product/detail/${productVo.productId}'/>">${productVo.name}</a></td>
                    <td>${productVo.unitPrice}</td>
                    <td>${productVo.regDate}</td>
                    <td>${productVo.imgPath}</td>
                    <td>${productVo.fileName}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<!-- 부트스트랩 JS 및 jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
