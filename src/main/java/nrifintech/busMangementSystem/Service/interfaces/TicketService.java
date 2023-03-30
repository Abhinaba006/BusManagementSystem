package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.payloads.TicketDto;
import nrifintech.busMangementSystem.payloads.TicketResponse;
import nrifintech.busMangementSystem.repositories.TicketRepo;

@Service
public interface TicketService {	
    void createTicket(Ticket ticket);
    void cancelTicket(int ticket_id);
    List<Ticket> getAllTicketByPersonId(int userId);
    Integer getTotalTicketsDoneByUser(int userId);
	// List<Ticket> getAllTicketByPersonEmail(String userEmail);
    TicketResponse getAllTicketByPersonEmail(String userEmail,Integer pageNumber,Integer pageSize);
}
