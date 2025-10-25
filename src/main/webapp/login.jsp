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
            }
        </style>
    </head>
    <body>
      
        <div class="container">
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
                                <label for="remember">Ghi nhớ tài khoản</label>
                            </div>
                            <a href="#" style="color:#d00000;">Quên mật khẩu</a>
                        </div>

                        <button type="submit" class="btn-login">Đăng nhập</button>

                        <div class="text-center mt-3">
                            <span>Bạn chưa có tài khoản? </span>
                            <a href="register" style="color:#d00000; font-weight:600;">Đăng ký</a>
                        </div>
                    </form>

                    <c:if test="${not empty errorMessage}">
                        <p class="alert alert-danger" role="alert">${errorMessage}</p>
                    </c:if>
                </div>
            </div>

            <!-- Cột phải -->
            <div class="right-panel"></div>
        </div>

    </body>
</html>
