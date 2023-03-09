package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
		ticket.setBus(bus);
		ticket.setCreatedAt(new Date());
		ticket.setUser(user1);
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
		ticket2.setBus(bus);
		ticket2.setCreatedAt(new Date());
		ticket2.setStatus("Confirmed");
		ticket2.setUser(user2);
		ticketRepo.save(ticket2);
		
		List<Ticket> tickets = ticketRepo.findAll();
		System.out.println("Testing find all method:" +tickets.size());
		assertThat(tickets).hasSize(2);
	}

	@Test
	public void findbyId() {
		Ticket result = ticketRepo.findById(ticket.getId()).orElse(null);
		assertNotNull(result);
		assertThat(result.getStatus()).matches(ticket.getStatus());
	}
	
	@Test
	void testFindByBusIdAndStatusOrderByCreatedAtDesc() {
		//lets create 2 waiting tickets.
		Ticket t1 = new Ticket();
		t1.setBus(bus);
		t1.setCreatedAt(new Date());
		t1.setStatus("waiting");
		t1.setUser(user1);
		ticketRepo.save(t1);
		
		Ticket t2 = new Ticket();
		t2.setBus(bus);
		t2.setCreatedAt(new Date(System.currentTimeMillis() -  60 * 60 * 1000));
		t2.setStatus("waiting");
		t2.setUser(user2);
		ticketRepo.save(t2);
		
		//get 
		System.out.println("Bus id: "+bus.getId());
		//fetch bus_id from db.
		List<Ticket> result = ticketRepo.findByBusIdAndStatusOrderByCreatedAtDesc(busRepo.findAll().get(0).getId(), "waiting");
		assertThat(result).hasSize(2);
		
	}

	@Test
	void testFindByCreatedAtBefore() {
		//create one ticket for yesterday.
		Ticket t = new Ticket();
		t.setBus(bus);
		t.setCreatedAt(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
		t.setUser(user1);
		t.setStatus("availed");
		ticketRepo.save(t);
		
		LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().atStartOfDay();
        Date current_date = Date.from(midnight.atZone(ZoneId.systemDefault()).toInstant());

		List<Ticket> result = ticketRepo.findByCreatedAtBefore(current_date);
		assertThat(result.get(0).getStatus()).isEqualTo(t.getStatus());
	}

	@Test
	void testFindConfirmedTicketByTicket() {
		List<Ticket> result = ticketRepo.findConfirmedTicketByUser(user1.getId());
		assertThat(result.get(0).getStatus()).isEqualTo(ticket.getStatus());
	}

	@Test
	void testFindByUserId() {
		List<Ticket> result = ticketRepo.findByUserId(user1.getId());
		assertThat(result.get(0).getStatus()).isEqualTo(ticket.getStatus());
	}

	@Test
	void testFindByCreatedToday() {
		//insert one ticket for yesterday.
		//and the query should return result not more than 1.
		Ticket t = new Ticket();
		t.setBus(bus);
		t.setUser(user1);
		t.setStatus("Waiting");
		t.setCreatedAt(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
		
		List<Ticket>  result = ticketRepo.findByCreatedToday(new Date());
		assertThat(result).hasSize(1);
	}

}
