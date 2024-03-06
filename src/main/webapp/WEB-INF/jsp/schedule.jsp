<%--
  Created by IntelliJ IDEA.
  User: a16124
  Date: 8/18/22
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <title>schedule-a-visit</title>
</head>
<style>
    .btn-with-spinner {
        position: relative;
        padding-left: 30px; /* Adjust based on your button's size and spinner's position */
    }

    .spinner {
        display: none;  /* Initially hidden */
        position: absolute;
        left: 10px;
        top: 50%;
        transform: translateY(-50%);
        border: 4px solid rgba(0, 0, 0, 0.1);
        border-top: 4px solid blue;  /* Change this color based on your button's color */
        border-radius: 50%;
        width: 16px;
        height: 16px;
        animation: spin 1s linear infinite;
    }

    @keyframes spin {
        0% { transform: translateY(-50%) rotate(0deg); }
        100% { transform: translateY(-50%) rotate(360deg); }
    }

</style>

<script>
    function showSpinner(buttonElement) {
        var spinner = buttonElement.querySelector('.spinner');
        spinner.style.display = 'block';
        // You can also disable the button to prevent multiple clicks
        buttonElement.disabled = true;

        setTimeout(function() {
            window.location.href = "/home";  // replace with your target URL
        }, 2000);

        // If you want to hide the spinner after a set time (e.g., 5 seconds), you could use:
        // setTimeout(function() {
        //     spinner.style.display = 'none';
        //     buttonElement.disabled = false;
        // }, 5000);
    }

</script>
<body>
    <p>Page under construction </p>
    <p></p>

    <button class="btn-with-spinner" onclick="showSpinner(this)">
        <div class="spinner"></div>
        Click Me
    </button>

</body>
</html>
