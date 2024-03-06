
function selectSlot(buttonEl, timeslot, event) {

    event.preventDefault();
    let buttons = document.getElementsByClassName('time-slot');
    for (let button of buttons) {
        button.classList.remove('selected');
    }

    buttonEl.classList.add('selected');

    //set timeslot value

    document.getElementById("selectedTimeSlot").value = timeslot;

    let selectedDate = document.querySelector(".date-input").value;
    document.getElementById('selectedDate').value = selectedDate;
    console.log("selected date is " + selectedDate);

    document.getElementById("submit-btn").disabled = false;
}

function formatTimeSlotsAmPm(hour, minute) {
    const amPm =  hour >= 12 ? 'PM' : 'AM';
    hour = hour % 12 || 12;
    minute = minute < 10 ? '0' + minute : minute;
    return hour + ':' + minute + ' ' + amPm;
}

function displayScheduleButton() {
    const timeSlot = document.getElementById("").style.display ='block';
}


function getOpenCalendarSlots(selectedDate) {

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4) {
            if (xhttp.status === 200) {

                const formattedDate = formatDate(selectedDate);

                document.getElementById("slot-title").innerHTML="Available time slots for " + formattedDate;
                let response = JSON.parse(xhttp.responseText);

                let timeSlots = "";
                // timeSlots += "<button class='time-slot' onclick='selectSlot(this, \"" + formattedTimeSlots + "\", event)'>" + formattedTimeSlots + "</button> ";


                for (let i = 0; i < response.length; i++) {
                    let slot = response[i];
                    // let formattedTimeSlots = slot[0] + ':' + (slot[1] < 10 ? '0' : '') + slot[1];
                    let formattedTimeSlots = formatTimeSlotsAmPm(slot[0], slot[1]);
                    timeSlots +=  "<button class='time-slot' onclick='selectSlot(this, \"" + formattedTimeSlots + "\", event) '>" + formattedTimeSlots + "</button> ";
                }
                document.getElementById("date-picker").innerHTML = timeSlots;
                sessionStorage.setItem('timeSlots', timeSlots);
                sessionStorage.setItem('selectedDate', selectedDate);
                document.getElementById("submit-btn").style.display ='block';
                document.getElementById("submit-btn").disabled = true;

                console.log("Formatted slots: " + timeSlots);
                console.log(JSON.parse(xhttp.responseText));

            } else {
                console.error("Error fetching calendar slots:  " + xhttp.status + " " + xhttp.statusText)
                document.getElementById("error-events").innerHTML = "Error fetching calendar slots: " + xhttp.statusText.toString();
            }
        }
    };

    xhttp.open("GET", "/calendar-events?date=" + encodeURIComponent(selectedDate), true);
    xhttp.send();
}