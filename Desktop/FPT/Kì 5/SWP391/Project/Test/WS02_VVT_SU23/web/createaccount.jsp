<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="mainController" method="post">
            <h1>Register Page</h1>
            <label for="username">Username*</label>
            <input id="username" type="text" name="username" required autocomplete="off"> (6 - 12 chars)
            <br>
            <label for="password">Password*</label>
            <input id="password" type="password" name="password" required autocomplete="off"> (8 - 20 chars)
            <br>
            <label for="confirm">Confirm*</label>
            <input id="confirm" type="password" name="confirm" required>
            <br>
            <label for="fullname">Full name*</label>
            <input id="fullname" type="text" name="fullname" required> (2-40 chars)
            <br>
            <input type="submit" value="Register" name="btn" >
            <input type="reset" value="reset">
        </form>
    </body>
</html>
