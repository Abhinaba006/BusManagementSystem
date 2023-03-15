$.ajax({
    url:  "http://localhost:8080/api/v1/user/validateToken",
    type: "GET",
    headers: {
        "Authorization": getTokenCookie(),
        "Content-Type": "application/json"
    }, 
    error: function()
    {
        window.location.replace("google.com");
    } 
});






































