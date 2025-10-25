<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Login Form</title>
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <style>
            
            .container {
                display: flex;
                width: 100%;
                height: 100vh;
                margin: 0;
                padding: 0;
            }

            * {
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }

            body {
                margin: 0;
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #fff;
            }

            .container {
                display: flex;
                width: 100%;
                height: 100vh;
            }

            /* Phần form bên trái */
            .left-panel {
                width: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #fff;
            }

            .login-form {
                width: 500px;
                padding: 30px;
                margin-left:-60px;

                box-shadow: 0 0 10px rgba(208, 0, 0, 0.4);
                border-radius:15px;

            }

            .login-form h2 {
                color: #d00000;
                font-weight: 600;
                margin-bottom: 10px;
            }

            .login-form p {
                color: #555;
                margin-bottom: 25px;
            }

            .form-control {
                border-radius: 10px;
                margin-bottom: 20px;
                height: 55px;
            }

            .btn-login {
                background-color: #d00000;
                color: white;
                border: none;
                width: 100%;
                height: 45px;
                font-size: 16px;
                font-weight: 600;
                border-radius: 6px;
            }

            .btn-login:hover {
                background-color: #a30000;
            }

            .remember-section {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 15px;
            }

            .alert-danger {
                margin-top: 15px;
            }

            /* Phần ảnh bên phải */
            .right-panel {
                flex: 1;
                justify-content: flex-end;
                width: 50%;
                margin-right: -290px;

                background-image: url('img/background_login.jpg');
                background-size: cover;
                background-position: center;
            }

            a {
                text-decoration: none;
>>>>>>> feature-login
            }
        </style>
    </head>
    <body>
      
        <div class="container">
<<<<<<< HEAD
            <div class="login-form">
                
                <h2 class="text-center mb-4">${empty loginTitle ? 'Login' : loginTitle}</h2>
                
                <form action="${empty loginTarget ? 'auth' : loginTarget}" method="POST">
                    
                    <c:choose>
                        <c:when test="${loginTarget == 'admin'}">
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>

                <c:if test="${not empty error}">
                    <p style="color:red;" class="mt-3">${error}</p>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <p style="color:red;" class="mt-3">${errorMessage}</p>
                </c:if>

                <c:if test="${loginTarget != 'admin'}">
                    <p class="text-center mt-3">
                        Chưa có tài khoản? <a href="auth?action=register">Đăng ký ngay</a>
                    </p>
                    <p class="text-center mt-2">
                        <a href="home">Quay về trang chủ</a>
                    </p>
                </c:if>
=======
            <!-- Cột trái -->
            <div class="left-panel">
                <div class="login-form">
                    <h2>Đăng nhập</h2>
                    <p>Đăng nhập để truy cập vào website của chúng tôi</p>

                    <form action="auth" method="POST">
                        <%
                            String savedEmail = "";
                            String savedPassword = "";
                            boolean rememberChecked = false;

                            Cookie[] cookies = request.getCookies();
                            if (cookies != null) {
                                for (Cookie c : cookies) {
                                    if ("userEmail".equals(c.getName())) {
                                        savedEmail = c.getValue();
                                    } else if ("userPassword".equals(c.getName())) {
                                        savedPassword = c.getValue();
                                    }
                                }
                            }
                            if (savedEmail != null && !savedEmail.isEmpty() && savedPassword != null && !savedPassword.isEmpty()) {
                                rememberChecked = true;
                            }

                            if (request.getAttribute("errorMessage") != null) {
                                savedEmail = "";
                                savedPassword = "";
                                rememberChecked = false;
                            }
                        %>

                        <input type="email" class="form-control" name="email" placeholder="Email" value="<%= savedEmail%>" required>
                        <input type="password" class="form-control" name="password" placeholder="Password" value="<%= savedPassword%>" required>

                        <div class="remember-section">
                            <div>
                                <input type="checkbox" id="remember" name="remember" <%= rememberChecked ? "checked" : ""%>>
                                <label for="remember">Remember me</label>
                            </div>
                            <a href="#" style="color:#d00000;">Forgot Password</a>
                        </div>

                        <button type="submit" class="btn-login">Log In</button>

                        <div class="text-center mt-3">
                            <span>Bạn chưa có tài khoản? </span>
                            <a href="register" style="color:#d00000; font-weight:600;">Đăng ký</a>
                        </div>
                    </form>

                    <c:if test="${not empty errorMessage}">
                        <p class="alert alert-danger" role="alert">${errorMessage}</p>
                    </c:if>
                </div>
>>>>>>> feature-login
            </div>

            <!-- Cột phải -->
            <div class="right-panel"></div>
        </div>

    </body>
</html>
