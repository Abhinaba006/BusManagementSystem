

var nrifintech="NRI TRAVEL";
var booking = `My Bookings`; 
var logout="Logout";

document.querySelector(".nrifintech").innerHTML=nrifintech;
document.querySelector(".booking").innerHTML=booking;
document.querySelector(".logout").innerHTML=logout;

function openSidebar() {
	console.log("OPEN")
	document.getElementById("mySidebar").style.display = "block";
  }
  
  function closeSidebar() {
	document.getElementById("mySidebar").style.display = "none";
  }


  function firstdiv(){
	document.getElementById("firstdiv").style.display = "block";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="red";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="black";
  }

  function seconddiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "block";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="red";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="black";
  }

  function thirddiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "block";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="red";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="black";
  }

  function fourthdiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "block";
	document.getElementById("fifthdiv").style.display = "none";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="red";
	document.getElementById("text5").style.color="black";
  }

  function fifthdiv(){
	document.getElementById("firstdiv").style.display = "none";
	document.getElementById("seconddiv").style.display = "none";
	document.getElementById("thirddiv").style.display = "none";
	document.getElementById("fourthdiv").style.display = "none";
	document.getElementById("fifthdiv").style.display = "block";
	document.getElementById("text1").style.color="black";
	document.getElementById("text2").style.color="black";
	document.getElementById("text3").style.color="black";
	document.getElementById("text4").style.color="black";
	document.getElementById("text5").style.color="red";
  }


  $(document).ready(function() {
	var fieldIndex = 1;
	$(".add-field").on("click", function(event) {
		event.preventDefault();
	// Create a new row with two text fields
	var newRow = "<tr><td><input type='text' name='name'></td><td><input type='text' name='email'></td></tr>";
	
	// Append the new row to the table body
	$(".container").append(newRow);
	});
  });

function addEmployee(event){
	event.preventDefault();
	const name = $("#add-employee-name").val();
	const email = $("#add-employee-email").val();
	const password = $("#add-employee-password").val();
	console.log(name,email,password);
	if(name == "" || email == "" || password == ""){
		return alert("Please provide valide credentials for a employee");
	}
	$.ajax({
		url: "http://localhost:8080/api/v1/user/create",
		type: "POST",
		data: JSON.stringify({
		name:name,
		email:email,
		password:password
	}),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("User added successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});


}


  function on() {
	//Getall user
	$.get("http://localhost:8080/api/v1/user/get",function(data){
		console.log(data)
		const email = $("#search-email").val();
		if(email == ""){
			return alert("Please provide an email");
		}
		var found = 0;
		let user = null;
		for(var i = 0;i<data.length;i++){
			if(data[i].email == email){
				found = 1;
				user = data[i];
				document.querySelector(".search-overlay").style.display="block";
			}
		}
		if(found == 0){
			return alert("No employee found!")
		}
		$("#employee-name").val(user.name);
		$("#employee-email").val(user.email);
	}).fail(function(){
		alert("Server error");
	})
	
}
function addDestination(event){
	event.preventDefault();
	const name = $("#dest-name").val();
	const latitude = $("#dest-latitude-input").val();
	const longitude = $("#dest-longitude-input").val();
	console.log(name,latitude,longitude);
	if(name == "" || latitude == "" || longitude == ""){
		return alert("Please provide all details!");
	}
	$.ajax({
		url: "http://localhost:8080/api/v1/destination/create",
		type: "POST",
		data: JSON.stringify({
		name:name,
		latitude:latitude,
		longitude:longitude
	}),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("Destination added successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});
}

function off() {
	document.querySelector(".search-overlay").style.display="none";
}

function on2(event) {
	event.preventDefault();
	const busNumber = $("#bus-search-name").val();
	console.log(busNumber);
	$.get("http://localhost:8080/api/v1/bus/get",function(data){
		console.log(data[0]);
		let bus = null;
		for(var i = 0;i<data.length;i++){
			if(data[i].bus_number == busNumber){
				bus = data[i];
			}
		}
		if(bus == null){
			return alert("No bus found!");
		}
		console.log(bus);
		document.querySelector(".bus-overlay").style.display="block";
		$("#bus-number-o").val(bus.bus_number);
		$("#bus-name-o").val(bus.name);
		$("#bus-seats-o").val(bus.numberOfSeats);
	});
	
}

function off2() {
	document.querySelector(".bus-overlay").style.display="none";
}

function on3(event) {
	event.preventDefault();
	const destName = $("#dest-search-name").val();
	console.log(destName);
	$.get("http://localhost:8080/api/v1/destination/get",function(data){
		console.log(data[0]);
		let dest = null;
		for(var i = 0;i<data.length;i++){
			if(data[i].name == destName){
				dest = data[i];
			}
		}
		if(dest == null){
			return alert("No destinations found!");
		}
		document.querySelector(".destination-overlay").style.display="block";
		$("#dest-edit-name").val(dest.name);
		$("#dest-edit-longitude-input").val(dest.longitude)
		$("#dest-edit-latitude-input").val(dest.latitude);

	})
	
}

function addBus(event){
	event.preventDefault();
	const busNumber =  $("#bus-number").val();
	const seats = $("#bus-seats").val();
	const busName = $("#bus-name").val();
	console.log(busNumber,seats,busName);
	if(busNumber == "" || seats == "" || busName == ""){
		return alert("Please provide all fields");
	}
	$.ajax({
		url: "http://localhost:8080/api/v1/bus/create",
		type: "POST",
		data: JSON.stringify({
		name:busName,
		bus_number : busNumber,
		numberOfSeats:seats
	}),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("Bus added successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});

}
function off3() {
	document.querySelector(".destination-overlay").style.display="none";
}


function add_field() {
	var table = document.querySelector(".container");
	var row = table.insertRow(-1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);

	var div1 = document.querySelector(".from");
	var newDiv1 = document.createElement("div");
	newDiv1.innerHTML = div1.innerHTML;
	cell1.appendChild(newDiv1);

	var div2 = document.querySelector(".to");
	var newDiv2 = document.createElement("div");
	newDiv2.innerHTML = div2.innerHTML;
	cell2.appendChild(newDiv2);


  }

  const save_destination=(evt)=>{
	evt.preventDefault();
	const name = $("#dest-edit-name").val();
	const latitude = $("#dest-edit-latitude-input").val();
	const longitude = $("#dest-edit-longitude-input").val();

	$.ajax({
		url: "http://localhost:8080/api/v1/destination/update",
		type: "POST",
		data: JSON.stringify({
		name:name,
		latitude:latitude,
		longitude:longitude
	}),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("Destination updated successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});
  }

  document.querySelector('.save-button').addEventListener('click', save_destination);


  const delete_destination=(evt)=>{
	evt.preventDefault();
  }
