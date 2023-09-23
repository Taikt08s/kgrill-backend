<%-- 
    Document   : login
    Created on : Jul 8, 2023, 2:41:21 PM
    Author     : TINH KHUNG
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <style>
            #Form{
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            /*CSS here*/
            #login{
                max-width: 400px;
                flex-grow: 1;
                padding: 30px 30px 40px;
                height:  450px;
                background-color: white;
                text-align: center;
                display: block;
                margin: auto;
                box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;
            }

            .box-center{
                height: 300px;
                display: block;
                padding: 20px 50px 50px 50px;
                box-sizing: border-box;
            }

            .form-design{
                text-align: left;

            }

            .form-design input{
                width: 100%;  
                height: 30px;
                text-align: 25px;
            }

            .form-design h3{
                margin-bottom: 5px;
            }

            .login-button{
                margin-top: 20px;
                width: 102% ;
                height: 40px;
                background-color: black;
                color: white;
                border-radius: 15px;
                border:none;
            }




        </style>
    </head>
    <body style="background-color: gainsboro">

        <div id="Form">

            <form action="mainController" id="login">
                <h1 style="text-align: center; margin-bottom: 0px " >Login</h1>

                <div class="box-center">
                    <div class="form-design">
                        <h3>Username</h3>
                        <input type="text"  placeholder="type your username" name="username"><br>
                    </div>
                    <div class="form-design">
                        <h3>Password</h3>
                        <input type="password"  placeholder="type your password" name="password"> <br>
                    </div>
                    <input class="login-button" type="submit" value="Login"  name="btn"><br>
                    <br>
                    <a href="createaccount.jsp">Create new account</a>
                </div>

                <div class="form-design">

                    <a  href="shopping.jsp" style="color:black">Skip and heading to shopping</a>
                    <c:set var="rs" value="${requestScope.rs}" />
                    <c:if test="${not rs and not empty param.username  }">
                        <h3 style="color: red; text-align: center">sai tai khoan hoac mat khau</h1>
                        </c:if>
                </div>

            </form>

        </div>

    </body>
</html>
