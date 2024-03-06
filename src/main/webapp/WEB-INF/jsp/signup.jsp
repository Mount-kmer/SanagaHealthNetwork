<%--
  Created by IntelliJ IDEA.
  User: a16124
  Date: 8/18/22
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>
<html>
<head>
    <title>signup|To find doctor in cameroon|</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/authentication.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
</head>

<style>
    * {
    /*font-family: Rubik serif;*/
        font-family: Rubik, serif;
    }

    input[type=text], input[type=password],input[type=email], input[type=tel],select, input[type=date] {
        padding: 12px 20px;
        margin-left: auto;
        margin-right: auto;
        margin-top: 12px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background: white;
        display: block;
        width: 60%;
        justify-content: space-between;
    }



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

    @media screen and (max-width: 45rem) {
        input[type=text] , input[type=password], input[type=date],input[type=tel], input[type=email], select{
            width: 100%;
            border-radius: 5px;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            background-color: white;
        }
        input[type=submit]{
            background-color: lightsteelblue;
        }

        h1{
            font-size: 18px;
        }

        input[type=submit] {
            background-color: cornflowerblue;
        }

    }
</style>

<header></header>
<body>
<header-component></header-component>
    <main sanaga-page="content subcontainer">
        <h1 style="font-size: 25px; line-height: 1.5em" >Welcome to SanagaHealth, your health is important to us.</h1>
        <p style="font-size: 18px; margin-top: 18px">First, lets get some basic information.</p>
        <div class="signup-container">
            <c:if test="${not empty validationError}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach items="${validationError}" var="error">
                            <li style="color: red">${error.defaultMessage}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <form:form action="/signup" method="post" modelAttribute="signup" id="sanaga-form">
                <form:input id="firstName" type="text" placeholder="First Name" pattern="^[a-zA-Z][a-zA-Z-]{1,}$"
                            title="Firts name must be at least 2 characters, and start with a letter"  required="required"  autocomplete="true"   path="firstName" />
                <form:input id="lastName"  type="text" placeholder="Last Name" pattern="^[a-zA-Z][a-zA-Z-]{1,}$"
                            title="Last name must be at least 2 characters, and start with a letter" required="required"  autocomplete="true" path="lastName"/>
                <p style="text-align: center; color: red">${message}</p>
                <form:input id="Email" type="email" placeholder="Email" pattern="" path="email"
                            title="please provide a valid email" required="required" autocomplete="true" />
                <form:input id="password"  type="password" placeholder="Password"
                            title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                            path="password" required="required" autocomplete="true" /> ${password}
                <div class="row" id="requirements">
<%--                    <p>Password Requirements</p>--%>
                    <span id="lower" class="invalid">  - A lowercase letter</span>
                    <span id="upper" class="invalid">  - A capital (uppercase) letter</span>
                    <span id="number" class="invalid"> - A number</span>
                    <span id="specialChar" class="invalid"> - A special character</span>
                    <span id="length" class="invalid"> - Minimum 8 characters</span>
                </div>

                <form:input id="date" type="date"  placeholder="DOB"  path="dateOfBirth" required="required" autocomplete="true" />
                <span id="message" style="display: none">You must be 18 years old to join</span>
                <form:select path="gender">
                    <form:option value="" label="Gender" />
                    <form:options items="${genderItems}"/>
                </form:select><br>
<%--                <form:select path="gender"  class="dropdown-item" items="${genderItems}" required="required"/><br>--%>
<%--                <form:button>Register</form:button>--%>
<%--                <input type="submit"  sanaga-button="fancy-button" class=".submit-btn" value="Register">--%>
                <input type="submit" value="Register" style="width:30%" sanaga-button="fancy-btn">

            </form:form>
        </div>
    </main>
</body>
</html>
