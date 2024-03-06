<%--
  Created by IntelliJ IDEA.
  User: Daniel Nkwenti
  Date: 9/18/23
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>
<html>
<head>
    <script type="text/javascript" src="js/header.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <title>SanagaHealth|Join as a Provider</title>
</head>
<body>
    <header-component></header-component>
    <main sanaga-page="content subcontainer">
        <div modelattribute="doctor">
            <p> Dr  ${doctorForm.firstName}, ${doctorForm.lastName} was successfully registered.</p>
            <a href="/home">Login</a>
        </div>
    </main>

</body>
</html>
