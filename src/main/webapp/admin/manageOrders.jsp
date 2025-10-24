<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Đơn hàng</title>
        <link href="../bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Admin Panel</a>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin">Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/product">Quản lý Sản phẩm</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.request.contextPath}/admin/order">Quản lý Đơn hàng</a>
                        </li>
                    </ul>
                </div>
                <div class="d-flex">
                    <span class="navbar-text text-white me-3">
                        Chào, ${sessionScope.admin.admin_name}
                    </span>
                    <a href="${pageContext.request.contextPath}/admin?action=logout" class="btn btn-outline-light">Đăng xuất</a>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h1 class="mb-4">Quản lý Đơn hàng</h1>

            <c:if test="${not empty sessionScope.successMsg}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.successMsg}
                </div>
                <c:remove var="successMsg" scope="session" />
            </c:if>
            <c:if test="${not empty sessionScope.errorMsg}">
                <div class="alert alert-danger" role="alert">
                    ${sessionScope.errorMsg}
                </div>
                <c:remove var="errorMsg" scope="session" />
            </c:if>

            <table class="table table-striped table-bordered align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>ID Đơn hàng</th>
                        <th>Khách hàng</th>
                        <th>Ngày đặt</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                        <th>Cập nhật</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${requestScope.orderList}">
                        <tr>
                            <td>${order.order_id}</td>
                            <td>${order.cus_id.fullname}</td>
                            <td>
                                ${order.order_date}
                            </td>
                            <td>
                                <fmt:formatNumber value="${order.total}" type="currency" currencySymbol="VND" />
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.status == 'Pending'}">
                                        <span class="badge bg-warning text-dark">${order.status}</span>
                                    </c:when>
                                    <c:when test="${order.status == 'Processing'}">
                                        <span class="badge bg-info text-dark">${order.status}</span>
                                    </c:when>
                                    <c:when test="${order.status == 'Shipped'}">
                                        <span class="badge bg-primary">${order.status}</span>
                                    </c:when>
                                    <c:when test="${order.status == 'Delivered'}">
                                        <span class="badge bg-success">${order.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">${order.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/order" method="POST" class="d-flex">
                                    <input type="hidden" name="orderId" value="${order.order_id}">
                                    <select name="newStatus" class="form-select form-select-sm me-2" style="width: 150px;">
                                        <option value="Pending" ${order.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                        <option value="Processing" ${order.status == 'Processing' ? 'selected' : ''}>Processing</option>
                                        <option value="Shipped" ${order.status == 'Shipped' ? 'selected' : ''}>Shipped</option>
                                        <option value="Delivered" ${order.status == 'Delivered' ? 'selected' : ''}>Delivered</option>
                                        <option value="Canceled" ${order.status == 'Canceled' ? 'selected' : ''}>Canceled</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary btn-sm">Lưu</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="../bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>