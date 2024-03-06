<%--
 
  User: Daniel Nkwenti
  Date: 10/11/23
  Time: 4:27 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <title>SanagaHealth Cameroon| SignUp See Doctors In Cameroon</title>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <script type="text/javascript" src="js/header.js" ></script>
</head>

<style>
    .cardContainer{
        display: flex;
        /*justify-content: center;*/
        gap: 60px;
        margin-top: 33px;
    }

    .cardDecision{
        border: 1px solid lightsteelblue;
        padding: 20px;
        width: 100%;
        height: 60px;
        text-align: center;
        transition: background-color 0.3s;
    }

    .cardDecision:hover{
        background-color: lightsteelblue;
    }

    .cardDecision a {
        text-decoration: none;
        color: black;
    }
</style>
<body>
    <header-component></header-component>
    <main sanaga-page="content subcontainer">
        <h1 style="font-size: 30px; line-height: 1.5em">Getting access to quality care starts here.</h1><br>
        <p>As a patient when you create an account, you will be able to schedule appointments,
            see lab results and prescriptions all from your own personalized dashboard.
            Want to see doctors available first? Find doctors and providers <a href="/search" style="text-decoration: none"> here</a></p>
        <p style="font-size: 18px"></p><br>
        <p style="font-size: 18px">As a Health Care provider, providing personalized care to your patients have never been easier.</p><br>
        <h1 style="font-size: 23px">What type of user are you?</h1>

        <div class="cardContainer">
            <div class="cardDecision">
                <a href="/goToSignup"><p>Patient</p></a>
            </div>
            <p>OR</p>
            <div class="cardDecision">
                <a href="/provider_join"><p>Provider</p></a>
            </div>
        </div>
    </main>
</body>
</html>
