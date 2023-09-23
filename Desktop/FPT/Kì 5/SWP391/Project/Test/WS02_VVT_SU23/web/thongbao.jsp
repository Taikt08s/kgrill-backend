<%-- 
    Document   : thongbao.jsp
    Created on : Jul 9, 2023, 11:23:04 AM
    Author     : TINH KHUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${requestScope.url eq 'delete'}" >
            <h1>Xoa du lieu nguoi dung thanh Cong</h1>
            <a href="login.jsp">back to login</a>
        </c:if>
        <c:if test="${requestScope.url eq 'insert'}" >
            <h1>Dang ky thanh cong</h1>
            <a href="login.jsp">back to login</a>
        </c:if>
        <c:if test="${requestScope.url eq 'invalid'}" >
            <h1 style="color: red">Invalid username or password!!!</h1>
            <a href="login.jsp">back to login</a><br>
            <a href="createaccount.jsp">Try again</a>
        </c:if>

    </body>
</html>
