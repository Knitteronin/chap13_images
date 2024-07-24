<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Detail</title>
<!-- 부트스트랩 CSS 추가 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Product Detail</h2>
    <div class="card">
        <div class="card-header">
            ${productWithImageVo.name}
        </div>
        <div class="card-body">
            <p><strong>상품 ID:</strong> ${productWithImageVo.productId}</p>
            <p><strong>설명:</strong> ${productWithImageVo.description}</p>
            <p><strong>단가:</strong> ${productWithImageVo.unitPrice}</p>
            <p><strong>입고일:</strong> ${productWithImageVo.regDate}</p>
            <c:if test="${not empty productWithImageVo.imgList}">
                <div class="row">
                    <c:forEach var="image" items="${productWithImageVo.imgList}">
                        <div class="col-md-3">
                            <div class="card mb-3">
                                <img src="${pageContext.request.contextPath}/resources/upload/${image.imgPath}/${image.fileName}" class="card-img-top" alt="Image">
                                <div class="card-body">
                                    <p class="card-text">${image.fileName}</p>
                                    <c:if test="${image.isMain == 1}">
                                        <span class="badge badge-primary">대표 이미지</span>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>
<!-- 부트스트랩 JS 및 jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
