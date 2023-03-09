
/* 

    @author: Arijit Saha
	@desc: js for all the html files 
	@file path from root: Ex ./index.js/

*/
// var cors = require('cors');
var nrifintech="NRI TRAVEL";
var booking = `My Bookings`; 
var logout="Logout";
var home="Home";

// document.getElementById("nrifintech").innerHTML=nrifintech;
// document.getElementById("booking").innerHTML = booking; 
// document.getElementById("logout").innerHTML = logout;

// document.querySelector(".nrifintech").innerHTML=nrifintech;
// document.querySelector(".booking").innerHTML=booking;
// document.querySelector(".logout").innerHTML=logout;
// document.querySelector(".home").innerHTML=home;

function displayDestinations(event){
	event.preventDefault();
	$.get("http://localhost:8080/api/v1/destination/get",function(data){
		console.log(data);
		var selectElement = document.getElementById('user-from');

		// Add options from the data array
		data.forEach(function(item) {
		var optionElement = document.createElement('option');
		optionElement.value = item.id;
		optionElement.textContent = item.name;
		var flag = 1;
		for(var i = 0; i < selectElement.options.length;i++){
			if(item.id == selectElement.options[i].value) flag = 0;
		}
		if(flag || selectElement.options.length == 1)
			selectElement.appendChild(optionElement);
});
	}).fail(function(){
		return new alert("Server error! Please try again!")
	})
}
function displayDestinations1(event){
	event.preventDefault();
	$.get("http://localhost:8080/api/v1/destination/get",function(data){
		console.log(data);
		var selectElement = document.getElementById('user-to');

		// Add options from the data array
		data.forEach(function(item) {
		var optionElement = document.createElement('option');
		optionElement.value = item.id;
		optionElement.textContent = item.name;
		var flag = 1;
		for(var i = 0; i < selectElement.options.length;i++){
			if(item.id == selectElement.options[i].value) flag = 0;
		}
		if(flag || selectElement.options.length == 1)
			selectElement.appendChild(optionElement);
});
	}).fail(function(){
		return new alert("Server error! Please try again!")
	})
}
function getRoutes(event){
	event.preventDefault();
	const source = $("#user-from").val();
	const dest = $("#user-to").val();
	console.log(source,dest);
	$.get("http://localhost:8080/api/v1/route/getBySrcDest/"+source+"/"+dest,function(data){
		console.log(data);
		for(var i = 0;i<data.length;i++){
			const route_id = data[i].id;
			$.get("http://localhost:8080/api/v1/route/getDestinations/" + route_id,function(data){
				console.log(data);
			}).fail(function(){
				return alert("Server error! Please try again!");
			});

		}
	}).fail(function(){
		alert("Server error! Please try again later.")
	})

}
function togglepassword() {
	var x = document.getElementById("mypassword");
	var y=document.getElementById("mypassword-icon");
	if (x.type === "password") {
		
	  	
		y.src="./public/hide-eye.png";
	  x.type = "text";
	} else {
	  x.type = "password";
	  y.src="./public/eye-icon.svg";
	  y.class="hidden-eye";
	}
}

function on() {
	// document.getElementById("overlay").style.display = "block";
	document.querySelector(".cancel-overlay").style.display="block";
}
  
function off() {
	// document.getElementById("overlay").style.display = "none";
	document.querySelector(".cancel-overlay").style.display="none";
}

function onbooking()
{
	document.querySelector(".booking-cancel-overlay").style.display="block";
}
function offbooking()
{
	document.querySelector(".booking-cancel-overlay").style.display="none";
}

function onroute()
{
	document.querySelector(".route-overlay").style.display="block";
}
function offroute()
{
	document.querySelector(".route-overlay").style.display="none";
}
function homeonroute()
{
	document.querySelector(".home-route-overlay").style.display="block";
}
function homeoffroute()
{
	document.querySelector(".home-route-overlay").style.display="none";
}

/*Fetching functions*/
 //var fromelement ="";
function getfrom()
{
	selectedElementFrom=document.querySelector(".from-id");
	frontelement = selectedElementFrom.options[selectedElementFrom.selectedIndex].value;
	console.log("Got from value"+ frontelement);
	return frontelement;
}

//var toelement ="";
function getto()
{
	selectedElementTo=document.querySelector(".to-id");
	toelement = selectedElementTo.options[selectedElementTo.selectedIndex].value;
	console.log("Got to value"+toelement);
	return toelement;
}




