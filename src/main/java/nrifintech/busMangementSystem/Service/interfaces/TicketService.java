package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Ticket;
@Service
public interface TicketService {
	Ticket createTicket(Ticket route);
	Ticket updateTicket(Ticket route, int id);
	Ticket getTicket(int id);
	List<Ticket> getTicket();
	void deleteTicket(int id);
}
