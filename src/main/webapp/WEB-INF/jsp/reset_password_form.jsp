<%--
  Created by IntelliJ IDEA.
  User: a16124
  Date: 3/15/23
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>
<html>
<head>
    <title>SanagaHealth Cameroon| Reset password</title>
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/authentication.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <meta name="viewport" content="width=device-width">
</head>
<style>

    .valid {
        color: green;
    }

    .valid:before{
        position: relative;
        left: -15px;
        /*content: "✔";*/
    }

    .invalid {
        color: red;
    }

    .invalid:before{
        position: relative;
        left: -25px;
        /*content: "X";*/
    }

    #requirements{
        display: none;
        text-align: center;

    }
</style>
<body>
    <header-component></header-component>
    <main sanaga-page="content subcontainer" style="margin-top: 54px">
        <p style="margin-bottom: 23px; font-size: 22px">Fill in the required information below to reset your password. </p>
        <div class="signup-container">
            <form action="/forgot-password" method="post" modelAttribute="resetPassword">
                <input type="password" placeholder="Password" id="password"   required="required"/><br>
                <span id="icon"></span>
                <input  type="password" placeholder="Confirm password" id="password2" required="required"/><br>
                <input type="submit" value="Submit" style="width:20%" sanaga-button="fancy-btn">
            </form>

        <div class="row" id="requirements">
            <h3>Password Requirements</h3>
            <span id="lower" class="invalid">  - A lowercase letter</span>
            <span id="upper" class="invalid">  - A capital (uppercase) letter</span>
            <span id="number" class="invalid"> - A number</span>
            <span id="specialChar" class="invalid"> - A special character</span>
            <span id="length" class="invalid"> - Minimum 8 characters</span>
        </div>
        </div>
    </main>
</body>
</html>
