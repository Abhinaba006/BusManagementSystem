
var nrifintech = "NRI TRAVEL";
var booking = `My Bookings`;
var logout = "Logout";

document.querySelector(".nrifintech").innerHTML = nrifintech;
document.querySelector(".booking").innerHTML = booking;
document.querySelector(".logout").innerHTML = logout;

function openSidebar() {
    console.log("OPEN")
    document.getElementById("mySidebar").style.display = "block";
}

function closeSidebar() {
    document.getElementById("mySidebar").style.display = "none";
}


function firstdiv() {
    document.getElementById("firstdiv").style.display = "block";
    document.getElementById("seconddiv").style.display = "none";
    document.getElementById("thirddiv").style.display = "none";
    document.getElementById("fourthdiv").style.display = "none";
    document.getElementById("fifthdiv").style.display = "none";
    document.getElementById("sixthdiv").style.display = "none";
    document.getElementById("seventhdiv").style.display = "none";
    document.getElementById("text1").style.color = "red";
    document.getElementById("text2").style.color = "black";
    document.getElementById("text3").style.color = "black";
    document.getElementById("text4").style.color = "black";
    document.getElementById("text5").style.color = "black";
    document.getElementById("text6").style.color = "black";
    document.getElementById("text7").style.color = "black";
}

function seconddiv() {
    document.getElementById("firstdiv").style.display = "none";
    document.getElementById("seconddiv").style.display = "block";
    document.getElementById("thirddiv").style.display = "none";
    document.getElementById("fourthdiv").style.display = "none";
    document.getElementById("fifthdiv").style.display = "none";
    document.getElementById("sixthdiv").style.display = "none";
    document.getElementById("seventhdiv").style.display = "none";
    document.getElementById("text1").style.color = "black";
    document.getElementById("text2").style.color = "red";
    document.getElementById("text3").style.color = "black";
    document.getElementById("text4").style.color = "black";
    document.getElementById("text5").style.color = "black";
    document.getElementById("text6").style.color = "black";
    document.getElementById("text7").style.color = "black";
}

function thirddiv() {
    document.getElementById("firstdiv").style.display = "none";
    document.getElementById("seconddiv").style.display = "none";
    document.getElementById("thirddiv").style.display = "block";
    document.getElementById("fourthdiv").style.display = "none";
    document.getElementById("fifthdiv").style.display = "none";
    document.getElementById("sixthdiv").style.display = "none";
    document.getElementById("seventhdiv").style.display = "none";
    document.getElementById("text1").style.color = "black";
    document.getElementById("text2").style.color = "black";
    document.getElementById("text3").style.color = "red";
    document.getElementById("text4").style.color = "black";
    document.getElementById("text5").style.color = "black";
    document.getElementById("text6").style.color = "black";
    document.getElementById("text7").style.color = "black";
}

function fourthdiv() {
    document.getElementById("firstdiv").style.display = "none";
    document.getElementById("seconddiv").style.display = "none";
    document.getElementById("thirddiv").style.display = "none";
    document.getElementById("fourthdiv").style.display = "block";
    document.getElementById("fifthdiv").style.display = "none";
    document.getElementById("sixthdiv").style.display = "none";
    document.getElementById("seventhdiv").style.display = "none";
    document.getElementById("text1").style.color = "black";
    document.getElementById("text2").style.color = "black";
    document.getElementById("text3").style.color = "black";
    document.getElementById("text4").style.color = "red";
    document.getElementById("text5").style.color = "black";
    document.getElementById("text6").style.color = "black";
    document.getElementById("text7").style.color = "black";
}

function fifthdiv() {
    document.getElementById("firstdiv").style.display = "none";
    document.getElementById("seconddiv").style.display = "none";
    document.getElementById("thirddiv").style.display = "none";
    document.getElementById("fourthdiv").style.display = "none";
    document.getElementById("fifthdiv").style.display = "block";
    document.getElementById("sixthdiv").style.display = "none";
    document.getElementById("seventhdiv").style.display = "none";
    document.getElementById("text1").style.color = "black";
    document.getElementById("text2").style.color = "black";
    document.getElementById("text3").style.color = "black";
    document.getElementById("text4").style.color = "black";
    document.getElementById("text5").style.color = "red";
    document.getElementById("text6").style.color = "black";
    document.getElementById("text7").style.color = "black";
}

function sixthdiv() {
    document.getElementById("firstdiv").style.display = "none";
    document.getElementById("seconddiv").style.display = "none";
    document.getElementById("thirddiv").style.display = "none";
    document.getElementById("fourthdiv").style.display = "none";
    document.getElementById("fifthdiv").style.display = "none";
    document.getElementById("sixthdiv").style.display = "block";
    document.getElementById("seventhdiv").style.display = "none";
    document.getElementById("text1").style.color = "black";
    document.getElementById("text2").style.color = "black";
    document.getElementById("text3").style.color = "black";
    document.getElementById("text4").style.color = "black";
    document.getElementById("text5").style.color = "black";
    document.getElementById("text6").style.color = "red";
    document.getElementById("text7").style.color = "black";
}

function seventhdiv() {
    document.getElementById("firstdiv").style.display = "none";
    document.getElementById("seconddiv").style.display = "none";
    document.getElementById("thirddiv").style.display = "none";
    document.getElementById("fourthdiv").style.display = "none";
    document.getElementById("fifthdiv").style.display = "none";
    document.getElementById("sixthdiv").style.display = "none";
    document.getElementById("seventhdiv").style.display = "block";
    document.getElementById("text1").style.color = "black";
    document.getElementById("text2").style.color = "black";
    document.getElementById("text3").style.color = "black";
    document.getElementById("text4").style.color = "black";
    document.getElementById("text5").style.color = "black";
    document.getElementById("text6").style.color = "black";
    document.getElementById("text7").style.color = "red";
}



$(document).ready(function () {
    var fieldIndex = 1;
    $(".add-field").on("click", function (event) {
        event.preventDefault();
        // Create a new row with two text fields
        var newRow = "<tr><td><input type='text' name='name'></td><td><input type='text' name='email'></td></tr>";

        // Append the new row to the table body
        $(".container").append(newRow);
    });
});

function add_route_field(event) {
    event.preventDefault();
    const select_div = document.getElementById("add_field");
    const len = select_div.childElementCount;
    console.log(len);

    const newDiv = document.createElement("div");
    newDiv.className = "select_content";
    newDiv.innerHTML = `
    <select class="select_class" id="select_dest_${len + 1}" onclick="add_Destination_toselect(event, ${len})">
        <option>Add destination</option>
    </select>
    <input class="select_time" id="select_time_${len + 1}" placeholder="HH:MM (24hr-format)">
`;
    select_div.appendChild(newDiv);

}
function add_route_field_edit(event) {
    event.preventDefault();
    const select_div = document.getElementById("add_field_edit");
    const len = select_div.childElementCount;
    console.log(len);
    select_div.innerHTML += `
    <div class = "select_content">
        <select class = "select_class" id = "select_dest_edit_${len + 1}" onclick = "add_Destination_toselect(event,${len})">
            <option>Add destination</option>
        </select>
        <input class = "select_time" id = "select_time_edit_${len + 1}" placeholder = "HH:MM(24hr-format)"></select>
    </div> 
    `
}
function addEmployee(event) {
    event.preventDefault();
    const name = $("#add-employee-name").val();
    const email = $("#add-employee-email").val();
    const password = $("#add-employee-password").val();
    const employeeId = $("#add-employee-id").val();

    if (name == "" || email == "" || password == "" || employeeId == "") {
        return createAlert("Please provide valid credentials for a employee", "info");//alert("Please provide valide credentials for a employee");
    }
    $.ajax({
        url: "http://localhost:8080/api/v1/user/create",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify({
            name: name,
            email: email,
            password: password,
            employeeId: employeeId
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            createAlert("User added successfully", "success");

            //clear all text fields
            $("#add-employee-name").val("");
            $("#add-employee-email").val("");
            $("#add-employee-password").val("");
            $("#add-employee-id").val("");
            //but add email in search field
            $("#search-email").val(email);




        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });


}

function getRoutesAdmin(event) {
    event.preventDefault();
    document.querySelector(".admin_routes").innerHTML = "";
    console.log("Hitting");
    const source = $("#admin_from").val();
    const dest = $("#admin_to").val();
    const today = new Date();
    const yyyy = today.getFullYear();
    let mm = today.getMonth() + 1; // Months start at 0!
    let dd = today.getDate();

    if (dd < 10) dd = '0' + dd;
    if (mm < 10) mm = '0' + mm;

    const formattedDate = dd + ':' + mm + ':' + yyyy;

    const result = [];
    $.ajax({
        url: "http://localhost:8080/api/v1/route/getBySrcDest/" + source + "/" + dest,
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
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
                obj["routeId"] = route_id;
                $.ajax({
                    url: "http://localhost:8080/api/v1/route/getDestinations/" + route_id,
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
                            url: "http://localhost:8080/api/v1/route/getReport/" + route_id + "/" + formattedDate,
                            type: "GET",
                            headers: {
                                "Authorization": getTokenCookie(),
                                "Content-Type": "application/json"
                            },
                            success: function (data) {
                                var diff = data.total_seats - data.total_bookings;
                                obj["seatsLeft"] = Math.max(0, diff);
                                $.ajax({
                                    url: "http://localhost:8080/api/v1/route/getBus/" + route_id,
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
                                 <div class = "index_route_item" style = "width:80%">
                                 <div class = "index_source">
                                        <div class = "index_heading">Route Id</div>
                                        <div class = "index_bus_number">${obj.routeId}</div>
                                    </div>
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
                                        <div class = "index_book" route_id = ${obj.route_id} bus_id =  ${obj.busId} user_id = ${obj.userId} date =  ${formattedDate} onclick=" deleteRoute(event,${obj.route_id})" style = "background-color:orangered;">Delete</div>
                                    </div>
                                </div>
                                                `;
                                        const parentDiv = document.querySelector(".admin_routes");
                                        parentDiv.innerHTML += routeHTML;
                                    },
                                    error: function () {
                                        return createAlert("Server error! Please try again!", "failure");
                                        //return alert("Server error! Please try again!")
                                    }
                                });
                            },
                            error: function () {
                                return createAlert("Server error! Please try again!", "failure");
                                //return alert("Server error! Please try again!");
                            }
                        });
                    },
                    error: function () {
                        return createAlert("Server error! Please try again!", "failure");
                        //return alert("Server error! Please try again!");
                    }
                });




            }
        },
        error: function () {
            createAlert("Server error! Please try again!", "failure");
            //alert("Server error! Please try again later.")
        }
    });

    console.log(result.length);


}

function deleteRoute(event, routeId) {
    console.log(routeId);
    console.log("Hitting");
    event.preventDefault();
    const id = $("#bus-id-o").val();
    $.ajax({
        url: "http://localhost:8080/api/v1/route/delete/" + routeId,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "DELETE",
        success: function (result) {
            console.log(result);
            createAlert("Bus deleted successfully", "success");
            //alert("Bus deleted successfully!")
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again!", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });

}
function cancelTicket(event) {
    const ticket_id = $(event.target).attr("ticket_id");
    // console.log(getTokenCookie())
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/ticket/cancel",
        data: JSON.stringify(ticket_id),
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (response) {
            console.log("Ticket cancelled successfully");

            createAlert("Ticket cancelled successfully", "success");

            //alert("Ticket cancelled sucessfully!");
            // Handle success response here
        },
        error: function (xhr, status, error) {
            console.log("Error cancelling ticket: " + error);
            createAlert("Server error! Please try again!", "failure");
            //alert("Server error! Please try again!");
            // Handle error response here
        }
    });
}
function searchTickets(event,optionalValue=0) {
    event.preventDefault();
    document.querySelector(".admin-ticket-content").innerHTML = "";
    const html = ``;
    const email = document.getElementById("search-tickets-admin").value
    const id = 1; //To be changed the the id that is to be fetched by the email
    const status=document.getElementById('status').value
    console.log(email)
    $.ajax({
        url: "http://localhost:8080/api/v1/ticket/getByUserEmail/"+email+ "?pageNumber="+optionalValue+"&status="+status,
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (d) {
           
            console.log(status)
            // "id": 28,
            // "routeId": 5,
            // "busId": 4,
            // "userId": 1,
            // "status": "CANCELLED",
            // "date": "09:03:2023"
            var data=d.content;
            var total_pages=d.totalPages;
            var current_page=d.pageNumber;
            // var page_number=d.pageNumber+1;
            const parentDiv = document.querySelector(".admin-ticket-pagination");
            parentDiv.innerHTML="";
            console.log(474, current_page);
            if(d.firstpage==false)
            {
                
                const prevdiv=`<div class="pagination-divs-nextprev" onclick="searchTickets(event, ${current_page}-1 )"> Prev </div>`;
                parentDiv.innerHTML +=prevdiv;
            }
            for(var i=current_page+1;i <= current_page+3 && i <= total_pages;i++)
            {
                console.log('asdasdwdasdfsyfth')

                if(i==current_page+1)
                {
                    const pagediv=`<div class="pagination-divs" style="border-bottom: 2px solid blue;" onclick="searchTickets(event, ${i}-1 )"> ${i} </div>`;
                    parentDiv.innerHTML +=pagediv;
                }
                else{
                    const pagediv=`<div class="pagination-divs" onclick="searchTickets(event, ${i}-1 )"> ${i} </div>`;
                    parentDiv.innerHTML +=pagediv;
                }
                
            }
            if(d.lastPage==false && d.pageNumber+2 <d.totalPages )
            {
                const nextPagetoGo=current_page+2;
                const nextdiv=`<div class="pagination-divs-nextprev" onclick="searchTickets(event, ${nextPagetoGo} )"> Next </div>`;
                parentDiv.innerHTML +=nextdiv;
            }
    

            document.getElementById("admin_ticket_count").innerHTML = "Total tickets found : " + d.totalElements;
            for (var i = 0; i < data.length; i++) {
                const route_id = data[i].routeId;
                const ticket_id = data[i].id;
                const formattedDate = data[i].date;
                const status = data[i].status;
                const obj = {
                    busId: "",
                    busName: "",
                    busNumber: "",
                    destination_name: "",
                    destination_time: "",
                    route_id: "",
                    source_name: "",
                    source_time: "",
                    userId: 1, //To be changed
                    date: formattedDate
                };
                obj["route_id"] = route_id;
                console.log("thus",obj.route_id);
                $.ajax({
                    url: "http://localhost:8080/api/v1/route/getDestinations/" + route_id,
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
                            url: "http://localhost:8080/api/v1/route/getReport/" + route_id + "/" + formattedDate,
                            type: "GET",
                            headers: {
                                "Authorization": getTokenCookie(),
                                "Content-Type": "application/json"
                            },
                            success: function (data) {
                                var diff = data.total_seats - data.total_bookings;
                                obj["seatsLeft"] = Math.max(0, diff);
                                $.ajax({
                                    url: "http://localhost:8080/api/v1/route/getBus/" + route_id,
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
                                        obj["userId"] = 1;
                                        console.log(obj);
                                        let color;
                                        if (status === "CONFIRMED") {
                                            color = "green";
                                        }
                                        else if (status === "CANCELLED") {
                                            color = "red";

                                        }
                                        else if (status === "WAITING") {
                                            color = "orange";
                                        }
                                        else if (status === "AVAILED") {
                                            color = "grey";
                                        }

                                        if (status === "CONFIRMED" || status === "WAITING") {
                                            const routeHTML = `
                                        
                                            <div class = "admin-ticket-values">
                                                <div class = "admin-ticket-user-value">${obj.date}</div>
                                                <div class = "admin-ticket-user-value">${obj.source_name}</div>
                                                <div class = "admin-ticket-user-value">${obj.destination_name}</div>
                                                <div class = "admin-ticket-user-value">${obj.busNumber}</div>
                                                <div class = "admin-ticket-user-value" style="color:${color}">${status}</div>
                                                <div class = "admin-ticket-user-value" style = "color:red;cursor: pointer;" onclick="cancelTicket(event)"  ticket_id = ${ticket_id}>Cancel</div>
                                            </div> 
                                            `;

                                            const parentDiv = document.querySelector(".admin-ticket-content");
                                            parentDiv.innerHTML += routeHTML;
                                        }
                                        else {
                                            const routeHTML = `
                                        
                                            <div class = "admin-ticket-values">
                                                <div class = "admin-ticket-user-value">${obj.date}</div>
                                                <div class = "admin-ticket-user-value">${obj.source_name}</div>
                                                <div class = "admin-ticket-user-value">${obj.destination_name}</div>
                                                <div class = "admin-ticket-user-value">${obj.busNumber}</div>
                                                <div class = "admin-ticket-user-value"  style="color:${color}">${status}</div>
                                                <div class = "admin-ticket-user-value" style = "color:black;" ticket_id = ${ticket_id}>-</div>
                                            </div> 
                                            `;

                                            const parentDiv = document.querySelector(".admin-ticket-content");
                                            parentDiv.innerHTML += routeHTML;
                                        }
                                    },
                                    error: function () {
                                        return createAlert("Server error! Please try again!", "failure");
                                        //return alert("Server error! Please try again!")
                                    }
                                });

                            },
                            error: function () {
                                return createAlert("Server error! Please try again!", "failure");
                                //return alert("Server error! Please try again!");
                            }
                        });

                    },
                    error: function () {
                        return createAlert("Server error! Please try again!", "failure");
                        //return alert("Server error! Please try again!");
                    }
                });




            }
        },
        error: function (e) {
            console.log(e);
            const parentDiv = document.querySelector(".admin-ticket-pagination");
            console.log(parentDiv);
            parentDiv.innerHTML="";
            return createAlert("Something went wrong. Please try again later!", "failure");
            //return alert("Something went wrong. Please try again later!");
        }
    });
}


function updateEmployee(event) {
    event.preventDefault();
    const name = $("#employee-name").val();
    const email = $("#employee-email").val();
    const empId = $("#employee-id").val();
    const password = $("#employee-password").val();
    const id = $("#emp-inp-id").val();
    console.log(name, email, empId, password, id);
    if (name == "" || email == "" || empId == "") {
        return createAlert("Please provide valid credentials for a employee!", "info");//alert("Please provide valide credentials for a employee");
    }
    const newObj = {
        name: name,
        email: email,
        employeeId: empId
    }
    if (password != "") {
        newObj["password"] = password;
    }
    $.ajax({
        url: "http://localhost:8080/api/v1/user/update/" + id,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify(
            newObj
        ),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            document.querySelector(".search-overlay").style.display = "none";
            createAlert("User updated successfully!", "success");
            


        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });


}
function displayDestinations3(event) {
    event.preventDefault();
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data);
            var selectElement = document.getElementById('admin_from');

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
            return new createAlert("Server error! Please try again", "failure");//alert("Server error! Please try again!")
        }
    });
}
function displayDestinations4(event) {
    event.preventDefault();
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data);
            var selectElement = document.getElementById('admin_to');

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
            return new createAlert("Server error! Please try again later!", "failure");//alert("Server error! Please try again!")
        }
    });
}
function deleteEmployee(event) {
    console.log("Hitting");
    event.preventDefault();

    const id = $("#emp-inp-id").val();
    console.log(id);
    $.ajax({
        url: "http://localhost:8080/api/v1/user/delete/" + id,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "DELETE",
        success: function (result) {
            console.log(result);
            //alert("Employee details deleted successfully!")
            document.querySelector(".search-overlay").style.display = "none";
            createAlert("Employee details deleted successfully!", "success");

        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong. Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}
function on() {
    //Getall user
    $.ajax({
        url: "http://localhost:8080/api/v1/user/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data)
            const email = $("#search-email").val();
            if (email == "") {
                return createAlert("Please provide an email", "info");//alert("Please provide an email");
            }
            var found = 0;
            let user = null;
            for (var i = 0; i < data.length; i++) {
                if ((data[i].email).toLowerCase() == email.toLowerCase()) {
                    found = 1;
                    user = data[i];
                    document.querySelector(".search-overlay").style.display = "block";
                }
            }
            if (found == 0) {
                return createAlert("No employee found!", "info");//alert("No employee found!")
            }
            $("#employee-name").val(user.name);
            $("#employee-email").val(user.email);
            $("#employee-id").val(user.employeeId);
            $("#emp-inp-id").val(user.id);
            $("#employee-password").val("");
        },
        error: function () {
            createAlert("Server error!", "failure");
        }
    });


}
function addDestination(event) {
    event.preventDefault();
    const name = $("#dest-name").val();
    const latitude = $("#dest-latitude-input").val();
    const longitude = $("#dest-longitude-input").val();
    console.log(name, latitude, longitude);
    if (name == "" || latitude == "" || longitude == "") {
        return createAlert("Please provide all details!", "info");//alert("Please provide all details!");
    }
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/create",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify({
            name: name,
            latitude: latitude,
            longitude: longitude
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            createAlert("Destination added successfully!", "success");
            //alert("Destination added successfully!")

            //clear text fields
            $("#dest-name").val("");
            $("#dest-latitude-input").val("");
            $("#dest-longitude-input").val("");

            //add destinatio name in search bar
            $("#dest-search-name").val(name);
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}

function off() {
    document.querySelector(".search-overlay").style.display = "none";
}

function on2(event) {
    event.preventDefault();
    const busNumber = $("#bus-search-name").val();
    console.log(busNumber);
    $.ajax({
        url: "http://localhost:8080/api/v1/bus/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data[0]);
            let bus = null;
            for (var i = 0; i < data.length; i++) {
                if ((data[i].bus_number).toLowerCase() == busNumber.toLowerCase()) {
                    bus = data[i];
                }
            }
            if (bus == null) {
                return createAlert("No bus found!", "info");
                //return alert("No bus found!");
            }
            console.log(bus);
            document.querySelector(".bus-overlay").style.display = "block";
            $("#bus-number-o").val(bus.bus_number);
            $("#bus-name-o").val(bus.name);
            $("#bus-seats-o").val(bus.totalNumberOfseats);
            $("#bus-id-o").val(bus.id);

        }
    });

}

function off2() {
    document.querySelector(".bus-overlay").style.display = "none";
}

function on3(event) {
    event.preventDefault();
    const destName = $("#dest-search-name").val();
    console.log(destName);
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data[0]);
            let dest = null;
            for (var i = 0; i < data.length; i++) {
                if ((data[i].name).toLowerCase() == destName.toLowerCase()) {
                    dest = data[i];
                }
            }
            if (dest == null) {
                return createAlert("No Destinations found!", "info");
                //return alert("No destinations found!");
            }
            document.querySelector(".destination-overlay").style.display = "block";
            $("#dest-edit-name").val(dest.name);
            $("#dest-edit-longitude-input").val(dest.longitude)
            $("#dest-edit-latitude-input").val(dest.latitude);
            $("#dest-edit-id").val(dest.id);
        }
    });

}

function updateDestination(event) {
    console.log("Hitting");
    event.preventDefault();
    const name = $("#dest-edit-name").val();
    const longitude = $("#dest-edit-longitude-input").val();
    const latitude = $("#dest-edit-latitude-input").val();
    const id = $("#dest-edit-id").val();
    console.log(name, latitude, longitude, id);
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/update/" + id,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify({
            name: name,
            latitude: latitude,
            longitude: longitude
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            document.querySelector(".destination-overlay").style.display = "none";
            createAlert("Destination updated successfully!", "success");
            //alert("Destination updated successfully!")
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}
function deleteDestination(event) {
    console.log("Hitting");
    event.preventDefault();
    const name = $("#dest-edit-name").val();
    const longitude = $("#dest-edit-longitude-input").val();
    const latitude = $("#dest-edit-latitude-input").val();
    const id = $("#dest-edit-id").val();
    console.log(name, latitude, longitude, id);
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/delete/" + id,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "DELETE",
        success: function (result) {
            console.log(result);
            document.querySelector(".destination-overlay").style.display = "none";
            createAlert("Destination deleted successfully!", "success");
            //alert("Destination deleted successfully!")
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again!", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}
function updateBus(event) {
    event.preventDefault();
    const bus_number = $("#bus-number-o").val();
    const bus_name = $("#bus-name-o").val();
    const total_seats = $("#bus-seats-o").val();
    const id = $("#bus-id-o").val();
    console.log(bus_name, bus_number, total_seats, id);
    $.ajax({
        url: "http://localhost:8080/api/v1/bus/update/" + id,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify({
            name: bus_name,
            bus_number: bus_number,
            totalNumberOfseats: total_seats
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result);

            document.querySelector(".bus-overlay").style.display = "none";
            createAlert("Bus updated successfully!", "success");
            //alert("Bus updated successfully!")
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}
function deleteBus(event) {
    console.log("Hitting");
    event.preventDefault();
    const id = $("#bus-id-o").val();
    $.ajax({
        url: "http://localhost:8080/api/v1/bus/delete/" + id,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "DELETE",
        success: function (result) {
            console.log(result);
            document.querySelector(".bus-overlay").style.display = "none";
            createAlert("Bus deleted successfully!", "success");
            //alert("Bus deleted successfully!")
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}
function addBus(event) {
    event.preventDefault();
    const busNumber = $("#bus-number").val();
    const seats = $("#bus-seats").val();
    const busName = $("#bus-name").val();
    console.log(busNumber, seats, busName);
    if (busNumber == "" || seats == "" || busName == "") {
        return createAlert("Please provide all details", "info");//alert("Please provide all fields");
    }
    $.ajax({
        url: "http://localhost:8080/api/v1/bus/create",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify({
            name: busName,
            bus_number: busNumber,
            totalNumberOfseats: seats
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            // alert("Bus added successfully!")
            createAlert("Bus added successfully!", "success");

            //clear all the text fields
            $("#bus-number").val("");
            $("#bus-seats").val("");
            $("#bus-name").val("");

            //add bus number in the search bar
            $("#bus-search-name").val(busNumber);

        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });

}
function off3() {
    document.querySelector(".destination-overlay").style.display = "none";
}

//For display of the destinations and bus dynamically
function displayBusID(event) {
    event.preventDefault();
    $.ajax({
        url: "http://localhost:8080/api/v1/bus/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            var selectElement = document.getElementById('select_bus');

            // Add options from the data array
            data.forEach(function (item) {
                var optionElement = document.createElement('option');
                optionElement.value = item.id;
                optionElement.textContent = item.bus_number;
                var flag = 1;
                for (var i = 0; i < selectElement.options.length; i++) {
                    if (item.id == selectElement.options[i].value) flag = 0;
                }
                if (flag || selectElement.options.length == 1)
                    selectElement.appendChild(optionElement);
            });
        },
        error: function () {
            return new createAlert("Server error! Please try again", "failure");//alert("Server error! Please try again!")
        }
    });
}

//For display of the destinations dynamically in search a route - from
// function displayDestinationsfrom(event){
//     event.preventDefault();
//     $.get("http://localhost:8080/api/v1/destination/get",function(data){
//     var selectElement = document.getElementById('select-destination-id-from');

//     // Add options from the data array
//     data.forEach(function(item) {
//         var optionElement = document.createElement('option');
//         optionElement.value = item.id;
//         optionElement.textContent = item.name;
//         var flag = 1;
//         for(var i = 0; i < selectElement.options.length;i++){
//             if(item.id == selectElement.options[i].value) flag = 0;
//         }
//         if(flag || selectElement.options.length == 1)
//         selectElement.appendChild(optionElement);
//     });
// }).fail(function(){
//     return new alert("Server error! Please try again!")
// })
// }

// //For display of the destinations dynamically in search a route - to
// function displayDestinationsto(event){
//     event.preventDefault();
//     $.get("http://localhost:8080/api/v1/destination/get",function(data){
//     var selectElement = document.getElementById('select-destination-id-to');

//     // Add options from the data array
//     data.forEach(function(item) {
//         var optionElement = document.createElement('option');
//         optionElement.value = item.id;
//         optionElement.textContent = item.name;
//         var flag = 1;
//         for(var i = 0; i < selectElement.options.length;i++){
//             if(item.id == selectElement.options[i].value) flag = 0;
//         }
//         if(flag || selectElement.options.length == 1)
//         selectElement.appendChild(optionElement);
//     });
// }).fail(function(){
//     return new alert("Server error! Please try again!")
// })
// }

function addRoute(event) {
    event.preventDefault();
    var table = document.getElementById("add_field");
    const data = [];
    for (var i = 0; i < table.childElementCount; i++) {
        const destId = $("#select_dest_" + (i + 1)).val();
        const time = $("#select_time_" + (i + 1)).val();
        if (destId == "" && time == "") continue;
        else if (destId == "" || time == "") return createAlert("Please provide valid destination/time combination!", "info");//alert("Please provide valid destination/time combination");
        data.push(destId + "_" + i + "_" + time);
    }
    console.log(data);
    if (data.length <= 1) {
        return createAlert("Total number of destinations should be greater than 1!", "info");//alert("Total number of destinations should be greater than 1");
    }
    const busId = $("#select_bus").val();
    if (busId == "" || busId == undefined) return createAlert("Please provide a bus id for this route!", "info");//alert("Please provide a bus id for this route!");
    console.log("data: ", data);
    $.ajax({
        url: "http://localhost:8080/api/v1/route/create/" + busId,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            createAlert("Route added successfully!", "success");
            //alert("Route added successfully!")
            //refresh the input fields and put the start and end destination name in the search bar.
            //refresh the page if any issue is resolved.
            document.getElementsByClassName("route_add_div").display="none";
            
            $("#select_bus").val("");
            

            




        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });

}
function add_field() {

    $.ajax({
        url: "http://localhost:8080/api/v1/destination/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data);
            data.unshift({
                name: "Select destination",
                id: -1
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
            data.forEach(function (item) {
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

        }
    });
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
function add_Destination_toselect(event, len) {
    event.preventDefault();
    $.ajax({
        url: "http://localhost:8080/api/v1/destination/get",
        type: "GET",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            console.log(data);
            var selectElement = document.getElementById('select_dest_' + (len + 1));

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
            return new createAlert("Server error! Please try again", "failure");//alert("Server error! Please try again!")
        }
    });
}
const save_destination = (evt) => {
    evt.preventDefault();
    const name = $("#dest-edit-name").val();
    const latitude = $("#dest-edit-latitude-input").val();
    const longitude = $("#dest-edit-longitude-input").val();

    $.ajax({
        url: "http://localhost:8080/api/v1/destination/update",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "POST",
        data: JSON.stringify({
            name: name,
            latitude: latitude,
            longitude: longitude
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            createAlert("Destination updated successfully!", "success");
            //alert("Destination updated successfully!")
        },
        error: function (xhr, status, error) {
            console.log(error);
            createAlert("Oops something went wrong! Please try again", "failure");
            //alert("Oops something went wrong! Please try again")
        }
    });
}

document.querySelector('.save-button').addEventListener('click', save_destination);


const delete_destination = (evt) => {
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
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        type: "GET",
        xhrFields: {
            responseType: 'blob' // This sets the response type to Blob
        },
        success: function (response) {
            var blob = new Blob([response], { type: 'application/octet-stream' }); // Create a Blob from the response
            var link = document.createElement('a'); // Create a link element
            link.href = window.URL.createObjectURL(blob); // Set the href to the URL of the Blob
            link.download = reportType + '.xlsx'; // Set the download attribute to the file name
            link.click(); // Trigger the click event to start the download
            createAlert("Report generated successfully!", "success");
            //alert("Report generated successfully!");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error generating report:", textStatus, errorThrown);
            console.log(textStatus, errorThrown);
            createAlert("Error generating report!", "failure");
            //alert("Error generating report!");
        }
    });
}

const resolveButton = document.querySelector('.resolve-button');

resolveButton.addEventListener('click', () => {
    console.log("resolve button clicked");
    resolveButton.disabled = true;
    resolveButton.textContent = 'Resolved';
});

let fetchUser = (id) => {

}

function getUnResolvedIssues() {
    const status = document.getElementById("issueStatus").value;
    const email = document.getElementById("search-issues-admin").value;
    const url = email===""?"http://localhost:8080/api/v1/issues/"+status:"http://localhost:8080/api/v1/issues/"+email+"/"+status;
    console.log(url)
    // Change it the user that is calling
    $.ajax({
        url,
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            // ... the rest of the function code remains the same
            //console.log(data);

    document.getElementById("admin_issue_count").innerHTML = "Total Results: " + data.length;
            
            const parentDiv = document.getElementById("issue-container");
            parentDiv.innerHTML = "";

            for (var i = 0; i < data.length; i++) {
                const id = data[i].id;
                const is_resolved = data[i].is_resolved;
                const issue = data[i].issue;
                const user_id = parseInt(data[i].user_id);
                const date = data[i].date;
                const obj = {
                    id: "",
                    is_resolved: "",
                    issue: "",
                    user_id: "",
                    date: "",
                };
                obj["id"] = id;
                obj["is_resolved"] = is_resolved;
                obj["issue"] = issue;
                obj["user_id"] = user_id;
                obj["date"] = moment(date).format("DD MMMM YYYY");

                $.ajax({
                    url: "http://localhost:8080/api/v1/user/get/" + user_id,
                    headers: {
                        "Authorization": getTokenCookie(),
                        "Content-Type": "application/json"
                    },
                    success: function (data2) {
                        console.log(data2)
                        obj["username"] = data2.name;
                        const issueHTML = `
                                <div class = "issue-div">
                                    <div class = "issue-heading">Issue ${obj.id}</div>
                                    <div class = "issue-user">${data2.email} 
                                    <p class = "issue-date">Created at: ${obj.date}</p>
                                    ${status === 'resolved' ? `<div class="issue-date" data-id="${obj.id}">Resolved at: ${obj.ResolvedDate}</div>` : ''}

                                    </div>
                                    <div class = "issue-text">${obj.issue}
                                    </div>
                                    <br/>
                                    ${status === 'unresolved' ? '<div class="issue resolve resolve-button" id=issue'+obj.id+'>Resolve</div>' : ''}
                                </div>
                                `;
                        // const parentDiv = document.querySelector(".issue-manage");
                        parentDiv.innerHTML += issueHTML;
                        // Add a click event handler for the dynamically created buttons
                        $('.resolve-button').click(function () {
                            // Get the ID value from the data-id attribute
                            var id = parseInt(this.id.substring(5));
                            // Make an AJAX call to post the data to the database
                            console.log(parseInt(this.id.substring(5)))

                            $.ajax({
                                url: 'http://localhost:8080/api/v1/issues/' + id + '/resolve',
                                headers: {
                                    "Authorization": getTokenCookie(),
                                    "Content-Type": "application/json"
                                },
                                type: "POST",
                                success: function (response) {
                                    // Do something if the POST request is successful
                                    console.log('Data posted to database');
                                    createAlert("Issue Number"+id+" resolved","success");
                                    //refresh the page if any issue is resolved.
                                    var link = document.getElementById('text6');
                                    link.click();

                                },
                                error: function (error) {
                                    // Do something if the POST request fails
                                    console.log(error);
                                }
                            });


                        });

                    }
                })

            }
        }
        ,
        error: function () {
            return createAlert("Something went wrong. Please try again later!", "failure");//alert("Something went wrong. Please try again later!");
        }
    });

}



/*

function getUnResolvedIssues() {
    // Change it the user that is calling
    $.get("http://localhost:8080/api/v1/issues/unresolved", function(data) {	
    	
        
    }).fail(function() {
        return alert("Something went wrong. Please try again later!");
    });
}

*/
//--------------------------------------------------------------------------//

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
function logOut() {
    deleteAllCookies();
    window.location = '/client/admin_login.html';
}



$(document).ready(function validateToken() {
    console.log("loading")
});


function createAlert(message, type) {
    var alertContainer = document.getElementById("alert-container");
    var alertBox = document.createElement("div");
    var closeButton = document.createElement("span");

    alertBox.textContent = message;
    closeButton.textContent = "×";
    closeButton.className = "closebtn";
    closeButton.onclick = function () {
        alertContainer.removeChild(alertBox);
        window.reload();
    };

    alertBox.className = "alert " + type;
    alertBox.appendChild(closeButton);
    alertContainer.appendChild(alertBox);

}

function searchIssues(event) {
    const issueSeachField = document.getElementById("search-issues-admin").value;
    //call getUserUnResolvedIssues function
    const status = document.getElementById("issueStatus").value
    console.log(status)
    if (issueSeachField != "")
        getUnResolvedIssues(status, issueSeachField);
    else
        getUnResolvedIssues(status);
}

function getUserUnResolvedIssues(email) {

    $.ajax({
        url: "http://localhost:8080/api/v1/issues/" + email + "/unresolved",
        headers: {
            "Authorization": getTokenCookie(),
            "Content-Type": "application/json"
        },
        success: function (data) {
            // ... the rest of the function code remains the same
            //console.log(data);
            const parentDiv = document.querySelector(".issue-manage-content");
            parentDiv.innerHTML = "";

            document.getElementById("admin_issue_count").innerHTML = "Total Results: " + data.length;


            for (var i = 0; i < data.length; i++) {
                const id = data[i].id;
                const is_resolved = data[i].is_resolved;
                const issue = data[i].issue;
                const user_id = parseInt(data[i].user_id);
                const date = data[i].date;
                const obj = {
                    id: "",
                    is_resolved: "",
                    issue: "",
                    user_id: "",
                    date: "",
                };
                obj["id"] = id;
                obj["is_resolved"] = is_resolved;
                obj["issue"] = issue;
                obj["user_id"] = user_id;
                obj["date"] = date;

                $.ajax({
                    url: "http://localhost:8080/api/v1/user/get/" + user_id,
                    headers: {
                        "Authorization": getTokenCookie(),
                        "Content-Type": "application/json"
                    },
                    success: function (data2) {


                        console.log(data2)
                        obj["username"] = data2.name;
                        const issueHTML = `
                                  <div class = "issue-div">
                                      <div class = "issue-heading">Issue ${obj.id}</div>
                                      <div class = "issue-user">${data2.email} <p class = "issue-date">Created at: ${obj.date}</p></div>
                                      <div class = "issue-text">${obj.issue}
                                      </div>
                                      <br/>
                                      <div class="issue resolve resolve-button" data-id="${obj.id}">Resolve</div>
                                  </div>
                                  `;
                        const parentDiv = document.querySelector(".issue-manage-content");
                        parentDiv.innerHTML += issueHTML;
                        // Add a click event handler for the dynamically created buttons
                        $('.resolve-button').click(function () {
                            // Get the ID value from the data-id attribute
                            var id = $(this).data('id');
                            // Make an AJAX call to post the data to the database
                            console.log("clicked")

                            $.ajax({
                                url: 'http://localhost:8080/api/v1/issues/' + id + '/resolve',
                                headers: {
                                    "Authorization": getTokenCookie(),
                                    "Content-Type": "application/json"
                                },
                                type: "POST",
                                success: function (response) {
                                    // Do something if the POST request is successful
                                    console.log('Data posted to database');
                                    createAlert("Issue Number " + id + " resolved", "success");
                                    //refresh the page if any issue is resolved.
                                    var link = document.getElementById('text6');
                                    link.click();

                                },
                                error: function (error) {
                                    // Do something if the POST request fails
                                    console.log(error);
                                }
                            });


                        });

                    }
                })

            }
        }
        ,
        error: function (xhr, status, error) {
            console.log(JSON.parse(xhr.responseText).message);
            if (JSON.parse(xhr.responseText).message === "user does not exists")
                return createAlert("user with this email does not exists!", "info");
            else
                return createAlert("Something went wrong. Please try again later!", "failure");//alert("Something went wrong. Please try again later!");
        }
    });
}














