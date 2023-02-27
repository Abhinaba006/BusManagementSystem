package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
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
	public Ticket createTicket(Ticket ticket) {
		Bus bus = busRepo.findById(ticket.getBus().getId())
				.orElseThrow(() -> new ResouceNotFound("Bus", "id", ticket.getBus().getId()));
		ticket.setBus(bus);

		Route route = routeRepo.findById(ticket.getRoute().getId())
				.orElseThrow(() -> new ResouceNotFound("Route", "id", ticket.getRoute().getId()));
		ticket.setRoute(route);

		User user = userRepo.findById(ticket.getUser().getId())
				.orElseThrow(() -> new ResouceNotFound("User", "id", ticket.getUser().getId()));
		ticket.setUser(user);

		return ticketRepo.save(ticket);
	}

	@Override
    public Ticket updateTicket(Ticket newTicket, int id) {
        Ticket ticket = ticketRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Ticket", "id", id));
        //ticket.setId(newTicket.getId());

        // Fetch and add the bus
        Bus bus = busRepo.findById(newTicket.getBus().getId())
                .orElseThrow(() -> new ResouceNotFound("Bus", "id", newTicket.getBus().getId()));
        ticket.setBus(bus);
        
        // Fetch and add the route
        Route route = routeRepo.findById(newTicket.getRoute().getId())
                .orElseThrow(() -> new ResouceNotFound("Route", "id", newTicket.getRoute().getId()));
        ticket.setRoute(route);
        
        // Fetch and add the user
//        User user = userRepo.findById(newTicket.getUser().getId())
//                .orElseThrow(() -> new ResouceNotFound("User", "id", newTicket.getUser().getId()));
//        ticket.setUser(user);
        
        return ticketRepo.save(ticket);
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


}
