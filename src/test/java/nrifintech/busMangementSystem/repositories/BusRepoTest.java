package nrifintech.busMangementSystem.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nrifintech.busMangementSystem.entities.Bus;

@SpringBootTest
class BusRepoTest {

	@Autowired
	private BusRepo busRepo;

	private Bus testBus;

	@Test
	public void testFindAll() {
		// to do
		Bus testBus = new Bus();
		testBus.setName("DN9");
		testBus.setBus_number("02sd");
		testBus.setTotalNumberOfseats(21);
		busRepo.save(testBus);
		
		List<Bus> buses = busRepo.findAll();
		// Assert that there is at least one bus in the database
		assertTrue(buses.size()==2);
	}

	@Test
	public void findbyId() {
		// to do
		Bus foundBus = busRepo.findById(testBus.getId()).orElse(null);
		// Assert that the bus was found
		assertNotNull(foundBus);
		// Assert that the found bus matches the test bus
		
		assertEquals(testBus, foundBus);
	}

	@Test
	public void deleteById() {
		busRepo.deleteById(testBus.getId());
		// Assert that the bus was deleted
		assertFalse(busRepo.findById(testBus.getId()).isPresent());
	}

	@BeforeEach
	public void setUp() {
		// initialize resources here
		// e.g., set up database connections, create test data, etc
		testBus = new Bus();
		testBus.setName("DN8");
		testBus.setBus_number("02sd");
		testBus.setTotalNumberOfseats(20);
		busRepo.save(testBus);

	}

	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		busRepo.deleteAll();
	}

}
