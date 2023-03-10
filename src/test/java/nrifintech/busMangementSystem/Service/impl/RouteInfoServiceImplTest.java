package nrifintech.busMangementSystem.Service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;

@SpringBootTest
public class RouteInfoServiceImplTest {

	   @Mock
	    private RouteInfoRepo routeInfoRepo;

	    @Mock
	    private BusMapRepo busMapRepo;

	    @Mock
	    private BusRepo busRepo;

	    @InjectMocks
	    private RouteInfoServiceImpl routeInfoService;

	    @Before
	    public void setUp() {
	        routeInfoService = new RouteInfoServiceImpl(routeInfoRepo, busMapRepo, busRepo);
	    }
    
    @Test
    public void testPreCheck() {
        int routeId = 1;
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy"));
        
        BusMap busMap = mock(BusMap.class);
        when(busMap.getBus_id()).thenReturn(1);
        when(busMapRepo.findByRouteId(routeId)).thenReturn(busMap);
        
        Bus bus = mock(Bus.class);
        when(bus.getTotalNumberOfseats()).thenReturn(50);
        when(busRepo.findById(1)).thenReturn(Optional.of(bus));
        
        RouteInfo routeInfo = mock(RouteInfo.class);
        when(routeInfoRepo.getRouteByPresentDate(routeId, formattedDate)).thenReturn(null).thenReturn(routeInfo);
        when(routeInfoRepo.save(routeInfo)).thenReturn(routeInfo);

        routeInfoService.preCheck(routeId);
        
        verify(routeInfoRepo).getRouteByPresentDate(routeId, formattedDate);
        verify(busMapRepo).findByRouteId(routeId);
        verify(busRepo).findById(1);
        //verify(routeInfoRepo).save(routeInfo);
    }
    
    @Test
    public void testChangeTotalBooking() {
        int routeId = 1;
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy"));
        
        RouteInfo routeInfo = mock(RouteInfo.class);
        RouteInfo savedRouteInfo = mock(RouteInfo.class); // create a mock object to return when save is called
        when(routeInfoRepo.getRouteByPresentDate(routeId, formattedDate)).thenReturn(routeInfo);
        when(routeInfoRepo.save(routeInfo)).thenReturn(savedRouteInfo); // return the mock object when save is called
        
        routeInfoService.changeTotalBooking(routeId, 1);
        
        //verify(routeInfoRepo).getRouteByPresentDate(routeId, formattedDate);
        verify(routeInfoRepo).save(routeInfo);
        assertEquals(savedRouteInfo.getTotal_bookings(), 0);
    }

    
    @Test
	public void testIncrementOverallBooking() throws ResouceNotFound {
    	RouteInfo routeInfo = new RouteInfo();
    	routeInfo.setDate("10:03:2023");
    	routeInfo.setOverall_bookings(40);
    	routeInfo.setTotal_bookings(30);
    	routeInfo.setTotal_seats(35);
    	routeInfo.setRoute_id(22);
    	
    	BusMap busMap = new BusMap();
    	busMap.setBus_id(11);
    	busMap.setRoute_id(22);
    	
    	Bus bus = new Bus();
    	bus.setBus_number("B01");
    	bus.setName("Howrah-Sealdah");
    	bus.setTotalNumberOfseats(35);
    	
		when(routeInfoRepo.getRouteByPresentDate(anyInt(), ArgumentMatchers.anyString())).thenReturn(routeInfo);
		when(busMapRepo.findByRouteId(anyInt())).thenReturn(busMap);
		when(busRepo.findById(anyInt())).thenReturn(Optional.of(bus));
		
		routeInfoService.incrementOverallBooking(1);
		
		assertEquals(40, routeInfo.getOverall_bookings()-1);
	}
    
}
