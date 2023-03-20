package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteInfo;

@SpringBootTest
class RouteInfoRepoTest {

	@Autowired
	private RouteInfoRepo routeInfoRepo;
	
	private RouteInfo r1,r2,r3;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testFindAll() {
	    //storing 3 routeInfos in routeInfo db, to check if findAll fetches these 3 data or not.
		List<RouteInfo> routeInfos = routeInfoRepo.findAll();
		assertThat(routeInfos).hasSize(3);
	}

	@Test
	public void findbyId() {
		Optional<RouteInfo> result  = routeInfoRepo.findById(r1.getId());
		assertThat(result).isPresent();
	}

	@Test
	void testGetRouteByPresentDate() {
		RouteInfo result = routeInfoRepo.getRouteByPresentDate(r1.getRoute_id(), r1.getDate());
		assertThat(result.getDate()).isEqualTo(r1.getDate());
		assertThat(result.getOverall_bookings()).isEqualTo(r1.getOverall_bookings());
		
	}

	@Test
	void testDeleteRouteInfo() {
		routeInfoRepo.deleteRouteInfo(r1.getRoute_id());
		// now find it again and check if its deleted properly or not.
       Optional<RouteInfo> deletedData = routeInfoRepo.findById(r1.getId());
       assertFalse(deletedData.isPresent()); // Make sure the entity was deleted
	}

	@Test
	void testGetRouteData() {
		List<RouteInfo> result = routeInfoRepo.getRouteData(r2.getRoute_id());
		//for routeId passed, we have 2 data in the db, lets check if we get 2 or not.
		//List<RouteInfo> result = routeInfoRepo.findAll();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getOverall_bookings()+result.get(1).getOverall_bookings()).isEqualTo(r2.getOverall_bookings()+r3.getOverall_bookings());
	}
	
	@BeforeEach
	public void setUp() {
		// initialize resources here
		System.out.println("setting up");
		r1 = new RouteInfo();
		r1.setDate("09-03-2023");
		r1.setRoute_id(1);
		r1.setTotal_seats(40);
		r1.setOverall_bookings(60);
		r1.setTotal_bookings(23);
		routeInfoRepo.save(r1);
		
		r2 = new RouteInfo();
		r2.setDate("08-03-2023");
		r2.setRoute_id(2);
		r2.setTotal_seats(30);
		r2.setOverall_bookings(50);
		r2.setTotal_bookings(23);
		routeInfoRepo.save(r2);
		
		r3 = new RouteInfo();
		r3.setDate("09-03-2023");
		r3.setRoute_id(2);
		r3.setTotal_seats(40);
		r3.setOverall_bookings(50);
		r3.setTotal_bookings(21);
		routeInfoRepo.save(r3);
	}

	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		System.out.println("Tearing Down");
		routeInfoRepo.deleteAll();
	}

}
