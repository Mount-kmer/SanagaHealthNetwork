

const passwordReq = [
    { id: "lower", regex: /[a-z]/},
    { id: "upper", regex: /[A-Z]/ },
    { id: "number", regex: /[0-9]/ },
    {id:  "specialChar",regex: /[^A-Za-z0-9]/},
    { id: "length", regex: /.{8,}/ }
]

function displayAuthRequirements(passwordInputField, authRequirementsId, authRequirementList = passwordReq){
    console.log("you called me")
    const authField = document.getElementById(passwordInputField);
    const authRequirements = document.getElementById(authRequirementsId);

    if (!authField || !authRequirements) return;

    authField.onfocus = () => {
        console.log("show message")
        authRequirements.style.display ='grid';
    }

    authField.onblur = () => {
        authRequirements.style.display = 'none';
    }

    authField.onkeyup = () => {
        console.log("keyup event")
        for (let req of authRequirementList) {
            let domElement = document.getElementById(req.id);
            if (!domElement ) continue;

            if (authField.value.match(req.regex)) {
                domElement.classList.remove('invalid');
                domElement.classList.add('valid');
            } else {
                domElement.classList.remove('valid');
                domElement.classList.add('invalid');
            }
        }
    }
}

document.addEventListener("DOMContentLoaded", () => {
    displayAuthRequirements("password", "requirements")
})