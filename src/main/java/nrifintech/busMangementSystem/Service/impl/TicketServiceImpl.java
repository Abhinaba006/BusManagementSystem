package nrifintech.busMangementSystem.Service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.payloads.TicketDto;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	@Autowired
	private BusRepo busRepo;
	@Autowired
	private RouteRepo routeRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public Ticket createTicket(Ticket Ticket) {
		return ticketRepo.save(Ticket);
	}


	@Override
	public Ticket updateTicket(Ticket ticket, int id) {
	    Ticket updatedTicket = ticketRepo.findById(id)
	            .orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
	    
	    updatedTicket.setBus(ticket.getBus());
	    updatedTicket.setRoute(ticket.getRoute());
	    updatedTicket.setUser(ticket.getUser());
	    updatedTicket.setStatus(ticket.getStatus());
	    updatedTicket.setCreatedAt(new Date());
	    
	    return ticketRepo.save(updatedTicket);
	}

	@Override
	public Ticket getTicket(int id) {
	    return this.ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
	}

	@Override
	public List<Ticket> getTicket() {
	    return this.ticketRepo.findAll();
	}

	@Override
	public void deleteTicket(int id) {
	    Ticket ticket = this.ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
	    this.ticketRepo.delete(ticket);
	}

	public Ticket getMostRecentWaitingTicket(int busId) {
	    // Create a list of all waiting tickets for the given bus ID, ordered by creation time in descending order
	    List<Ticket> waitingTickets = ticketRepo.findByBusIdAndStatusOrderByCreatedAtDesc(busId, "waiting");

	    // Return the first waiting ticket in the list, or null if the list is empty
	    return waitingTickets.isEmpty() ? null : waitingTickets.get(0);
	}

}
