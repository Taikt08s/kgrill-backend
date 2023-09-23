<%-- 
    Document   : shopping
    Created on : Jun 21, 2023, 8:27:20 AM
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

        <c:if test="${requestScope.act eq  'remove'}">
            <h2>remove successfully</h2>
            <a href="login.jsp">back to login</a>
        </c:if>

        <form action="mainController">
            <h1>Book Store</h1><br>
            <label for="goods">Book</label>
            <select id="goods" name="booklist">
                <option value="C++">C++</option>
                <option value="C#">C#</option>
                <option value="JAVA">JAVA</option>
                <option value="PHP">PHP</option>
                <option value="PYTHON">PYTHON</option>
                <option value="JAVASCRIPT">JAVASCRIPT</option>
            </select><br>
            <input type="submit" value="Add" name="btn">
            <input type="submit" value="View" name="btn">
        </form>


        <c:if test="${requestScope.act eq 'view'}">
            <% Cookie[] cookies = request.getCookies();
                if (cookies != null) {
            %>
            <table border="1">
                <tr>
                    <th>No.</th>
                    <th>Book Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                <form action="mainController">
                    <%
                        int count = 1;
                        for (int i = 1; i < cookies.length; i++) {
                            int tmp = Integer.parseInt(cookies[i].getValue());
                            if (tmp > 0) {
                    %>
                    <tr>
                        <td><%=count++%></td>
                        <td><%= cookies[i].getName()%></td>
                        <td><%= cookies[i].getValue()%></td>
                        <td><input type="checkbox" name="rmv" value="<%=cookies[i].getName()%>"></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <tr>
                        <td colspan="3"><a href="shopping.jsp">Add More Cart</a></td>
                        <td><input type="submit" value="Remove" name="btn"></td>
                    </tr>
                </form>
            </table>
            <% } else {
            %> 
            <h1>khong ton tai cart nao hoac da bi xoa het</h1> 
            <% }
            %>
        </c:if> 
    </body>
</html>
