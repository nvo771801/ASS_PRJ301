<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light py-2 shadow-sm">
    <div class="container-fluid">
        <!-- Logo -->
        <a class="navbar-brand fw-bold text-danger" href="#"> Group 3 Perfume</a>

        <!-- Search bar -->
        <form class="d-flex ms-3 flex-grow-1" role="search">
            <input class="form-control me-2" type="search" placeholder="Tìm kiếm" aria-label="Search">
        </form>

        <!-- Right menu -->
        <ul class="navbar-nav ms-auto align-items-center">
            <li class="nav-item me-3 d-flex align-items-center">
                <i class="bi bi-shop me-1"></i> 1 cửa hàng toàn quốc
            </li>


            <!-- Account dropdown -->
            <nav>

            <c:if test="${not empty sessionScope.customer}">
                Xin chào, ${sessionScope.customer.fullname}
                <a href="logout">Đăng xuất</a>
            </c:if>
                
                 <c:if test="${empty sessionScope.customer}">
            <li class="nav-item dropdown me-3">
                <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" 
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi bi-person"></i> Đăng nhập
                </a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="accountDropdown">
                    <li><a class="dropdown-item" href="register.jsp">Tạo tài khoản</a></li>
                    <li><a class="dropdown-item" href="login.jsp">Đăng nhập</a></li>
                </ul>
            </li>
                 </c:if>
            </nav>
            <!-- Wishlist icon -->
            <li class="nav-item me-3">
                <a class="nav-link position-relative" href="#">
                    <i class="bi bi-heart"></i>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">0</span>
                </a>
            </li>

            <!-- Cart icon -->
            <li class="nav-item">
                <a class="nav-link position-relative" href="#">
                    <i class="bi bi-cart"></i>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">0</span>
                </a>
            </li>
        </ul>
    </div>
</nav>

<!-- Bootstrap CSS & Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
