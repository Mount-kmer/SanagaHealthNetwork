<%--
  Created by IntelliJ IDEA.
  User: Daniel Nwkenti
  Date: 8/1/22
  Time: 1:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>

<html>
<head>
    <title>SanagaHealth|Find A Doctor</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="js/header.js" ></script>
    <link rel="stylesheet" type="text/css" href="css/search.css"/>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
</head>


<body>
    <header-component></header-component>
    <main sanaga-page="content">
        <h1 style="text-align: center; font-family: Rubik, serif">FIND A PROVIDER</h1>
<%--        <p>${pageContext.request.userPrincipal.name}</p>--%>
        <p style="text-align: center">At SanagaHealth you will find doctors of diverse backgrounds who specialize in different disciplines. Book an appointment today.
        </p>
        <div class="parent-container">
            <div class="cards-section">
                <c:if test="${!empty(doctorsList)}">
                    <div class="card-container">
                        <c:forEach var="doctor" items="${doctorsList}">
                            <div class="card-item">
                                <img src="https://img.freepik.com/free-photo/african-american-doctor-isolated-white-background-professional-occupation_155003-43370.jpg?w=1480&t=st=1709505247~exp=1709505847~hmac=4012c9c1cf8857d280099440efcf03be8609df49e0f995d9637c309188f299e7" alt="doctor img" style="width: 100%">
                                <h1 class="doctorName" style="font-size: 18px">Dr. ${doctor.firstName} ${doctor.lastName}</h1>
                                <p class="practice">${doctor.areaOfPractice} </p>
                                <p>${doctor.hospitalWorked.hospitalName} </p>
                                <button  data-tag="/appointment?name=${doctor.lastName}&id=${doctor.id}"> Schedule Appointment</button><input type="hidden" name="${pageContext.request.userPrincipal.name}">
                            </div>
                        </c:forEach>
                    </div>
            </c:if>
            </div>
        </div>
    </main>
</body>
</html>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let element = document.querySelectorAll('button[data-tag]');

        element.forEach(button => {
            button.addEventListener('click', () => {
                window.location.href= button.getAttribute('data-tag');
            })
        })
    })
</script>
