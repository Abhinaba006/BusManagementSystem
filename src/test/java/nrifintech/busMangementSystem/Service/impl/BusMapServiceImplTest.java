package nrifintech.busMangementSystem.Service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.repositories.BusMapRepo;

@ExtendWith(MockitoExtension.class)
class BusMapServiceImplTest {

	@Mock
    private BusMapRepo busMapRepo;

	@InjectMocks
    private BusMapServiceImpl busMapService;
	
	BusMap bm1,bm2,bm3;
	
    @BeforeEach
    void setup()
    {
    	System.out.println("Setting up");
    	bm1 = new BusMap();
        bm1.setBus_id(11);
        bm1.setRoute_id(21);
        
        bm2 = new BusMap();
        bm2.setBus_id(17);
        bm2.setRoute_id(29);
    
        bm3 = new BusMap();
        bm3.setBus_id(15);
        bm3.setRoute_id(24);
      }
    
	@Test
	void testAddBusMap() {
		busMapService.addBusMap(bm1.getRoute_id(),bm1.getBus_id());
		// Verify the mock behavior
        Mockito.verify(busMapRepo, Mockito.times(1)).save(Mockito.any(BusMap.class));
	}

	@Test
	void testUpdateBusByRoute() {
		//update bm2 with bm3
		//mock for repo used in update function
		when(busMapRepo.findByRouteId(bm2.getRoute_id())).thenReturn(bm2);
		busMapService.updateBusByRoute(bm2.getRoute_id(), bm3.getBus_id());
		Mockito.verify(busMapRepo, Mockito.times(1)).save(Mockito.any(BusMap.class));	
	}

	@Test
	void testDeleteByRouteId() {
        busMapService.deleteByRouteId(bm1.getRoute_id());
        // Verify the mock behavior
        Mockito.verify(busMapRepo, Mockito.times(1)).deleteByRouteId(bm1.getRoute_id());
	}

	@Test
	void testDeleteByBusId() {
        busMapService.deleteByBusId(bm1.getBus_id());
        // Verify the mock behavior
        Mockito.verify(busMapRepo, Mockito.times(1)).deleteByBusId(bm1.getBus_id());
	}

	@Test
	void testGetBusIdByRouteId() {
		when(busMapRepo.findByBusId(bm1.getRoute_id())).thenReturn(bm1);
		int bus_id = busMapService.getBusIdByRouteId(bm1.getRoute_id());
		assertEquals(bus_id,bm1.getBus_id());
	}

	@Test
	void testGetRouteIdByBusId() {
		when(busMapRepo.findByRouteId(bm2.getBus_id())).thenReturn(bm2);
		int route_id = busMapService.getRouteIdByBusId(bm2.getBus_id());
		assertEquals(route_id,bm2.getRoute_id());
	}

}
