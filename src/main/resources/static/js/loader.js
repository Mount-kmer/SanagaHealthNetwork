
function spinner() {
    let timeout = setTimeout(displayPageAfterSpinner, 3000);
}

function displayPageAfterSpinner() {
    document.getElementById("spinner").style.display="none";
    // document.body.innerHTML ="Loading";
    document.getElementById("spinnerDiv").style.display="block";
}