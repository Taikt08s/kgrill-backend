<%-- 
    Document   : welcome
    Created on : Jul 8, 2023, 4:23:30 PM
    Author     : TINH KHUNG
--%>

<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%--<c:set var="fullname" value="${param.fullname}"></c:set>--%>
        <% String fullname = request.getParameter("fullname");
            String decodedValue = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String temp = cookie.getName();
                    if (temp.equals("user")) {
                        fullname = cookie.getValue();
                        decodedValue = URLDecoder.decode(fullname, "UTF-8");
                        fullname = decodedValue;
                    }
                }
                if (fullname == null) {
        %>
        <h1>Khong ton tai cookie</h1>
        <%
                }
            }
        %>
        <h1>welcome <%=fullname%>,</h1>


        <form  action="mainController" >
            <input type="submit" value="Logout" name="btn"> <br>
            Enter LastName <input type="text" name="fullName" placeholder="Enter last name to search" ><br> 
            <input type="submit" value="Search" name="btn" >
        </form>   

        <c:set var="list" value="${requestScope.data}"></c:set>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>UserName</th>
                        <th>Password</th>
                        <th>FullName</th>
                        <th>Roles</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="item" items="${list}" varStatus="counter">
                        <c:if test="${item.roles==0}">
                            <c:set var="r" value="FALSE" ></c:set>
                        </c:if>
                        <c:if test="${item.roles==1}">
                            <c:set var="r" value="TRUE" ></c:set>
                        </c:if>
                    <form action="mainController">
                        <tr>
                            <td>1</td>
                            <td><input type="text" name="username" value="${item.username}"><br></td>
                            <td><input type="text" name="password" value="${item.password}"><br></td>
                            <td><input type="text" name="fullname" value="${item.fullname}"><br></td>
                            <td><input type="text" name="roles" value="${r}"><br></td>
                            <td><input type="submit" value="Delete" name="btn"</td>
                            <td><input type="submit" value="Update" name="btn"></td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>


