<%--
  Created by IntelliJ IDEA.
  User: Daniel Nwkenti
  Date: 9/19/23
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/loader.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <title>SangaHealth|Provider Login</title>
</head>
<style>
    #error{
        margin-right: auto;
        margin-left: auto;
        margin-bottom: 13px;
        text-align: center;
        display: block;
    }
</style>

<body >
    <header-component></header-component>
    <main sanaga-page="content">
        <h1 style="font-size: 24px">Welcome to SanagaHealth Doctor, please log in below</h1>
        <div class="signup-container" style="margin-top: 50px">
            <div id="error" style="color: red">
                <p class="text-danger">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
            </div>
            <form:form action="/provider_login_form" method="post"  modelAttribute="doctorLogin">
                <form:input  type="text"   placeholder="username" path="username" required="required" name="username"  /><br>
                <form:input  type="password" placeholder="password" path="password" required="required" name="password" /><br>
                <input type="submit" value="Login" style="color: black; text-align: center">
            </form:form>
            <p style="text-align: center; background: none">New here? Join below!</p>

            <p style="text-align: center;"><a href="/provider_join">SignUp</a></p>
        </div>
    </main>
</body>
</html>
