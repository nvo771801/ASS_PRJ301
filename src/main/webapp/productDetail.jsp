<%-- 
    Document   : productDetail
    Created on : Oct 17, 2025, 8:33:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>${requestScope.product.pro_name}</title>
        <style>
            .product-card img {
                height: 250px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <%-- <jsp:include page="header.jsp" /> --%>

        <div class="container mt-5 mb-5">

            <div class="row">
                <div class="col-md-6">
                    <img src="${requestScope.product.image_url}" class="img-fluid rounded shadow" alt="${requestScope.product.pro_name}">
                </div>

                <div class="col-md-6">
                    <span class="badge bg-primary mb-2">${requestScope.product.brand}</span>
                    <h1>${requestScope.product.pro_name}</h1>

                    <p class="lead text-muted">${requestScope.product.cat_id.cat_name}</p>

                    <p>${requestScope.product.des}</p>

                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="add">

                        <h3 id="product-price" class="text-danger my-3 fw-bold">
                            <fmt:formatNumber value="${requestScope.variants.get(0).price}" type="currency"/>
                        </h3>

                        <div class="mb-3">
                            <label for="variant-select" class="form-label fw-bold">Chọn dung tích:</label>
                            <select id="variant-select" name="variant_id" class_="form-select form-select-lg">
                                <c:forEach var="v" items="${requestScope.variants}">
                                    <option value="${v.variant_id}" data-price="${v.price}">
                                        ${v.volume}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="quantity-input" class="form-label fw-bold">Số lượng:</label>
                            <input type="number" id="quantity-input" name="quantity" class="form-control" 
                                   value="1" min="1" style="width: 100px;">
                        </div>

                        <button type="submit" class="btn btn-primary btn-lg shadow">
                            <i class="bi bi-cart-plus-fill"></i> Thêm vào giỏ hàng
                        </button>
                    </form>

                </div>
            </div>

            <hr class="my-5">

            <h2 class="mb-4">Sản phẩm liên quan</h2>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">

                <c:forEach var="r" items="${requestScope.related}">
                    <c:if test="${r.pro_id != product.pro_id}">
                        <div class="col">
                            <div class="card h-100 shadow-sm border-0 rounded-3 product-card">
                                <a href="productdetail?pro_id=${r.pro_id}">
                                    <img src="${r.image_url}" class="card-img-top rounded-top" alt="${r.pro_name}">
                                </a>
                                <div class="card-body">
                                    <p class="card-text text-muted small">${r.brand}</p>
                                    <h5 class="card-title fs-6">
                                        <a href="productdetail?pro_id=${r.pro_id}" class="text-dark text-decoration-none">
                                            ${r.pro_name}
                                        </a>
                                    </h5>
                                </div>
                                <div class="card-footer bg-white border-top-0 pb-3">
                                    <a href="productdetail?pro_id=${r.pro_id}" 
                                       class="btn btn-outline-primary btn-sm w-100">
                                        Xem chi tiết
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>

            </div>

        </div>

        <%-- <jsp:include page="footer.jsp" /> --%>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const variantSelect = document.getElementById('variant-select');
                const priceDisplay = document.getElementById('product-price');

                // Tạo một đối tượng định dạng tiền tệ cho Việt Nam
                const currencyFormatter = new Intl.NumberFormat('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });

                // Thêm sự kiện "change" cho thẻ select
                variantSelect.addEventListener('change', function () {
                    // Lấy thẻ option đang được chọn
                    const selectedOption = this.options[this.selectedIndex];

                    // Lấy giá từ thuộc tính 'data-price' mà ta đã gán ở trên
                    const price = selectedOption.getAttribute('data-price');

                    // Cập nhật giá tiền vào thẻ h3
                    priceDisplay.innerText = currencyFormatter.format(price);
                });
            });
        </script>
    </body>
    <script src="bootstrap.bundle.min.js" type="text/javascript"></script>
</html>
