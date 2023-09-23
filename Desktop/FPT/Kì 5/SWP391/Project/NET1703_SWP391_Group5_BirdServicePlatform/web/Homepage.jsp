<%-- 
    Document   : Homepage
    Created on : Sep 21, 2023, 6:58:41 PM
    Author     : TINH KHUNG
--%>
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
        <!-- Page Content-->
        <div class="container px-4 px-lg-5">
            <!-- Heading Row-->
            <div class="row gx-4 gx-lg-5 align-items-center my-5">
                <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0"
                                           src="https://dummyimage.com/900x400/dee2e6/6c757d.jpg" alt="..." /></div>
                <div class="col-lg-5">
                    <h1 class="font-weight-light">Dịch vụ có số lượng người đặt cao nhất </h1>
                    <p>Chi tiết về dịch vụ</p>
                    <a class="btn btn-primary" href="#!">Booking Now!</a>
                </div>
            </div>
            <!-- Call to Action-->
            <div class="card text-white bg-secondary my-5 py-4 text-center">
                <div class="card-body">
                    <p class="text-white m-0">This call to action card is a great place to showcase some important
                        information or display a clever tagline!</p>
                </div>
            </div>
            <!-- Content Row-->
            <div class="row gx-4 gx-lg-5">
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Dịch vụ số 1</h2>
                            <p class="card-text">Chi tiết</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">Add to cart</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Dịch vụ số 2</h2>
                            <p class="card-text">Chi tiết</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">Add to cart</a></div>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">
                        <div class="card-body">
                            <h2 class="card-title">Dịch vụ số 3</h2>
                            <p class="card-text">Chi tiết</p>
                        </div>
                        <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">Add to cart</a></div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container px-4 px-lg-5">
                <p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p>
            </div>
        </footer>
    </body>
</html>
