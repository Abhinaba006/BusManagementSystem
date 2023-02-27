package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.TicketRepo;
import nrifintech.busMangementSystem.Service.interfaces.*;
@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepo ticketRepo;
	
	@Override
	public Ticket createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepo.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket newTicket, int id) {
		// TODO Auto-generated method stub
		Ticket  ticket = ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
		ticket.setId(newTicket.getId());
		// fetch the bus and add it
		
		// fetch the route and add it
		
		// fetch the user and add it
		
		
		return this.ticketRepo.save(ticket);
	}

	@Override
	public Ticket getTicket(int id) {
		// TODO Auto-generated method stub
		 return this.ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
	}

	@Override
	public List<Ticket> getTicket() {
		// TODO Auto-generated method stub
		return  this.ticketRepo.findAll();
	}

	@Override
	public void deleteTicket(int id) {
		// TODO Auto-generated method stub
		Ticket ticket = this.ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
		ticketRepo.delete(ticket);	
	}

}
