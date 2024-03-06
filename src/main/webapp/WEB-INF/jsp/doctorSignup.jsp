<%--
  User: Daniel Nkwenti
  Date: 7/19/23
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/fragments/fragments.jsp"%>
<html>
<head>
    <title>SanagaHealth | Provider-SignUp Cameroon Doctors</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/signup.css"/>
    <script type="text/javascript" src="js/header.js" ></script>
    <script type="text/javascript" src="js/authentication.js" ></script>

<script>

    document.addEventListener('DOMContentLoaded', function() {
        let dateInput = document.getElementById('dateInput');

        dateInput.addEventListener('input', function() {
            let value = dateInput.value;
            let formattedValue = formatAsPartialDate(value); // Call the formatting function

            dateInput.value = formattedValue;
        });
    });

    function formatAsPartialDate(input) {
        let cleaned = input.replace(/\D/g, '');
        let formatted = '';

        if (cleaned.length > 0) {
            formatted += cleaned.substr(0, 2) || '__';
        }

        if (cleaned.length > 2) {
            formatted += '/' + cleaned.substr(2, 2) || '__';
        }

        if (cleaned.length > 4) {
            formatted += '/' + cleaned.substr(4, 4) || '____';
        }

        return formatted;
    }

    document.addEventListener("DOMContentLoaded", function() {
        const input = document.getElementById("phone");
        const iti = window.intlTelInput(input, {
            onlyCountries: ["cm"],
            utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
            preferredCountries: ["cm"],
            separateDialCode: true,
            initialCountry: "cm"
        });
    });

    document.addEventListener("DOMContentLoaded", () => {
        displayAuthRequirements("password", "requirements")
    })
</script>

    <style>


        input[type=text], input[type=password],input[type=email], input[type=date], input[type=tel] {
            padding: 12px 20px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 12px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background: white;
            display: block;
            width: 100%;
            justify-content: space-between;
        }

        @media screen and (max-width: 45rem) {
            input[type=text] , input[type=password], input[type=date], input[type=email]{
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
            .input-container{
                grid-template-columns: 1fr;
                grid-gap: 1.5rem;
                position: relative;
            }

            .input-box{
                /*margin-left: 11px;*/
            }

            select{
                width: 100%;
                display: inline-block;
            }

        }

        .input-container{
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(15em, 1fr));
        }

        .input-box{
            margin-left: 12px;
            margin-bottom: 3px;
        }

        select{
            width: 100%;
            padding: 14px 20px;
            margin-left: auto;
            margin-right: auto;
            /*margin-top: 12px;*/
            border: 1px solid #ccc;
            border-radius: 5px;
            background: white;
            display: block;
            justify-content: space-between;
        }

    </style>

</head>
<body>

    <header-component></header-component>
    <main sanaga-page="content subcontainer">
        <h1 style="font-size: 30px; line-height: 1.5em" >Welcome to SanagaHealth dear provider.</h1>
        <p style="font-size: 18px; margin-top: 18px">First, lets get some basic information to get started.</p>
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
            <form:form action="/provider_signup" method="post" modelAttribute="doctorForm" >
                <form:input path="firstName" type="text" placeholder="First Name" required="required" pattern="^[a-zA-Z][a-zA-Z-]{1,}$" name="first-name"  />
                <form:input path="lastName" type="text" placeholder="Last Name" required="required" pattern="^[a-zA-Z][a-zA-Z-]{1,}$"  name="last-name" />
                <form:input path="dateOfBirth" type="date" placeholder="Date of Birth" required="required" name="dob" />
                <form:input path="email"  type="text" placeholder="Email" required="required" name="email" />
                <form:input path="password" type="password" placeholder="Password" required="required"
                            name="password" id="password"
                            title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" /><br>

                <div class="row" id="requirements">
                    <h3>Password Requirements</h3>
                    <p id="lower" class="invalid">A lowercase letter</p>
                    <p id="upper" class="invalid">A capital (uppercase) letter</p>
                    <p id="number" class="invalid">A number</p>
                    <p id="specialChar" class="invalid">A special character</p>
                    <p id="length" class="invalid">Minimum 8 characters</p>
                </div>
                <div class="input-container">
                    <div class="input-box">
                        <form:input path="phoneNumber" id="phone" type="tel"  placeholder="" required="required" name="phone-number"/>
                    </div>
                    <div class="input-box">
                        <form:select path="hospitalId">
                            <form:option value="" label="Select Hospital" />
                            <c:forEach items="${hospitalList}" var="hospital">
                                <form:option value="${hospital.id}">${hospital.hospitalName}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="input-box">
                        <form:select path="practice">
                            <form:option value="" label="Select Practice" />
                            <form:options items="${practiceList}"/>
                        </form:select><br>
                    </div>

                </div>
                <form:select path="gender">
                    <form:option value="" label="Gender" />
                    <form:options items="${genderItems}"/>
                </form:select><br>
<%--                <form:button>Register</form:button>--%>
                <input type="submit" value="Register">
            </form:form>
        </div>
        <p>${error}</p>
    </main>

</body>
</html>
