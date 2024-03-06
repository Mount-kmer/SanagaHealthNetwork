<%--
  Created by IntelliJ IDEA.
  User: Daniel Nkwenti
  Date: 5/4/23
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>

<html>
<head>
    <title>SanagaHealth Cameroon|Registration Success</title>
    <script type="text/javascript" src="js/header.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
</head>


<body>
    <header-component></header-component>
    <main sanaga-page="content subcontainer">
        <div>
            <div modelattribute="user">
                <p>${signup.firstName} was successfully registered.</p>
                <a href="/home">Login</a>
            </div>
            <p>${message}</p>
        </div>
    </main>

</body>
</html>
