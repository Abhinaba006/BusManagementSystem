package nrifintech.busMangementSystem.Service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.repositories.BusRepo;

@ExtendWith(MockitoExtension.class)
class BusServiceImplTest {

	@Mock
	private BusRepo busRepo;
	
	@InjectMocks
	BusServiceImpl busServiceImpl;

	

	Bus testBus;

	@Test
    void testCreateBus() {
//         define the behavior of the mocked method
        when(busRepo.save(testBus)).thenReturn(testBus);

        // call the method being tested
        Bus createdBus = busServiceImpl.createBus(testBus);

        assertNotNull(createdBus);
        assertEquals(createdBus, testBus);
    }

	@Test
	void testUpdateBus() {
		Bus existingBus = new Bus();
		existingBus.setBus_number("24wb");
		existingBus.setName("dnb");
		existingBus.setName("uerhjhsdbf");
		existingBus.setTotalNumberOfseats(45);
		
		Bus updatedBus = existingBus;
		updatedBus.setTotalNumberOfseats(2);
		
		when(busRepo.findById(existingBus.getId())).thenReturn(Optional.of(existingBus));
		when(busRepo.save(existingBus)).thenReturn(updatedBus);
		
		Bus result = busServiceImpl.updateBus(updatedBus, existingBus.getId());
		assertEquals(result, updatedBus);

	}
//
	@Test
	void testGetBusById() {
		Bus existingBus = new Bus();
		existingBus.setId(1);
		existingBus.setName("dnb");
		existingBus.setBus_number("24wb");
		existingBus.setTotalNumberOfseats(45);
		
		when(busRepo.findById(existingBus.getId())).thenReturn(Optional.of(existingBus));
		
		Bus result = busServiceImpl.getBus(existingBus.getId());
		assertEquals(result, existingBus);
	}

	@Test
	void testGetAllBus() {
		Bus bus1 = new Bus();
		bus1.setId(1);
		bus1.setName("dnb");
		bus1.setBus_number("24wb");
		bus1.setTotalNumberOfseats(45);
		
		Bus bus2 = new Bus();
		bus2.setId(2);
		bus2.setName("dnb2");
		bus2.setBus_number("25wb");
		bus2.setTotalNumberOfseats(50);
		
		List<Bus> busList = Arrays.asList(bus1, bus2);
		
		when(busRepo.findAll()).thenReturn(busList);
		
		List<Bus> result = busServiceImpl.getBus();
		assertEquals(result, busList);
	}
//
//	@Test
//	void testDeleteBus() {
//		fail("Not yet implemented");
//	}

	@BeforeEach
	public void setUp() {
		// initialize resources here
		// e.g., set up database connections, create test data, etc
		testBus = new Bus();
		testBus.setName("DN8");
		testBus.setBus_number("02sd");
		testBus.setTotalNumberOfseats(20);

	}

}
