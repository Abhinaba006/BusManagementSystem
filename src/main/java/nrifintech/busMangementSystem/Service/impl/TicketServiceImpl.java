package nrifintech.busMangementSystem.Service.impl;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;

import nrifintech.busMangementSystem.Service.interfaces.TicketService;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;

import nrifintech.busMangementSystem.exception.UnauthorizedAction;

import nrifintech.busMangementSystem.payloads.TicketDto;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	RouteService routeService;
	@Autowired
	UserService userService;
	@Autowired
	BusService busService;
	@Autowired
	RouteInfoService routeInfoService;
	
	@Transactional
	@Modifying
	public void createTicket(Ticket ticket){
		//dont allow user to book a ticket if their ticket is already booked.
		//check from db if user data is present for today or not.
		if(ticketRepo.findUserByPresentDate(ticket.getUserId(), ticket.getDate())!=null)
			throw new UnauthorizedAction("Ticket already","");
		
		//change the status of the past ticket.
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String currentDate = formatter.format(now);
		 List<Ticket> pastTickets = this.ticketRepo.findPastTickets(currentDate);
	        //System.out.println("size iss " + ticketsCreatedToday.size());
	        // check if it is the first ticket of the day
			for(Ticket t:pastTickets)
			{
				if(t.getStatus().equals("WAITING"))
					t.setStatus("EXPIRED");
				else if(t.getStatus().equals("CONFIRMED"))
					t.setStatus("AVAILED");
				this.ticketRepo.save(t);
			}

		
		Ticket _ticket = ticketRepo.save(ticket);
         
		//Check first if there is seat or not
		routeInfoService.preCheck(_ticket.getRouteId(),ticket.getDate());
		RouteInfo _routeInfo = routeInfoService.getRouteInfo(_ticket.getRouteId(), ticket.getDate());
		if(_routeInfo.getTotal_bookings() < _routeInfo.getTotal_seats()){
			_ticket.setStatus("CONFIRMED");
			routeInfoService.changeTotalBooking(_ticket.getRouteId(), 1,_ticket.getDate());
			
		}
		else{
			_ticket.setStatus("WAITING");
			ticketRepo.save(_ticket);
		}
		routeInfoService.incrementOverallBooking(_ticket.getRouteId(),_ticket.getDate());
		//If not then add it to the queue and set the status of the ticket as waiting

		//If there is seat then update the route info

	}
	public void cancelTicket(int ticket_id){
		//Get the ticket
		Optional<Ticket> _ticketObj = ticketRepo.findById(ticket_id);
		Ticket _ticket = _ticketObj.get();

		//Get the route info
		RouteInfo _routeInfo = routeInfoService.getRouteInfo(_ticket.getRouteId(),_ticket.getDate());

		//Decrease the value
		routeInfoService.changeTotalBooking(_ticket.getRouteId(),-1,_ticket.getDate());

		_ticket.setStatus("CANCELLED");
		ticketRepo.save(_ticket);
		//If there are available seats then make the status to confirmed for the first ticket in the queue
			//Iterate through all the tickets and check which ticket is not cancelled and make it confirm
		Ticket _latestWaitingTicket = ticketRepo.findLatestUser(_ticket.getRouteId(),_ticket.getDate());
		if(_latestWaitingTicket!=null){
			_latestWaitingTicket.setStatus("CONFIRMED");
			ticketRepo.save(_latestWaitingTicket);
			routeInfoService.changeTotalBooking(_latestWaitingTicket.getRouteId(),1,_ticket.getDate());
		}

	}

	public List<Ticket> getAllTicketByPersonId(int userId){
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String currentDate = formatter.format(now);

		//change the status of the past ticket.
		 List<Ticket> pastTickets = this.ticketRepo.findPastTickets(currentDate);
	        //System.out.println("size iss " + ticketsCreatedToday.size());
	        // check if it is the first ticket of the day
			for(Ticket t:pastTickets)
			{
				if(t.getStatus().equals("WAITING"))
					t.setStatus("EXPIRED");
				else if(t.getStatus().equals("CONFIRMED"))
					t.setStatus("AVAILED");
				this.ticketRepo.save(t);
			}
		return ticketRepo.findByUserId(userId);
	}
	@Override
	public Integer getTotalTicketsDoneByUser(int userId) {
		return this.ticketRepo.getTotalTicketsDoneByUser(userId);
	}
	@Override
	public List<Ticket> getAllTicketByPersonEmail(String userEmail) {
		// TODO Auto-generated method stub
		System.out.println(userEmail);
		User user = userService.getUserByEmail(userEmail);
		return getAllTicketByPersonId(user.getId());
	}

}
