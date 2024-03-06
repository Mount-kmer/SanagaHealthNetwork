function formatDate(dateString) {
    const months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];

    const splitDate = dateString.split('-');
    const year = parseInt(splitDate[0], 10);
    const monthIndex = parseInt(splitDate[1], 10) - 1;
    const day = parseInt(splitDate[2], 10);

    const date = new Date(year, monthIndex, day);

    return `${months[monthIndex]} ${day} ${year}`;
}