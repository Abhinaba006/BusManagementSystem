
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
	const employeeId = $("#add-employee-id").val();

	if(name == "" || email == "" || password == "" || employeeId == ""){
		return alert("Please provide valide credentials for a employee");
	}
	$.ajax({
		url: "http://localhost:8080/api/v1/user/create",
		type: "POST",
		data: JSON.stringify({
		name:name,
		email:email,
		password:password,
		employeeId:employeeId
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

function updateEmployee(event){
	event.preventDefault();
	const name =  $("#employee-name").val();
	const email = $("#employee-email").val();
	const empId = $("#employee-id").val();
	const password = $("#employee-password").val();
	const id = $("#emp-inp-id").val();
	console.log(name,email,empId,password,id);
	if(name == "" || email == "" || empId == ""){
		return alert("Please provide valide credentials for a employee");
	}
	const newObj = {
		name:name,
		email:email,
		employeeId:empId
	}
	if(password!=""){
		newObj["password"] = password;
	}
	$.ajax({
		url: "http://localhost:8080/api/v1/user/update/"+id,
		type: "POST",
		data: JSON.stringify(
		newObj
	),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("User updated successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});


}
function deleteEmployee(event){
	console.log("Hitting");
	event.preventDefault();

	const id = $("#emp-inp-id").val();
	console.log(id);
	$.ajax({
		url: "http://localhost:8080/api/v1/user/delete/" + id,
		type: "DELETE",
		success: function(result) {
			console.log(result);
			alert("Employee details deleted successfully!")
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
			if((data[i].email).toLowerCase() == email.toLowerCase()){
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
		$("#employee-id").val(user.employeeId);
		$("#emp-inp-id").val(user.id);
		$("#employee-password").val("");
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
			if((data[i].bus_number).toLowerCase() == busNumber.toLowerCase()){
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
		$("#bus-seats-o").val(bus.totalNumberOfseats);
		$("#bus-id-o").val(bus.id);
	
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
			if((data[i].name).toLowerCase() == destName.toLowerCase()){
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
		$("#dest-edit-id").val(dest.id);
	})
	
}
 function updateDestination(event){
	console.log("Hitting");
	event.preventDefault();
	const name = 	$("#dest-edit-name").val();
	const longitude = 	$("#dest-edit-longitude-input").val();
	const latitude = $("#dest-edit-latitude-input").val();
	const id = $("#dest-edit-id").val();
	console.log(name,latitude,longitude,id);
	$.ajax({
		url: "http://localhost:8080/api/v1/destination/update/" + id,
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
function deleteDestination(event){
	console.log("Hitting");
	event.preventDefault();
	const name = 	$("#dest-edit-name").val();
	const longitude = 	$("#dest-edit-longitude-input").val();
	const latitude = $("#dest-edit-latitude-input").val();
	const id = $("#dest-edit-id").val();
	console.log(name,latitude,longitude,id);
	$.ajax({
		url: "http://localhost:8080/api/v1/destination/delete/" + id,
		type: "DELETE",
		success: function(result) {
			console.log(result);
			alert("Destination deleted successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});
}
function updateBus(event){
	event.preventDefault();
	const bus_number = $("#bus-number-o").val();
	const bus_name = $("#bus-name-o").val();
	const total_seats = $("#bus-seats-o").val();
	const id = $("#bus-id-o").val();
	console.log(bus_name,bus_number,total_seats,id);
	$.ajax({
		url: "http://localhost:8080/api/v1/bus/update/" + id,
		type: "POST",
		data: JSON.stringify({
			name:bus_name,
			bus_number : bus_number,
			totalNumberOfseats:total_seats
	}),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("Bus updated successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});
}
function deleteBus(event){
	console.log("Hitting");
	event.preventDefault();
	const id = $("#bus-id-o").val();
	$.ajax({
		url: "http://localhost:8080/api/v1/bus/delete/" + id,
		type: "DELETE",
		success: function(result) {
			console.log(result);
			alert("Bus deleted successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});
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
		totalNumberOfseats:seats
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

//For display of the destinations and bus dynamically
function displayBusID(event){
	event.preventDefault();
	$.get("http://localhost:8080/api/v1/bus/get",function(data){
		var selectElement = document.getElementById('select-bus-id');

		// Add options from the data array
		data.forEach(function(item) {
		var optionElement = document.createElement('option');
		optionElement.value = item.id;
		optionElement.textContent = item.bus_number;
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

function addRoute(event){
	event.preventDefault();
	var table = document.querySelector(".container");
	const data = [];
	for(var i = 0;i<table.rows.length;i++){
		const destId = $("#select_" + (i+1)).val();
		const time = $("#time-input_" + (i+1)).val();
		if(destId == "" && time == "") continue;
		else if(destId == "" || time == "") return alert("Please provide valid destination/time combination");
		data.push(destId + "_" + i + "_" + time);
	}
	console.log(data);
	if(data.length <= 1){
		return alert("Total number of destinations should be greater than 1");
	}
	const busId = $("#select-bus-id").val();
	if(busId == "" || busId == undefined) return alert("Please provide a bus id for this route!");
	console.log(busId);
	$.ajax({
		url: "http://localhost:8080/api/v1/route/create/" + busId,
		type: "POST",
		data: JSON.stringify(data),
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			alert("Route added successfully!")
		},
		error: function(xhr, status, error) {
			console.log(error);
			alert("Oops something went wrong! Please try again")
		}
	});

}
function add_field() {
	
	$.get("http://localhost:8080/api/v1/destination/get",function(data){
		console.log(data);
		data.unshift({
			name:"Select destination",
			id:-1
		})

		 // Get the table element
var table = document.querySelector(".container");

// Get the number of rows already present in the table
var rowLength = table.rows.length;

// Create the new table row element
var newRow = document.createElement('tr');

// Create the first table cell for the "from" column
var fromCell = document.createElement('td');

// Create the "from" div element with the select element and label
var fromDiv = document.createElement('div');
fromDiv.className = "from";

var fromToSearchText = document.createElement('div');
fromToSearchText.className = "from-to-search-text";

var fromText = document.createElement('label');
fromText.htmlFor = "fromtext";
fromText.textContent = "Add Bus Stop";
fromToSearchText.appendChild(fromText);

var optionsDiv = document.createElement('div');
optionsDiv.className = "options";

var selectElement = document.createElement('select');
selectElement.name = "";
selectElement.id = "select_" + (rowLength + 1);

// Add options from the data array
data.forEach(function(item) {
  var optionElement = document.createElement('option');
  optionElement.value = item.id;
  optionElement.textContent = item.name;
  selectElement.appendChild(optionElement);
});

optionsDiv.appendChild(selectElement);
fromDiv.appendChild(fromToSearchText);
fromDiv.appendChild(optionsDiv);
fromCell.appendChild(fromDiv);

// Create the second table cell for the "to" column
var toCell = document.createElement('td');

// Create the "to" div element with the input element and label
var toDiv = document.createElement('div');
toDiv.className = "to";

var toToSearchText = document.createElement('div');
toToSearchText.className = "from-to-search-text";

var toText = document.createElement('label');
toText.htmlFor = "timetext";
toText.textContent = "Add Timing";
toToSearchText.appendChild(toText);

var toOptionsDiv = document.createElement('div');
toOptionsDiv.className = "options";

var timeInput = document.createElement('input');
timeInput.type = "time";
timeInput.id = "time-input_" + (rowLength + 1);
timeInput.name = "time";

toOptionsDiv.appendChild(timeInput);
toDiv.appendChild(toToSearchText);
toDiv.appendChild(toOptionsDiv);
toCell.appendChild(toDiv);

// Add the cells to the row
newRow.appendChild(fromCell);
newRow.appendChild(toCell);

// Add the new row to the table
table.appendChild(newRow);
		
	})
	// var table = document.querySelector(".container");
	// var row = table.insertRow(-1);
	// var cell1 = row.insertCell(0);
	// var cell2 = row.insertCell(1);

	// var div1 = document.querySelector(".from");
	// var newDiv1 = document.createElement("div");
	// newDiv1.innerHTML = '<div class="from-to-search-text"><label for="fromtext">Add Bus Stop</label></div><div class="options"><select name="" id="select_dest_0"><option value="option0"></option><option value="option1">Option1</option><option value="option2">Option2</option><option value="option3">Option3</option></select></div>';
	// cell1.appendChild(newDiv1);

	// var div2 = document.querySelector(".to");
	// var newDiv2 = document.createElement("div");
	// newDiv2.innerHTML = div2.innerHTML;
	// cell2.appendChild(newDiv2);


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


// function generateReport(reportType) {
// 	$.ajax({
// 	  url: "http://localhost:8080/api/v1/report/" + reportType,
// 	  type: "GET",
// 	  xhrFields: {
// 		responseType: 'blob' // This sets the response type to Blob
// 	  },
// 	  success: function(response) {
// 		var blob = new Blob([response], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }); // Create a Blob from the response
//  // Create a Blob from the response
// 		var link = document.createElement('a'); // Create a link element
// 		link.href = window.URL.createObjectURL(blob); // Set the href to the URL of the Blob
// 		link.download = reportType + '.xlsx';
		
// 		link.type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';

// 		link.click(); // Trigger the click event to start the download
// 		alert("Report generated successfully!");
// 	  },
// 	  error: function(jqXHR, textStatus, errorThrown) {
// 		console.log("Error generating report:", textStatus, errorThrown);
// 		console.log(textStatus, errorThrown);
// 		alert("Error generating report!");
// 	  }
// 	});
//   }
  
function generateReport(reportType) {
	$.ajax({
	  url: "http://localhost:8080/api/v1/report/" + reportType,
	  type: "GET",
	  xhrFields: {
		responseType: 'blob' // This sets the response type to Blob
	  },
	  success: function(response) {
		var blob = new Blob([response], { type: 'application/octet-stream' }); // Create a Blob from the response
		var link = document.createElement('a'); // Create a link element
		link.href = window.URL.createObjectURL(blob); // Set the href to the URL of the Blob
		link.download = reportType + '.xlsx'; // Set the download attribute to the file name
		link.click(); // Trigger the click event to start the download
		alert("Report generated successfully!");
	  },
	  error: function(jqXHR, textStatus, errorThrown) {
		console.log("Error generating report:", textStatus, errorThrown);
		console.log(textStatus, errorThrown);
		alert("Error generating report!");
	  }
	});
  }
  
  
  
  
  
  

  //--------------------------------------------------------------------------//
 
  