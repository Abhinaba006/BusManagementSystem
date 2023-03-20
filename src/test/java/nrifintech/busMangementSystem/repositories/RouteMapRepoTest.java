package nrifintech.busMangementSystem.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import nrifintech.busMangementSystem.entities.RouteMap;
// ABHI
@SpringBootTest
class RouteMapRepoTest {
	RouteMap routeMap1, routeMap2, routeMap3, routeMap4;

	@Autowired
	RouteMapRepo routeMapRepo;
	
	@BeforeEach
	@Transactional
	public void setUp() {

		this.routeMapRepo.deleteAll();
		// create a route
		routeMap1 = new RouteMap();
		routeMap1.setDestination_id(2);
		routeMap1.setDestination_index(0);
		routeMap1.setRoute_id(1);
		routeMap1.setTime("12:04am");
		routeMapRepo.save(routeMap1);
		
		routeMap2 = new RouteMap();
		routeMap2.setDestination_id(1);
		routeMap2.setDestination_index(1);
		routeMap2.setRoute_id(1);
		routeMap2.setTime("12:14am");
		routeMapRepo.save(routeMap2);
		
		routeMap3 = new RouteMap();
		routeMap3.setDestination_id(4);
		routeMap3.setDestination_index(0);
		routeMap3.setRoute_id(2);
		routeMap3.setTime("11:04am");
		routeMapRepo.save(routeMap3);
		
		routeMap4 = new RouteMap();
		routeMap4.setDestination_id(5);
		routeMap4.setDestination_index(1);
		routeMap4.setRoute_id(2);
		routeMap4.setTime("11:24am");
		routeMapRepo.save(routeMap4);
	}
	@Test
	void findAll() {
		List<RouteMap> existingRouteMap = routeMapRepo.findAll();
		assertEquals(existingRouteMap.size(), 4);
	}
	
	
	@Test
	@Transactional
	void testDeleteFromRepoByRouteId() {
		routeMapRepo.deleteFromRepoByRouteId(routeMap1.getRoute_id());
		List<RouteMap> existingRouteMap = routeMapRepo.findAll();
		assertEquals(existingRouteMap.size(), 2);
	}

	@Test
	void testGetByRouteIdSortedByDestinationIndex() {
		List<RouteMap> listRouteMap = routeMapRepo.getByRouteIdSortedByDestinationIndex(routeMap1.getRoute_id());
		 assertThat(listRouteMap)
	        .as("Route maps list should be sorted by destination index")
	        .isSortedAccordingTo(Comparator.comparing(RouteMap::getDestination_index));
		
	}
	
	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		System.out.println("Tearing Down");
		this.routeMapRepo.deleteAll();
	}
}
