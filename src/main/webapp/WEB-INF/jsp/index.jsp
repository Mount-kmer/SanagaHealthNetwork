<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>SanagaHealth</title>
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/loader.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>

</head>

<style>

    @media screen  and (max-width: 600px) {
        input[type=submit], .button{
            background-color: lightsteelblue;
        }
    }

    #error{
        margin-right: auto;
        margin-left: auto;
        margin-bottom: 13px;
        text-align: center;
        display: block;

    }

</style>

<script>
    function openForm() {
        window.open("/user-selection");
    }

</script>

<body>

    <header-component></header-component>
    <main sanaga-page=" content subcontainer" style="margin-top: 28px">
<%--        <h1 style="font-size: 40px; font-family: Rubik,serif" >SanagaHealth</h1>--%>
        <p  style="margin-bottom: 15px">We connect you and your family with health care profesionals in Cameron.</p>
        <div class="signup-container" >
            <form:form action="/user-home" method="post" modelAttribute="login">
                <form:input id="userEmail" type="text"  placeholder="Email" path="username" required="required"  /><br>
                <form:input id="pword" type="password" name="password" placeholder="Password" path="password" required="required"  autocomplete="false" /><br>
<%--                <c:if test="${param.error != null}">--%>
                    <div id="error" style="color: red">
                        <p >${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                    </div>
<%--                </c:if>--%>
<%--                <p class="text-danger">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>--%>
                <input type="submit" value="Login" style="width:20%" sanaga-button="fancy-btn">
            </form:form>
            <a href="/forgot-password"><p style="text-align: center; padding: 14px;">Forgot Password?</p></a>
            <p style="text-align: center; background: none">New here? Join below!</p>
            <button type="submit" class="button" onclick="openForm()" style="width:20%" sanaga-button="fancy-btn">Signup</button>
        </div>
    </main>

</body>