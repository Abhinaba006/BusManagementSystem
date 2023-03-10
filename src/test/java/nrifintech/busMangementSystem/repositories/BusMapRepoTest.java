package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.User;

@SpringBootTest
class BusMapRepoTest {

	@Autowired
	private BusMapRepo busMapRepo;
	
	private BusMap busMap,busMap2;
	
	@Test
	void contextLoads() {
	}
	@Test
	public void testFindAll() {
		// adding one more data
		busMap2 = new BusMap();
		busMap2.setBus_id(30);
		busMap2.setRoute_id(40);
		busMapRepo.save(busMap2);
		//storing 2 buses in BusMap db, to check if findAll fetches these 2 data or not.
		List<BusMap> users = busMapRepo.findAll();
		assertThat(users).hasSize(2);
	}

	@Test
	public void findbyId() {
		Optional<BusMap> temp = Optional.ofNullable(busMapRepo.findByBusId(busMap.getBus_id()));
		Optional<BusMap> optionalUser = busMapRepo.findById(temp.get().getId());
		assertThat(optionalUser).isPresent();
	}

	@Test
	void testDeleteByBusId() {
		busMapRepo.deleteByBusId(busMap.getBus_id());
		 // now find it again and check if its deleted properly or not.
        Optional<BusMap> deletedData = busMapRepo.findById(busMap.getId());
        assertFalse(deletedData.isPresent()); // Make sure the entity was deleted
	}

	@Test
	void testDeleteByRouteId() {
		busMapRepo.deleteByRouteId(busMap.getRoute_id());
		 // now find it again and check if its deleted properly or not.
       Optional<BusMap> deletedData = busMapRepo.findById(busMap.getId());
       assertFalse(deletedData.isPresent()); // Make sure the entity was deleted
	}

	@Test
	void testFindByBusId() {
		BusMap fetchedData = busMapRepo.findByBusId(busMap.getBus_id());
		assertThat(fetchedData.getBus_id()).isEqualTo(busMap.getBus_id());
		assertThat(fetchedData.getRoute_id()).isEqualTo(busMap.getRoute_id());
	}

	@Test
	void testFindByRouteId() {
		BusMap fetchedData = busMapRepo.findByRouteId(busMap.getRoute_id());
		assertThat(fetchedData.getBus_id()).isEqualTo(busMap.getBus_id());
		assertThat(fetchedData.getRoute_id()).isEqualTo(busMap.getRoute_id());
	}
	
	@BeforeEach
	public void setUp() {
		// initialize resources here
		System.out.println("setting up");
		busMap = new BusMap();
		busMap.setBus_id(10);
		busMap.setRoute_id(20);
		busMapRepo.save(busMap);
	}

	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		System.out.println("Tearing Down");
		this.busMapRepo.deleteAll();
	}
}
