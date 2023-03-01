package nrifintech.busMangementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.payloads.*;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@Autowired
	BusService busService;

	@Autowired
	RouteService routeService;

	@Autowired
	UserService userService;

	// get
	@GetMapping("/ticket/get")
	public ResponseEntity<List<Ticket>> getAllTicket() {
		System.out.println("tkt get");
		return ResponseEntity.ok(this.ticketService.getTicket());
	}

	@GetMapping("/ticket/get/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") int ticketId) {
		return ResponseEntity.ok(this.ticketService.getTicket(ticketId));
	}
	
	@GetMapping("/ticket/getByUserId/{id}")
	public ResponseEntity<List<Ticket>> getTicketByUserId(@PathVariable("id") int userId) {
		System.out.println(userId);
		return ResponseEntity.ok(this.ticketService.getTicketByUserId(userId));
	}

	// post
	@PostMapping("/ticket/create")
	public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketDto ticketDto) {

		
		Ticket createdTicket = ticketService.createTicket(ticketDto);
		// Return the created ticket
		return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
	}

	// update
	@PostMapping("/ticket/update/{ticketId}")
	public ResponseEntity<Ticket> updateTicket(@Valid @RequestBody Ticket ticket,
			@PathVariable("ticketId") int ticketId) {
		Ticket updatedTicket = ticketService.updateTicket(ticket, ticketId);
		return ResponseEntity.ok(updatedTicket);
	}

	// delete
	@DeleteMapping("/ticket/delete/{ticketId}")
	public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") int ticketId) {
		ticketService.deleteTicket(ticketId);
		return new ResponseEntity(new ApiResponse("ticket deleted", true), HttpStatus.OK);
	}

	@PostMapping("/ticket/cancel/{ticketId}")
	public ResponseEntity<Ticket> cancelTicket(@PathVariable("ticketId") int ticketId) {
		Ticket ticket = ticketService.getTicket(ticketId);
		ticket.setStatus("cancelled");
		Bus bus = ticket.getBus();
		if (bus.getNumberOfSeats() == 0) {
			Ticket waitingTicket = ticketService.getMostRecentWaitingTicket(bus.getId());
			if (waitingTicket != null) {
				waitingTicket.setStatus("confirmed");
			}
		} else
			bus.setNumberOfSeats(bus.getNumberOfSeats() + 1);
		return ResponseEntity.ok(ticket);
	}
}
