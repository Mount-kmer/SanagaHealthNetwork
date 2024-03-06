// Get the current date
let currentDate = new Date().toISOString().split("T")[0];

// Get the date input element
let dateInput = document.getElementById("date-input");

// Set the minimum selectable date to the current date
dateInput.setAttribute("min", currentDate);

// Generate the time slots based on availability
dateInput.addEventListener("change", function() {
    let selectedDate = this.value;
    let timeSelect = document.getElementById("time");

    // Remove existing options
    while (timeSelect.firstChild) {
        timeSelect.removeChild(timeSelect.firstChild);
    }

    // Simulate booked time slots
    let bookedTimes = ["09:00", "13:30", "16:15"]; // Replace with your actual booked time slots

    // Generate available time slots based on the selected date
    let availableTimes = generateAvailableTimeSlots(selectedDate, bookedTimes);

    // Add options to select element
    for (let i = 0; i < availableTimes.length; i++) {
        let option = document.createElement("option");
        option.value = availableTimes[i];
        option.text = availableTimes[i];
        timeSelect.appendChild(option);
    }
});

// Function to generate available time slots based on selected date and booked appointments
function generateAvailableTimeSlots(selectedDate, bookedTimes) {
    let availableTimes = [];

    // Define your time slot intervals and range here
    let startTime = "08:00";
    let endTime = "17:00";
    let interval = 60; // in minutes

    let currentDate = new Date().toISOString().split("T")[0];

    // If selected date is the current date, consider only future time slots
    if (selectedDate === currentDate) {
        let currentTime = new Date();
        let currentHour = currentTime.getHours();
        let currentMinute = currentTime.getMinutes();

        let roundedMinute = Math.ceil(currentMinute / interval) * interval;
        if (roundedMinute === 60) {
            currentHour++;
            roundedMinute = 0;
        }

        let currentFormattedTime =
            ("0" + currentHour).slice(-2) + ":" + ("0" + roundedMinute).slice(-2);
        startTime = currentFormattedTime;
    }

    let currentTime = startTime;

    while (currentTime <= endTime) {
        let formattedTime = formatTime(currentTime);
        if (!bookedTimes.includes(formattedTime)) {
            availableTimes.push(formattedTime);
        }
        currentTime = addMinutes(currentTime, interval);
    }

    return availableTimes;
}

// Function to add minutes to a given time string (HH:MM format)
function addMinutes(time, minutes) {
    let timeParts = time.split(":");
    let hours = parseInt(timeParts[0]);
    let mins = parseInt(timeParts[1]);

    let newMins = mins + minutes;
    let newHours = hours + Math.floor(newMins / 60);
    newMins = newMins % 60;

    return ("0" + newHours).slice(-2) + ":" + ("0" + newMins).slice(-2);
}

// Function to format time to HH:MM format
function formatTime(time) {
    let timeParts = time.split(":");
    let hours = parseInt(timeParts[0]);
    let mins = parseInt(timeParts[1]);

    return ("0" + hours).slice(-2) + ":" + ("0" + mins).slice(-2);
}
