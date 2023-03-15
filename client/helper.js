


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





$.get("http://localhost:8080/api/v1/route/getReport/" + route_id + "/" + formattedDate, function (data) {
  var diff = data.total_seats - data.total_bookings;
  obj["seatsLeft"] = Math.max(0, diff);

  $.ajax({
    url: "http://localhost:8080/api/v1/route/getBus/" + route_id,
    method: "GET",
    headers: {
      "Authorization": getTokenCookie(),
      "Content-Type": "application/json"
    },
    success: function(data) {
      obj["busName"] = data.name;
      obj["busId"] = data.id;
      obj["busNumber"] = data.bus_number;
      obj["userId"] = 1;
      
      console.log(obj);
      result.push(obj);
      // {
      // 	"routeId":5,
      // 	"busId":4,
      // 	"userId":1,
      // 	"status":"CONFIRMED",
      // 	"date":"10:03:2023"
      // }
      
      const routeHTML = `
        <div class="index_route_item" style="width:80%">
          <div class="index_source">
            <div class="index_heading">Bus details</div>
            <div class="index_bus_number">${obj.busName}</div>
            <div class="index_bus_name">${obj.busNumber}</div>
          </div>
          <div class="index_source">
            <div class="index_heading">Source</div>
            <div class="index_value">${obj.source_name}</div>
            <div class="index_value">${obj.source_time}</div>
          </div>
          <div class="index_source">
            <div class="index_heading">Destination</div>
            <div class="index_value">${obj.destination_name}</div>
            <div class="index_value">${obj.destination_time}</div>
          </div>
          <div class="index_source">
            <div class="index_heading">Seats left</div>
            <div class="index_value">${obj.seatsLeft}</div>
          </div>
          <div class="index_source">
            <div class="index_book" route_id=${obj.route_id} bus_id=${obj.busId} user_id=${obj.userId} date=${formattedDate} onclick="deleteRoute(event,${obj.route_id})" style="background-color:orangered;">Delete</div>
          </div>
        </div>
      `;
      
      const parentDiv = document.querySelector(".admin_routes");
      parentDiv.innerHTML += routeHTML;
    },
    error: function() {
      return alert("Server error! Please try again!");
    }
  });
  
}, function () {
  return alert("Server error! Please try again!");
}, {
  headers: {
    "Authorization": getTokenCookie(),
    "Content-Type": "application/json"
  }
});
