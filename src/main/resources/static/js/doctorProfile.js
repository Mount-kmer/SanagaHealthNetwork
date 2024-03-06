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
    button.classList.add('active');

    //
    if (tabItem !== 'appointment' || !window.history.state || window.history.state.view !== 'default') {
        history.pushState({view: tabItem}, '', '/userprofile/' + tabItem);
    }


}




function openNav() {
    document.getElementById("myNav").style.width = "70%";
}

function closeNav() {
    document.getElementById("myNav").style.width = "0%";
}