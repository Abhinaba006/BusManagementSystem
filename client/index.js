
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

function deleteAllCookies() {
	document.cookie.split(";").forEach(function (c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
}
function logOut(){
	deleteAllCookies();
	window.location = '/client/user_login.html';
}
function displayDestinations(event) {
	event.preventDefault();
	$.get("http://localhost:8080/api/v1/destination/get", function (data) {
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
	}).fail(function () {
		return new alert("Server error! Please try again!")
	})
}
function displayDestinations1(event) {
	event.preventDefault();
	$.get("http://localhost:8080/api/v1/destination/get", function (data) {
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
	}).fail(function () {
		return new alert("Server error! Please try again!")
	})
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
	$.get("http://localhost:8080/api/v1/route/getBySrcDest/" + source + "/" + dest, function (data) {
		console.log(data);

		for (var i = 0; i < data.length; i++) {
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
				userId: 1, //To be changed
			};
			obj["route_id"] = route_id;
			$.get("http://localhost:8080/api/v1/route/getDestinations/" + route_id, function (data) {
				console.log(data);
				obj["source_name"] = data[0].destination.name;
				obj["source_time"] = data[0].time;

				obj["destination_name"] = data[data.length - 1].destination.name;
				obj["destination_time"] = data[data.length - 1].time;

				$.get("http://localhost:8080/api/v1/route/getReport/" + route_id + "/" + formattedDate, function (data) {
					var diff = data.total_seats - data.total_bookings;
					obj["seatsLeft"] = max(0, diff);
					$.get("http://localhost:8080/api/v1/route/getBus/" + route_id, function (data) {
						obj["busName"] = data.name;
						obj["busId"] = data.id;
						obj["busNumber"] = data.bus_number;

						//To be changed
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
									<div class="content-element">

									<div class="content-element-main">
										<div class="content-element-main-divs">
										<div class="bus-name-div">
											<div class="bus-icon"><img src="./public/vector4.svg" alt=""></div>
											<div class="bus-name-text">${obj.busName}</div>
										</div>
										<div class="bus-no">${obj.busNumber}</div>
										</div>
										<div class="content-element-main-divs pickup-drop">
										<div class="pickup-div">
											<div class="location-icon"><img class="location-icon-img" src="./public/placeholder-14@2x.png" alt=""></div>
											<div class="pickup-text">Pickup</div>
										</div>
										<div class="pickup-location-text">${obj.source_name}</div>
										<div class="pickup-time-text">${obj.source_time}</div>
										</div>
										<div class="content-element-main-divs pickup-drop">
										<div class="drop-div">
											<div class="location-icon"><img class="drop-icon-img" src="./public/placeholder-14@2x.png" alt=""></div>
											<div class="drop-location">Drop</div>
										</div>
										<div class="drop-location-text">${obj.destination_name}</div>
										<div class="drop-time-text">${obj.destination_time}</div>
										</div>
										<div class="content-element-main-divs seats-left">
										<div class="seats-left-div">
											<div class="seats-left-text">Seats left</div>
										</div>
										<div class="seats-left-number">${obj.seatsLeft}</div>
										</div>
										<div class="content-element-main-divs">
										<div class="see-route-div" onclick="homeonroute()">
											<div class="route-icon"><img src="./public/route-icon.svg" alt=""></div>
											<div class="see-route-text">See Route</div>
										</div>
										<div id = "getRouteId-${i}" value = ${obj.route_id} ></div>
										<div id = "getBusId-${i}" value = ${obj.busId} ></div>
										<div id = "getUserId-${i}" value = ${obj.userId} ></div>
										<div id = "getDate-${i}" value = ${obj.date} ></div>
										<button class="book-btn-div" onclick=" bookTicket(event)">
											<div class="btn-search-icon-div"><img class="btn-search-icon" src="./public/white-book-icon.svg"></div>
											<div route_id = ${obj.route_id} bus_id =  ${obj.busId} user_id = ${obj.userId} date =  ${formattedDate} class="book-text">Book</div>
										</button>
										</div>
									</div>
									</div>
								`;
						const parentDiv = document.querySelector(".content");
						parentDiv.innerHTML += routeHTML;
					}).fail(function () {
						return alert("Server error! Please try again!")
					})
				}).fail(function () {
					return alert("Server error! Please try again!");
				});
			}).fail(function () {
				return alert("Server error! Please try again!");
			});




		}
	}).fail(function () {
		alert("Server error! Please try again later.")
	})

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
			alert("Ticket booked successfully!")
		},
		error: function (xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
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
function homeonroute() {
	document.querySelector(".home-route-overlay").style.display = "block";
}
function homeoffroute() {
	document.querySelector(".home-route-overlay").style.display = "none";
}

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




