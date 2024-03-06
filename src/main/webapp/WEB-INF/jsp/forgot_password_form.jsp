<%--
  Created by IntelliJ IDEA.
  User: Daniel Nkwenti
  Date: 3/15/23
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>

<html>
<head>
    <title>Sanaga-Health Cameroon| Forgot_Password</title>
    <script type="text/javascript" src="js/header.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <meta name="viewport" content="width=device-width">
</head>

<body>
    <header-component></header-component>

    <main sanaga-page="content subcontainer" style="margin-top: 45px">
        <p style="margin-bottom: 23px">If you forgot your password, we are happy to help you get back into your account. Please enter your email below.</p>
        <div class="signup-container">
            <form:form action="/forgot-password" method="post" modelAttribute="resetPassword">
                <form:input  placeholder="email address" path="email"/><br>
                <input type="submit" value="Submit" style="width:20%" sanaga-button="fancy-btn">
            </form:form>
            <p>${resetMessage}</p>
            <p>${error}</p>
        </div>
    </main>
</body>
</html>
