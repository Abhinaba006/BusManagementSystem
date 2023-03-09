package nrifintech.busMangementSystem.Service.impl;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;

@ExtendWith(MockitoExtension.class)
class RouteInfoServiceImplTest {

	@Mock
    private RouteInfoRepo routeInfoRepo;
	
	@Mock
	private BusMapRepo busMapRepo;
	
	@Mock
	private BusRepo busRepo;

	@InjectMocks
    private RouteInfoServiceImpl routeInfoService;
	
	RouteInfo r1,r2,r3;
	
    @BeforeEach
    void setup()
    {
    	System.out.println("Setting up");
    	r1 = new RouteInfo();
        r1.setDate("09:03:2023");
        r1.setOverall_bookings(30);
        r1.setRoute_id(11);
        r1.setTotal_bookings(23);
        r1.setTotal_seats(40);
        
        r2 = new RouteInfo();
        r2.setDate("09:03:2023");
        r2.setOverall_bookings(33);
        r2.setRoute_id(22);
        r2.setTotal_bookings(25);
        r2.setTotal_seats(40);
    
        r3 = new RouteInfo();
        r3.setDate("08:03:2023");
        r3.setOverall_bookings(30);
        r3.setRoute_id(22);
        r3.setTotal_bookings(23);
        r3.setTotal_seats(40);
      }
	
	@Test
	void testPreCheck() {
		when(routeInfoRepo.getRouteByPresentDate(r1.getRoute_id(), r1.getDate())).thenReturn(r1);

		BusMap busMap = new BusMap();
		busMap.setBus_id(1);
		busMap.setRoute_id(r1.getRoute_id());
		when(busMapRepo.findByRouteId(r1.getRoute_id())).thenReturn(busMap);

		Bus bus = new Bus();
		bus.setTotalNumberOfseats(50);
		bus.setBus_number("WB0123");
		bus.setName("HW-SDF");
		Optional<Bus> b = Optional.of(bus);
		when(busRepo.findById(1)).thenReturn(b);

		// Method invocation
		routeInfoService.preCheck(r1.getRoute_id());

		// Verification
		Mockito.verify(routeInfoRepo, Mockito.times(1)).save(Mockito.any(RouteInfo.class));	
	}

	@Test
	void testChangeTotalBooking() {
		fail("Not yet implemented");
	}

	@Test
	void testIncrementOverallBooking() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRouteInfo() {
		fail("Not yet implemented");
	}

}
