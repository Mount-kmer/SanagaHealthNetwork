

function validateDateOfBirth(dateOfBirthId,  minAge, messageId){
    let dateField = document.getElementById(dateOfBirthId).value;
    let errMessage = document.getElementById(messageId);

    const dobValue  = new Date(dateField);
    const currentDate = new Date();
    let age = currentDate.getFullYear() - dobValue.getFullYear();

    if (currentDate.getMonth() < dobValue.getMonth() || (currentDate.getMonth() === dobValue.getMonth() && currentDate.getDate() < dobValue.getDate())) {
        age--;
    }

    if (age < minAge) {
        errMessage.style.display ="block";
        dateField.value = "";
        dateField.focus();
        return false;
    }

    return true;
}

function consumeAgeValidator(formId, dateOfBirthId, minAge, messageId) {
    const form = document.getElementById(formId);
    let submit = document.getElementById("button");
    form.addEventListener("submit", function (event){
        if (!validateDateOfBirth(dateOfBirthId, minAge, messageId)) {
            event.preventDefault();
            submit.disable = true;
        }
    });
}
