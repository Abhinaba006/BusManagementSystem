package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;

@SpringBootTest
class TicketRepoTest {
	
	private User user1;
	private User user2;
	private Bus bus;
	private Ticket ticket;

	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BusRepo busRepo;
	

//	@Test
//	void contextLoads() {
//	}


	@BeforeEach
	public void setUp() {
		// initialize resources here
		
		//creating user instance.
		user1 = new User();
		user1.setEmail("demo123@gmail.com");
		user1.setName("demoUser");
		user1.setPassword("demo123");
		userRepo.save(user1);
		
		//creating bus instance.
		bus = new Bus();
		bus.setName("Howrah-SaltLake");
		bus.setTotalNumberOfseats(30);
		bus.setBus_number("WB215|A");
		busRepo.save(bus);
		
		//creating ticket instance.
		ticket = new Ticket();
		ticket.setBusId(bus.getId());
		
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        String formattedDate = date.format(formatter);
        
        
		ticket.setDate(formattedDate);
		ticket.setUserId(user1.getId());
		ticket.setStatus("Confirmed");
		ticketRepo.save(ticket);
	}

	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		System.out.println("Tearing Down");
//		this.userRepo.deleteAll();
//		this.busRepo.deleteAll();
		this.ticketRepo.deleteAll();
		this.userRepo.deleteAll();
		this.busRepo.deleteAll();
		
	}

	@Test
	public void testFindAll() {
		// adding one more data, for another user in the same bus
		user2 = new User();
		user2.setEmail("temp123@gmail.com");
		user2.setName("tempUser");
		user2.setPassword("temp123");
		userRepo.save(user2);
		
		//creating another ticket.
		Ticket ticket2 = new Ticket();
		ticket2.setBusId(bus.getId());
		
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        String formattedDate = date.format(formatter);
        
        
		ticket2.setDate(formattedDate);
		ticket2.setStatus("Confirmed");
		ticket2.setUserId(user2.getId());
		ticketRepo.save(ticket2);
		
		List<Ticket> tickets = ticketRepo.findAll();
		System.out.println("Testing find all method:" +tickets.size());
		assertThat(tickets).hasSize(2);
	}

	@Test
	public void findbyId() {
		Ticket result = ticketRepo.findById(ticket.getId()).orElse(null);
		assertNotNull(result);
		assertEquals(result, ticket);
		assertThat(result.getStatus()).matches(ticket.getStatus());
	}
	
	@Test
	public void getLastWaitingTicket() {
		
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        String formattedDate = date.format(formatter);
        
        
		User user1 = new User();
		user1.setEmail("temp123@gmail.com");
		user1.setName("tempUser");
		user1.setPassword("temp123");
		userRepo.save(user1);
		
		Ticket ticket1 = new Ticket();
		ticket1.setBusId(bus.getId());
		ticket1.setStatus("waiting");
		ticket1.setDate(formattedDate);
		ticket1.setUserId(user1.getId());
		ticket1.setRouteId(0);

		ticketRepo.save(ticket1);
		
		// user 2 
		
		
		Ticket ticket2 = new Ticket();
		ticket2.setBusId(bus.getId());
		ticket2.setStatus("waiting");
		ticket2.setDate(formattedDate);
		ticket2.setUserId(user1.getId()+1);
		ticket2.setRouteId(0);

		ticketRepo.save(ticket2);
		
		
		
		
		Ticket ticket = ticketRepo.findLatestUser(0, formattedDate);
		assertEquals(ticket, ticket1);
		
	}


	@Test
	void testFindByUserId() {
		List<Ticket> result = ticketRepo.findByUserId(user1.getId());
		assertThat(result.get(0).getStatus()).isEqualTo(ticket.getStatus());
	}


}
