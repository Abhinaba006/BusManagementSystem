package nrifintech.busMangementSystem.Service.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.repositories.TicketRepo;

// ABHI
@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

	@InjectMocks
	private TicketServiceImpl ticketService;

	@Mock
	private TicketRepo ticketRepo;

	@Mock
	private RouteService routeService;

	@Mock
	private UserService userService;

	@Mock
	private BusService busService;

	@Mock
	private RouteInfoService routeInfoService;

	@BeforeEach
	@Transactional
	public void setUp() {
		Mockito.mockitoSession().initMocks(this);
	}

	@Test
	public void createTicketTest() {
	    Ticket ticket = new Ticket();
	    ticket.setRouteId(1);

	    when(ticketRepo.save(ticket)).thenReturn(ticket);

	    LocalDate date = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
	    String formattedDate = date.format(formatter);
	    
	    RouteInfo routeInfo = new RouteInfo();
	    routeInfo.setId(1); // set the id value
	    routeInfo.setTotal_bookings(2);
	    routeInfo.setTotal_seats(3);

	    when(routeInfoService.getRouteInfo(routeInfo.getId(), null)).thenReturn(routeInfo);

	    ticketService.createTicket(ticket);
	    assertEquals("CONFIRMED", ticket.getStatus());
	}


	@Test
	public void createTicketStatusWaitingTest() {
		Ticket ticket = new Ticket();
		ticket.setRouteId(1);

		when(ticketRepo.save(ticket)).thenReturn(ticket);

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String formattedDate = date.format(formatter);
		RouteInfo routeInfo = new RouteInfo();
		routeInfo.setTotal_bookings(2);
		routeInfo.setTotal_seats(2);

		when(routeInfoService.getRouteInfo(1, null)).thenReturn(routeInfo);

		ticketService.createTicket(ticket);
		assertEquals("WAITING", ticket.getStatus());

	}

	@Test
	void testCancelTicket() {
		// create a ticket
		Ticket ticket = new Ticket();
		ticket.setId(1);
		ticket.setBusId(11);
		ticket.setRouteId(22);
		ticket.setDate("10-03-2023");
		ticket.setStatus("confirmed");
		ticket.setUserId(31);
		
		// Mock the ticketRepo.findById() method
		when(ticketRepo.findById(ticket.getId())).thenReturn(Optional.of(ticket));
		
		// Call the service method
		ticketService.cancelTicket(ticket.getId());
		
		// Verify that the ticket status has been updated to CANCELLED
		Ticket updatedTicket = ticketRepo.findById(ticket.getId()).orElse(null);
		assertNotNull(updatedTicket);
		assertEquals("CANCELLED", updatedTicket.getStatus());
	}


	@Test
	void testGetAllTicketByPersonId() {
	    // create two tickets for the same person
	    Ticket ticket1 = new Ticket();
	    ticket1.setUserId(1);
	    ticket1.setRouteId(1);
	    ticket1.setStatus("CONFIRMED");

	    when(ticketRepo.save(ticket1)).thenReturn(ticket1);

	    ticketRepo.save(ticket1);

	    Ticket ticket2 = new Ticket();
	    ticket2.setUserId(1);
	    ticket2.setRouteId(2);
	    ticket2.setStatus("CONFIRMED");

	    when(ticketRepo.save(ticket2)).thenReturn(ticket2);
	    when(ticketRepo.findByUserId(1)).thenReturn(Arrays.asList(ticket1, ticket2));

	    ticketRepo.save(ticket2);

	    // get all tickets for the person
	    List<Ticket> tickets = ticketService.getAllTicketByPersonId(1);

	    // verify that both tickets have been returned
	    assertEquals(2, tickets.size());
	    assertTrue(tickets.contains(ticket1));
	    assertTrue(tickets.contains(ticket2));
	}

}
