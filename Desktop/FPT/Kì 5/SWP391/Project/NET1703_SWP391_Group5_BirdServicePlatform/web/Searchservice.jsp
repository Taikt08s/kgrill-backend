<%-- 
    Document   : Searchservicec
    Created on : Sep 22, 2023, 8:57:02 PM
    Author     : TINH KHUNG
--%>
<%@page import="model.Service"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLDecoder"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bird Service</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-5">
                <a class="navbar-brand" href="#!">Bird Service</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-center">
                        <li class=" nav-item"><a class="nav-link" aria-current="page" href="#!">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Register</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Contact</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Services</a></li>
                        <div class="nav-item"><a href="#!" class="nav-link">
                                <nav class="navbar ">
                                    <div class="container-fluid">
                                        <form action="mainController" class="d-flex" role="search">
                                            <input class="form-control me-2" type="search" placeholder="Search"
                                                   aria-label="Search"  name="keyword" >
                                            <button class="btn btn-outline-light " type="submit" name="btn" value="searchService" >Search</button>
                                        </form>
                                    </div>
                                </nav>
                            </a></div>

                    </ul>
                </div>
            </div>
        </nav>
        <%
            List<Service> service = (List<Service>) request.getAttribute("service");
            for (Service s : service) {
        %>  

        <div class="container px-4 px-lg-5">
            <div style="padding-top:3vh" >
                <!-- Content Row-->
                <div class="row gx-4 gx-lg-5">
                    <div class="col-md-4 mb-5">
                        <div class="card h-100">
                            <div class="card-body"> 
                                <span>
                                    Name of Service: <%=s.getServiceName()%>
                                </span> <br>
                                <span>
                                    Description:  <%=s.getServiceDesc()%>
                                </span> <br>
                            </div>
                            <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">Add to cart</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%            }
        %>
    </body>
</html>
