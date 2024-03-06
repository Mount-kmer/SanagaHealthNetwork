<%--
  Created by IntelliJ IDEA.
  User: a16124
  Date: 3/5/23
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>SanagaHealth|Doctor Portal</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="js/header.js" ></script>
        <script type="text/javascript" src="js/doctorProfile.js" ></script>
        <link rel="stylesheet" type="text/css" href="css/base.css"/>
        <link rel="stylesheet" type="text/css" href="css/profile.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@100&family=Roboto+Serif:wght@100&family=Roboto+Slab:wght@100&family=Rubik:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    </head>
    <body style="font-family: Rubik, serif">
    <header>
        <div class="sg-header-top">
            <div class="sg-logo">
                <a href="/user-home" style="text-decoration: none; color: #333333;">SanagaHealth</a>
            </div>
            <div class="sg-user">
                <a href="#" class="notify"><i class="far fa-envelope"  style='font-size:20px' ></i> </a>
                <a href="#" class="notify"><i class="far fa-bell"   style='font-size:20px'></i> </a>

                <div class="dropdown">
                    <button class="dropbtn" >FOMUKONGYUY  NATHANIEL <i class='fas fa-caret-down' style='font-size:20px'></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="/user-logout">Logout</a>
                    </div>
                </div>

                <i class='far fa-user-circle open' style='font-size:22px' onclick="openNav()" ></i>
                <div id="myNav" class="overlay">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                    <div class="overlay-content">
                        <p style="color: black">Daniel  Nkwenti</p>
                        <a href="/provider_logout">Logout</a>

                    </div>
                </div>
            </div>
        </div>

        <nav class="sg-header-nav" id="mySanagaNav" >
            <div class="sanaga-tabs" >
                <button class="sanaga-tab-button active" onclick="displayTabItems('appointment', this)">Appointments</button>
                <button class="sanaga-tab-button" onclick="displayTabItems('labs', this)">Labs</button>
<%--                <button class="sanaga-tab-button" onclick="displayTabItems('messages', this)">Messages</button>--%>
                <button class="sanaga-tab-button" onclick="displayTabItems('availability', this)">Availability</button>
<%--                <button class="sanaga-tab-button" onclick="displayTabItems('pharmacy', this)">Availability</button>--%>

            </div>
        </nav>

        <main sanaga-page="content subcontainer">
            <div class="sanaga-tab-content active" id="appointment" style="display: none">
                You have an upcoming appointment with
            </div>
            <div class="sanaga-tab-content" id="labs" style="display: none">
                There are no labs for now..
            </div>
            <div class="sanaga-tab-content" id="availability" style="display: none">
                <form:form method="post" action="/" modelAttribute="availability">
                    <label for="start" style="text-align: center">Start Time:</label>
                    <form:input path="startTime"  id="start" required="required" type="time"/>
                    <label for="end">End Time:</label>
                    <form:input path="endTime" id="end" required="required" type="time"/>
                    <label for="localDate">Date:</label>
                    <form:input path="localDate" required="required" type="date"/>
                </form:form>
            </div>
            <div class="sanaga-tab-content" id="messages" style="display: none">
                There are no messages...
            </div>
        </main>

    </header>
    </body>
</html>


<script>
        function displayTabItems(tabItem, button) {
        let element = document.querySelectorAll('.sanaga-tab-content');
        for (let content of element) {
        content.style.display = 'none';
    }

        let buttonElements = document.querySelectorAll('.sanaga-tab-button');
        for (let content of buttonElements) {
        content.classList.remove('active');
    }

        document.getElementById(tabItem).style.display='block';
        button.classList.add('active')
    }


        function openNav() {
            document.getElementById("myNav").style.width = "70%";
        }

        function closeNav() {
            document.getElementById("myNav").style.width = "0%";
        }

</script>
<style>
    .open{
        display: none;
    }

    @media screen  and (max-width: 600px){
        .sg-header-nav {
            display: block;
            flex-direction: column;
        }

        .sg-header-top{
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }

        .sanaga-tab-button{
            display: grid;
            grid-template-columns: 2fr;

        }


        .dropdown{
            display: none;
        }

        .open{
            display: block;
        }
    }
</style>
