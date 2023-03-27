

/* 

	@author: Arijit Saha
	@desc: js for all the html files 
	@file path from root: Ex ./index.js/

*/

// var cors = require('cors');
var nrifintech = "NRI TRAVEL";
var booking = `My Bookings`;
var logout = "Logout";
var home = "Home";


// document.getElementById("nrifintech").innerHTML=nrifintech;
// document.getElementById("booking").innerHTML = booking; 
// document.getElementById("logout").innerHTML = logout;

// document.querySelector(".nrifintech").innerHTML=nrifintech;
// document.querySelector(".booking").innerHTML=booking;
// document.querySelector(".logout").innerHTML=logout;
// document.querySelector(".home").innerHTML=home;

function getTokenCookie() {
	const cookieName = "token=";
	const cookies = document.cookie.split("; ");
	for (let i = 0; i < cookies.length; i++) {
		const cookie = cookies[i];
		if (cookie.indexOf(cookieName) === 0) {
			return cookie.substring(cookieName.length, cookie.length);
		}
	}
	return "";
}

function getIdCookie() {
    const cookieString = document.cookie;
    if (cookieString) {
        const cookies = cookieString.split(';');
        for (let i = 0; i < cookies.length; i++) {
            const cookie = cookies[i].trim();
            if (cookie.startsWith('id=')) {
                return parseInt(cookie.substring('id='.length, cookie.length));
            }
        }
    }
    return null;
}

function deleteAllCookies() {
	document.cookie.split(";").forEach(function (c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
}
function logOut(){
	console.log("lof")
	deleteAllCookies();
	window.location = '/client/user_login.html';
}
function displayDestinations(event) {
	event.preventDefault();
	$.ajax({
		url:  "http://localhost:8080/api/v1/destination/get",
		type: "GET",
		headers: {
			"Authorization": getTokenCookie(),
			"Content-Type": "application/json"
		},
		success: function (data) {
			console.log(data);
			var selectElement = document.getElementById('user-from');
	
			// Add options from the data array
			data.forEach(function (item) {
				var optionElement = document.createElement('option');
				optionElement.value = item.id;
				optionElement.textContent = item.name;
				var flag = 1;
				for (var i = 0; i < selectElement.options.length; i++) {
					if (item.id == selectElement.options[i].value) flag = 0;
				}
				if (flag || selectElement.options.length == 1)
					selectElement.appendChild(optionElement);
			});
		}, 
		error: function () {
			return new createAlert("Server error! Please try again!","failure"); //alert("Server error! Please try again!")
		}
	});
}
function displayDestinations1(event) {
	event.preventDefault();
	$.ajax({
		url: "http://localhost:8080/api/v1/destination/get" ,
		type: "GET",
		headers: {
			"Authorization": getTokenCookie(),
			"Content-Type": "application/json"
		},
		success: function (data) {
			console.log(data);
			var selectElement = document.getElementById('user-to');
	
			// Add options from the data array
			data.forEach(function (item) {
				var optionElement = document.createElement('option');
				optionElement.value = item.id;
				optionElement.textContent = item.name;
				var flag = 1;
				for (var i = 0; i < selectElement.options.length; i++) {
					if (item.id == selectElement.options[i].value) flag = 0;
				}
				if (flag || selectElement.options.length == 1)
					selectElement.appendChild(optionElement);
			});
		}, 
		error: function () {
			return new createAlert("Server error! Please try again!","failure");//alert("Server error! Please try again!")
		}
	});
	
}
function max(a, b) {
	if (a >= b) return a;
	return b;
}
function getRoutes(event) {
	event.preventDefault();
	document.querySelector(".content").innerHTML = "";
	console.log("Hitting");
	const source = $("#user-from").val();
	const dest = $("#user-to").val();
	const date = $("#datepicker").val();

	const d = date.split('/');
	var formattedDate = d[1] + ":" + d[0] + ":" + d[2];
	console.log(formattedDate);
	const result = [];
	$.ajax({
		url:  "http://localhost:8080/api/v1/route/getBySrcDest/"+source+"/"+dest,
		type: "GET",
		headers: {
			"Authorization": getTokenCookie(),
			"Content-Type": "application/json"
		},
		success:function(data){
			console.log(data);
		
			document.querySelector(".content").innerHTML+=`
			<div class = "index_show_results">
					<p class = "index_show_results_p">${data.length} results found!</p>
			</div>`
			document.querySelector(".content").innerHTML+=`
			<div class = "index_show_line"></div>
			
			`
			for(var i = 0;i<data.length;i++){
				const route_id = data[i].id;
				const obj = {
					busId: "",
					busName: "",
					busNumber: "",
					destination_name: "",
					destination_time: "",
					route_id: "",
					seatsLeft: "",
					source_name: "",
					source_time: "",
					userId: getIdCookie(), //To be changed
				};
				obj["route_id"] = route_id;
				$.ajax({
					url:  "http://localhost:8080/api/v1/route/getDestinations/" + route_id,
					type: "GET",
					headers: {
						"Authorization": getTokenCookie(),
						"Content-Type": "application/json"
					},
					success: function (data) {
						console.log(data);
						obj["source_name"] = data[0].destination.name;
						obj["source_time"] = data[0].time;
				
						obj["destination_name"] = data[data.length - 1].destination.name;
						obj["destination_time"] = data[data.length - 1].time;
				
						$.ajax({
							url:  "http://localhost:8080/api/v1/route/getReport/" + route_id + "/" + formattedDate,
							type: "GET",
							headers: {
								"Authorization": getTokenCookie(),
								"Content-Type": "application/json"
							},
							success: function (data) {
								var diff = data.total_seats - data.total_bookings;
								obj["seatsLeft"] = max(0, diff);
								$.ajax({
									url:  "http://localhost:8080/api/v1/route/getBus/" + route_id,
									type: "GET",
									headers: {
										"Authorization": getTokenCookie(),
										"Content-Type": "application/json"
									},
									success: function (data) {
										obj["busName"] = data.name;
										obj["busId"] = data.id;
										obj["busNumber"] = data.bus_number;
								
									//To be changed
									obj["userId"] = getIdCookie();
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
								 <div class = "index_route_item">
									<div class = "index_source">
										<div class = "index_heading">Bus details</div>
										<div class = "index_bus_number">${obj.busName}</div>
										<div class = "index_bus_name">${obj.busNumber}</div>
									</div>
									<div class = "index_source">
										<div class = "index_heading">Source</div>
										<div class = "index_value">${obj.source_name}</div>
										<div class = "index_value">${obj.source_time}</div>
									</div>
									<div class = "index_source">
										<div class = "index_heading">Destination</div>
										<div class = "index_value">${obj.destination_name}</div>
										<div class = "index_value">${obj.destination_time}</div>
									</div>
									<div class = "index_source">
										<div class = "index_heading">Seats left</div>
										<div class = "index_value">${obj.seatsLeft}</div>
									   
									</div>
									<div class = "index_source">
										<div class = "index_route" on onclick="homeonroute(${obj.route_id})">See route</div>
										<div class = "index_book" route_id = ${obj.route_id} bus_id =  ${obj.busId} user_id = ${obj.userId} date =  ${formattedDate} onclick=" bookTicket(event)">Book</div>
									</div>
								</div>
												`;
										const parentDiv = document.querySelector(".content");
										parentDiv.innerHTML += routeHTML;
									}, 
									error: function () {
										return createAlert("Server error! Please try again!","failure");
										//return alert("Server error! Please try again!")
									}
								});
							}, 
							error: function () {
								return createAlert("Server error! Please try again!","failure");
								//return alert("Server error! Please try again!");
							}
						});
					}, 
					error: function () {
						return createAlert("Server error! Please try again!","failure");
						//return alert("Server error! Please try again!");
					}
				});
				
	
	
	
	
			}
		} , 
		error: function () {
             createAlert("Server error! Please try again later.","failure");
			//alert("Server error! Please try again later.")
		}
	});

	console.log(result.length);


}

function bookTicket(event) {
	event.preventDefault();
	const ele = event.target;
	const route_id = $(ele).attr("route_id");
	// <div route_id = ${obj.route_id} bus_id =  ${obj.busId} user_id = ${obj.userId} date =  ${obj.date} class="book-text">Book</div>
	const bus_id = $(ele).attr("bus_id");
	const user_id = $(ele).attr("user_id");
	const date = $(ele).attr("date");
	console.log(route_id, bus_id, user_id, date);
	$.ajax({
		url: "http://localhost:8080/api/v1/ticket/create",
		headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
		type: "POST",
		data: JSON.stringify({
			"routeId": route_id,
			"busId": bus_id,
			"userId": user_id,
			"status": "CONFIRMED",
			"date": date
		}),
		contentType: "application/json",
		success: function (result) {
			console.log(result);
			createAlert("Ticket booked successfully!","success");
			
			
			//alert("Ticket booked successfully!");
			// location.reload(); 
		},
		error: function (xhr, status, error) {
			console.log(error);
			if(JSON.parse(xhr.responseText).message === "Ticket already booked")
			  createAlert("First cancel 1 confirmed ticket, then book a ticket.","info"); //alert("First cancel 1 confirmed ticket, then book a ticket.");
			else if(JSON.parse(xhr.responseText).message === "waiting")
			  createAlert("Your ticket is in a waiting list","info");
			else
			createAlert("Oops something went wrong! Please try again","failure");
			//alert("Oops something went wrong! Please try again ");
		}
	});
}
function togglepassword() {
	var x = document.getElementById("mypassword");
	var y = document.getElementById("mypassword-icon");
	if (x.type === "password") {


		y.src = "./public/hide-eye.png";
		x.type = "text";
	} else {
		x.type = "password";
		y.src = "./public/eye-icon.svg";
		y.class = "hidden-eye";
	}
}

function on() {
	// document.getElementById("overlay").style.display = "block";
	document.querySelector(".cancel-overlay").style.display = "block";
}

function off() {
	// document.getElementById("overlay").style.display = "none";
	document.querySelector(".cancel-overlay").style.display = "none";
}

function onbooking() {
	document.querySelector(".booking-cancel-overlay").style.display = "block";
}
function offbooking() {
	document.querySelector(".booking-cancel-overlay").style.display = "none";
}

function onroute() {
	document.querySelector(".route-overlay").style.display = "block";
}
function offroute() {
	document.querySelector(".route-overlay").style.display = "none";
}
function homeonroute(routeId) {
	document.querySelector(".home-route-overlay").style.display = "block";


	// get routeId dynamically
	// put the auth token here

	fetch('http://localhost:8080/api/v1/route/getDestinations/'+routeId, {
		headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        }
		// headers: {
		// 	'Authorization': `{authToken}`
		// }
	})
		.then(response => response.json())
		.then(data => {
			// format the data to use it in html
			data.forEach(d => {
				d["destinationName"] = d.destination.name
				delete d.destination
			})
			console.log(data)
			const containerElem = document.querySelector('.route-overlay-main-div');
			containerElem.innerHTML =""
			for (let i = 0; i < data.length; i++) {
				const time = data[i].time;
				const destinationName = data[i].destinationName;

				const elem = document.createElement('div');
				elem.className = 'route-overlay-main-div-elements-div';
				elem.innerHTML = `
								<div class="route-overlay-time">${time}</div> 
								<img class="route-overlay-icon" src="./public/route-overlay-icon.svg" alt=""> 
								<div class="route-overlay-place">${destinationName}</div>
							`;

				containerElem.appendChild(elem);
			}
		})
		.catch(error => console.error(error));
}

function homeoffroute()
{
	document.querySelector(".home-route-overlay").style.display="none";

 }
// function homeoffroute() {
// 	document.querySelector(".home-route-overlay").style.display = "none";
// >>>>>>> fec760e6080061d95a16414552cb5d45bf4a795f
// }

/*Fetching functions*/
//var fromelement ="";
function getfrom() {
	selectedElementFrom = document.querySelector(".from-id");
	frontelement = selectedElementFrom.options[selectedElementFrom.selectedIndex].value;
	console.log("Got from value" + frontelement);
	return frontelement;
}

//var toelement ="";
function getto() {
	selectedElementTo = document.querySelector(".to-id");
	toelement = selectedElementTo.options[selectedElementTo.selectedIndex].value;
	console.log("Got to value" + toelement);
	return toelement;
}


//see route function
function homeonroute(routeId) {
	document.querySelector(".home-route-overlay").style.display = "block";
        console.log(routeId)
	// get routeId dynamically
	// let routeId = 1
	// put the auth token here
	const authToken = 'your-auth-token-here';

	fetch('http://localhost:8080/api/v1/route/getDestinations/'+routeId, {
		// headers: {
		// 	'Authorization': `{authToken}`
		// }
		headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        }
	})
		.then(response => response.json())
		.then(data => {
			// format the data to use it in html
			data.forEach(d => {
				d["destinationName"] = d.destination.name
				delete d.destination
			})
			// console.log(data)
			let containerElem = document.getElementById('route-container')
			containerElem.innerHTML =""
            console.log(data)
			for (let i = 0; i < data.length; i++) {
				const time = data[i].time;
				const destinationName = data[i].destinationName;

				const elem = document.createElement('div');
				elem.className = 'route-overlay-main-div-elements-div';
				elem.innerHTML = `
								<div class="route-overlay-time">${time}</div> 
								<img class="route-overlay-icon" src="./public/route-overlay-icon.svg" alt=""> 
								<div class="route-overlay-place">${destinationName}</div>
							`;

				containerElem.appendChild(elem);
			}
            console.log(containerElem)
		})
		.catch(error => console.error(error));
}


function createAlert(message, type) {
    var alertContainer = document.getElementById("alert-container");
    var alertBox = document.createElement("div");
    var closeButton = document.createElement("span");

    alertBox.textContent = message;
    closeButton.textContent = "×";
    closeButton.className = "closebtn";
    closeButton.onclick = function() {
      alertContainer.removeChild(alertBox);
	  //location.reload();
	  window.location = '/client/my_bookings.html';
    };

    alertBox.className = "alert " + type;
    alertBox.appendChild(closeButton);
    alertContainer.appendChild(alertBox);

  }


