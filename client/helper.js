


$.ajax({
    url: 'http://localhost:8080/api/v1/issues/' + id + '/resolve',
    headers: {
        "Authorization": getTokenCookie(),
        "Content-Type": "application/json"
    },
    type:"POST",
    success: function (response) {
        // Do something if the POST request is successful
        console.log('Data posted to database', response);
        //refresh the page if any issue is resolved.
        var link = document.getElementById('text6');
        link.click();

    },
    error: function (error) {
        // Do something if the POST request fails
        console.log(error);
    }
});