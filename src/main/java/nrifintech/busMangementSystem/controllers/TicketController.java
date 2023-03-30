package nrifintech.busMangementSystem.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.payloads.TicketDto;
import nrifintech.busMangementSystem.payloads.TicketResponse;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@Autowired
	BusService busService;

	@Autowired
	RouteService routeService;

	@Autowired
	UserService userService;
	
	@Autowired
	RouteInfoService routeInfoService;

	@Autowired
	TicketRepo ticketRepo;
	
	@Autowired
	BusMapRepo busMapRepo;

	@PostMapping("/create")
	ResponseEntity<?> createTicket(@Valid @RequestBody Ticket ticket){
		 ticketService.createTicket(ticket);
		 ApiResponse response = new ApiResponse("Ticket booked successfully!", true);
		 return new ResponseEntity<>(response, HttpStatus.OK);
		 //return new ResponseEntity(new ApiResponse("Ticket booked successfully!", true), HttpStatus.OK);
	}
	@PostMapping("/cancel")
	ResponseEntity<?> cancleTicket(@Valid @RequestBody int ticket_id){
		ticketService.cancelTicket(ticket_id);
		ApiResponse response = new ApiResponse("Ticket cancelled successfully!", true);
		 return new ResponseEntity<>(response, HttpStatus.OK);
		//return new ResponseEntity(new ApiResponse("Ticket cancelled successfully!", true), HttpStatus.OK);
	}
	// @GetMapping("/getByUserEmail/{userEmail}")
	// ResponseEntity<?> getUserTickets(@PathVariable("userEmail") String userEmail){
	// 	System.out.println(71);
	// 	System.out.println( userEmail);
	// 	List<Ticket> tickets = ticketService.getAllTicketByPersonEmail(userEmail);
	// 	return  ResponseEntity.ok(tickets);
	// }

	@GetMapping("/getByUserEmail/{userEmail}")
	ResponseEntity<TicketResponse> getUserTickets(@PathVariable("userEmail") String userEmail ,
	@RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
	@RequestParam(value="pageSize",defaultValue = "4",required=false) Integer psize){
		System.out.println(71);
		System.out.println( userEmail);
		TicketResponse ticketResponse = ticketService.getAllTicketByPersonEmail(userEmail,pno,psize);
		return new ResponseEntity<>(ticketResponse,HttpStatus.OK);
	}
	
	@GetMapping("/get/{userId}/totalTickets")
	public ResponseEntity<Integer> myInteger(@PathVariable("userId") int userId) {
        return ResponseEntity.ok().body(this.ticketService.getTotalTicketsDoneByUser(userId));
    }
	@GetMapping("/get/{userId}")
	ResponseEntity<?> getUserTickets(@PathVariable("userId") int userId){
		List<Ticket> tickets = ticketService.getAllTicketByPersonId(userId);
		return  ResponseEntity.ok(tickets);
	}

	
}
