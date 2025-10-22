<%-- 
    Document   : product
    Created on : Oct 17, 2025, 8:33:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Cửa hàng</title>
        <style>
        .product-card img {
            height: 250px;
            object-fit: cover; /* Đảm bảo ảnh vừa vặn đẹp mắt */
        }
        .product-card .card-title {
            height: 48px; /* Giữ 2 dòng cho tên sản phẩm */
            overflow: hidden;
        }
    </style>
    </style>
    </head>
    <body>

    <body>

    <%-- <jsp:include page="header.jsp" /> --%>

    <div class="container mt-5 mb-5">
        <div class="row">

            <div class="col-lg-3">
                <h3 class="mb-3">Bộ lọc</h3>
                
                <form action="product" method="get" class="mb-4">
                    <input type="hidden" name="action" value="search">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control" 
                               placeholder="Tìm kiếm sản phẩm..." 
                               value="${requestScope.keyword}">
                        <button class="btn btn-outline-primary" type="submit">Tìm</button>
                    </div>
                </form>

                <h5>Danh mục</h5>
                <div class="list-group shadow-sm">
                    <a href="product" 
                       class="list-group-item list-group-item-action 
                              ${empty requestScope.active_cat_id ? 'active' : ''}">
                        Tất cả sản phẩm
                    </a>
                    
                    <c:forEach var="c" items="${requestScope.categories}">
                        <a href="product?action=filter&cat_id=${c.cat_id}" 
                           class="list-group-item list-group-item-action 
                                  ${requestScope.active_cat_id == c.cat_id ? 'active' : ''}">
                            ${c.cat_name}
                        </a>
                    </c:forEach>
                </div>
            </div>

            <div class="col-lg-9">
                <h3 class="mb-3">Sản phẩm của chúng tôi</h3>
                
                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                    
                    <c:forEach var="p" items="${requestScope.products}">
                        <div class="col">
                            <div class="card h-100 shadow-sm border-0 rounded-3 product-card">
                                <a href="productdetail?pro_id=${p.pro_id}">
                                    <img src="${p.image_url}" class="card-img-top rounded-top" alt="${p.pro_name}">
                                </a>
                                
                                <div class="card-body">
                                    <p class="card-text text-muted small">${p.brand}</p>
                                    <h5 class="card-title fs-6">
                                        <a href="productdetail?pro_id=${p.pro_id}" class="text-dark text-decoration-none">
                                            ${p.pro_name}
                                        </a>
                                    </h5>
                                </div>
                                
                                <div class="card-footer bg-white border-top-0 pb-3">
                                    <a href="productdetail?pro_id=${p.pro_id}" 
                                       class="btn btn-primary w-100">
                                        Xem chi tiết
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty requestScope.products}">
                        <div class="col-12">
                            <div class="alert alert-warning" role="alert">
                                Không tìm thấy sản phẩm nào phù hợp.
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>

        </div>
    </div>
    </body>
    <%-- <jsp:include page="footer.jsp" /> --%>
    <script src="bootstrap.bundle.min.js" type="text/javascript"></script>
</html>
