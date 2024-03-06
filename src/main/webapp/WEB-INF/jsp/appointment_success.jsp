<%--
  Created by IntelliJ IDEA.
  User: Daniel Nkwenti
  Date: 7/12/23
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>SanagaHealth| Appointment Success </title>
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/loader.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>

</head>

<body>
    <header-component></header-component>
    <main sanaga-page="content subcontainer">
        <div>
            <div modelattribute="appointment" style="margin-top: 45px; font-size: 18px; line-height: 1.5em" >
                <p>${bookedAppointment} </p>
                <p><a href="/userprofile">Back to Profile</a></p>
            </div>
        </div>
    </main>
</body>
</html>
