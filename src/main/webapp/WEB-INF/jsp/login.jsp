<%--
  Created by IntelliJ IDEA.
  User: a16124
  Date: 12/3/22
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>SanagaHealth|</title>
</head>
<body>
<div id="formCard" class="card" style="width: 30rem; border: 1px solid gray">
    <div class="card-body">
        
        <form action="submit" method="post" >
            <input id="userEmail" type="text" placeholder="username"    />
            <input id="pword" type="password" placeholder="password" />
            <input type="submit" value="Login">
        </form>
        <div class="buttons">
            <button class="btn btn-dark"  style="margin-right: 5px" onclick="openForm()">Create Account</button>
            <a  href="#" style="margin-right: 7px">Forgot password?</a>
        </div>
    </div>
</div>
</body>
</html>
