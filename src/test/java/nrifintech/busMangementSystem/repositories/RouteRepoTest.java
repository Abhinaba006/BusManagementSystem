package nrifintech.busMangementSystem.repositories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nrifintech.busMangementSystem.entities.Route;


@SpringBootTest
class RouteRepoTest {

	@Autowired
	private RouteRepo routeRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindAll() {
		// adding one more data
		Route route = new Route();
		route.setStart_destination_id(1);
		route.setEnd_destination_id(4);
		route.setTotal_destinations(5);
		routeRepo.save(route);
		List<Route> Routes = routeRepo.findAll();
		assertThat(Routes).hasSize(2);
	}

	@Test
	public void findbyId() {
		List<Route> temp = routeRepo.searchBySourceAndDestination(3, 6);
		Optional<Route> result = routeRepo.findById(temp.get(0).getId());
		assertThat(result).isPresent();
	}


	@Test
	void testSearchBySourceAndDestination() {
		List<Route> result = routeRepo.searchBySourceAndDestination(3, 6);
		assertThat(result.get(0).getTotal_destinations()).isEqualTo(4);
		
	}
	
	@BeforeEach
	public void setUp() {
		// initialize resources here
		// e.g., set up database connections, create test data, etc.
		System.out.println("setting up");
		Route route = new Route();
		route.setStart_destination_id(3);
		route.setEnd_destination_id(6);
		route.setTotal_destinations(4);
		routeRepo.save(route);
	}

	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		System.out.println("Tearing Down");
		this.routeRepo.deleteAll();
	}
	

}
