<%--
  Created by IntelliJ IDEA.
  User: Daniel Nkwenti
  Date: 5/11/23
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1"%>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="network.doctors.SanagaHealthNetwork.entity.DoctorList" %>
<%@ page import="network.doctors.SanagaHealthNetwork.entity.Appointment" %>
<%@ page import="network.doctors.SanagaHealthNetwork.service.AppointmentService" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>


<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/slotSelector.js" ></script>
    <script type="text/javascript" src="js/dateformat.js" ></script>
    <title>SangaHealth| Make An Appointment With A Doctor In Cameroon</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">

</head>


    <style>

        * {
            /*font-family: Rubik serif;*/
            font-family: Rubik, serif;
        }
        .content{
            max-width: 700px;
            margin: 3px  auto;
            /*margin-top: 4px;*/
            padding:120px;
        }

        label {
            display:flex;
            margin-top: 10px;
        }

        select, input[type="date"]{
            width: 50%;
            padding: 5px;
            margin-top: 5px;
        }

        .submit-btn{
            width: 50%;
        }

        /*input[type="date"]{*/
        /*    float: left;*/
        /*}*/
        .card {
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
            font-family: Rubik , serif;
        }

        .card img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 10px;
        }

        .card h2 {
            margin: 0;
            font-size: 18px;
        }

        .fields{
            display: flex;
            flex-direction: row;
            flex-basis: fit-content;
        }

        .card p {
            margin: 0;
            font-size: 14px;
            color: black;
        }

        input[type=submit]{
            background-color: cornflowerblue;
            padding: 8px 12px;
            border-radius: 20px;
            border: 1px solid white;
            font-size: 18px;
            text-align: center;
            margin-left: auto;
            margin-right: auto;
            display: block;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;


        }
        input[type=submit]:disabled {
            background-color: lightgrey;
            color: white;
            cursor: not-allowed;
        }


        .slot-container {
            flex: 1;
            padding: 10px;
            margin-top: 34px;
            display: grid;
            gap: 10px;
            grid-template-columns: repeat(auto-fill, minmax(35em, 1fr));
            flex-shrink: unset;
        }

        @media screen  and (max-width: 45em) {

            select, input[type=date]{
                margin-left: 5rem;
                width: 100%;
            }

            /*.fields{*/
            /*   display: flex;*/
            /*    flex-grow: inherit;*/
            /*}*/

            .slot-container{
                display: grid;
                grid-template-columns: 2fr 2fr 2fr;
            }

            label{
                float: left;
                text-align: left;
                text-wrap: nowrap;

            }

        }
        
        .time-slot {
            background-color: white;
            border: 1px solid black;
            padding: 5px 10px;
            margin: 5px;
            cursor: pointer;
        }
        
        .time-slot.selected {
            background-color: cornflowerblue;
            color: white;
        }


    </style>

<body>
<header-component></header-component>

<main sanaga-page="content subcontainer" style="margin-top: 28px">
    <h3 style="line-height: 1.5em;">We appreciate you using our service please schedule your appointment below</h3>
    <hr style="border: 1px solid #f0f2f4">

    <c:forEach var="doctor" items="${doctorInfo}">
        <h3>Dr. ${doctor.firstName}, ${doctor.lastName} - <span>${doctor.areaOfPractice}.</span></h3>
        <p> <b>Clinic:</b>    ${doctor.hospitalWorked.hospitalName}</p>
    </c:forEach>

        <div class="slot-container">
            <form action="/calendar-events" method="get">
                <div class="fields">
                    <label path="date" >Choose Date:</label>
                    <input path="date" name="date" type="date"  class="date-input" required="true" cssStyle="width: 40%; text-align: center"
                           min="${currentDate}" autocomplete="off"  onchange="getOpenCalendarSlots(this.value)"/>
                    <p>${invalidDate}</p>
                    <input type="hidden" name="email">
                </div>
            </form>

            <p id="error-events"></p>
        </div>

        <hr style="border: 1px solid #f0f2f4">

        <form:form action="/appointments/new" method="post" modelAttribute="appointment">
            <h3 id="slot-title"></h3>
            <div id="date-picker" > </div><br>
            <input type="submit" value="Schedule Appointment" id="submit-btn" style="display: none" disabled>
            <form:hidden   id="selectedDate" path="date"/>
            <form:hidden  path="time" id="selectedTimeSlot"  />
            <input type="hidden" name="doctorName" value="${doctorName}">
            <input type="hidden" name="id" value="${doctorId}">
        </form:form>

</main>

</body>
</html>















