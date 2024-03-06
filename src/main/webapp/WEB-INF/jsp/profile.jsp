<%--
  Created by IntelliJ IDEA.
  User: a16124
  Date: 2/25/23
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>
<%--<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/doctorProfile.js" ></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
    <title>SanagaHealth| User Profile</title>
</head>
<body>
    <header>
        <div class="sg-header-top">
            <div class="sg-logo">
                <a href="/user-home" style="text-decoration: none; color: #333333;">SanagaHealth</a>
                <p>${pageContext.request.contextPath}</p>
            </div>
            <div class="sg-user">
                <a href="#" class="notify"><i class="far fa-envelope"  style='font-size:20px' ></i> </a>
                <a href="#" class="notify"><i class="far fa-bell"   style='font-size:20px'></i> </a>

                <div class="dropdown">
                    <button class="dropbtn" style="font-family: Rubik, serif;font-size: 20px">${firstname} ${lastname} <i class='fas fa-caret-down' style='font-size:20px'></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="/user-logout">Logout</a>
                    </div>
                </div>

                <i class='far fa-user-circle open' style='font-size:22px' onclick="openNav()" ></i>
                <div id="myNav" class="overlay">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                    <div class="overlay-content">
                        <p style="color: black">${firstname} ${lastname}</p>
                        <a href="/user-logout">Logout</a>

                    </div>
                </div>
            </div>
        </div>

        <nav class="sg-header-nav" id="mySanagaNav" >
            <div class="sanaga-tabs" >
                <button class="sanaga-tab-button active" data-tab="appointment" onclick="displayTabItems('appointment', this)" id="appointmentTabButton">Appointments</button>
                <button class="sanaga-tab-button" data-tab="labs" onclick="displayTabItems('labs', this)">Labs</button>
                <%--                <button class="sanaga-tab-button" onclick="displayTabItems('messages', this)">Messages</button>--%>
                <button class="sanaga-tab-button" onclick="displayTabItems('bills', this)">Bills</button>
            </div>
        </nav>

        <main>
            <div class="sanaga-tab-content active" id="appointment" >
                <h3 style="text-align: center">Please arrive 15 minutes before your shceduled appointment time.</h3>
                <div class="appointment-container" id="appointmentsContainer">
                    <c:if test="${!empty(app)}">
                        <c:forEach var="appointment" items="${app}">
                            <div class="appointment-card" id="app-sc">
                                <div class="appointment-details">
                                    <i class="fa fa-user-md"></i>
                                    <span style="font-size: 18px; text-decoration: none">Dr. ${appointment.doctorList.firstName}, ${appointment.doctorList.lastName} </span>
                                </div>
                                <div class="appointment-details">
                                    <i class="fa fa-clock"></i>
                                    <span style="font-size: 14px">Time: <strong>${appointment.time}</strong> </span>
                                </div>
                                <div class="appointment-details">
                                    <i class="fa fa-calendar-alt"></i>
                                    <span style="font-size: 14px;">Date: <strong>${appointment.localDate}</strong>  </span>
                                </div>
                                <div class="appointment-details">
                                    <i class="fa fa-hospital"></i>
                                    <span style="font-size: 14px">Location: <strong>${appointment.doctorList.hospitalWorked.hospitalName}</strong>  </span>
                                </div>
                                <div class="appointment-details">
                                    <i class="fa fa-map-marker-alt"></i>
                                    <span style="font-size: 14px">Address: <strong> ${appointment.doctorList.hospitalWorked.hospitalAddresses.street},
                                ${appointment.doctorList.hospitalWorked.hospitalAddresses.city},
                                            ${appointment.doctorList.hospitalWorked.hospitalAddresses.country}</strong> </span>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty app}">
                        <p>You do not have any upcoming appointments please schedule one below!</p>

                    </c:if>

                    <div class="doctors-list" id="app-new" style="display: none">
                        <h2>Book Appointment</h2>
                    </div>
                </div>

                <div class="new-app">
<%--                    <button id="appointmentButton">Schedule</button>--%>
                    <a href="/search" style="text-decoration: none">
                    <a href="${pageContext.request.contextPath}/search">Schedule New Appointment</a>
<%--                </div>--%>

            </div>

            <div class="sanaga-tab-content" id="labs" style="display: none">
                There are no labs for now..
            </div>

            <div class="sanaga-tab-content" id="pharmacy" style="display: none">
                There are no prescriptions...
            </div>

            <div class="sanaga-tab-content" id="bills" style="display: none">
                There are no messages...
            </div>
            </div>
        </main>
    </header>
</body>
</html>




