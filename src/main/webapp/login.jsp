<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login</title>
        <link href="bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background-color: #f8f9fa;
            }
            .login-form {
                max-width: 400px;
                margin: 100px auto;
                padding: 30px;
                background-color: #ffffff;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="login-form">
                <h2 class="text-center mb-4">Login</h2>
                <form action="auth" method="POST">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>

                <c:if test="${not empty errorMessage}">
                    <p style="color:red;">${errorMessage}</p>
                </c:if>
            </div>
        </div>

        <script src="bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>