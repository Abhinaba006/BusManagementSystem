package nrifintech.busMangementSystem.Service.impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nrifintech.busMangementSystem.Service.interfaces.BusMapService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteMap;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;
import nrifintech.busMangementSystem.repositories.RouteMapRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;

class RouteServiceImplTest {

    @Mock
    private RouteRepo routeRepo;

    @Mock
    private RouteMapRepo routeMapRepo;

    @Mock
    private BusMapService busMapService;

    @Mock
    private RouteInfoRepo routeInfoRepo;

    @InjectMocks
    private RouteServiceImpl routeService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRoute() {
    	List<String> destinations = Arrays.asList("1_0_10:00:00", "2_1_11:00:00");
        int busId = 1;
        // Mock the routeRepo to return a saved Route with ID 1
        Route savedRoute = new Route();
        savedRoute.setId(1);
        when(routeRepo.save(any(Route.class))).thenReturn(savedRoute);

        // Mock the routeMapRepo to return saved RouteMaps with correct IDs and indices
        List<RouteMap> savedMaps = new ArrayList<>();
        for (int i = 0; i < destinations.size(); i++) {
            String[] parts = destinations.get(i).split("_");
            RouteMap map = new RouteMap();
            map.setRoute_id(savedRoute.getId());
            map.setDestination_id(Integer.parseInt(parts[0]));
            map.setDestination_index(i);
            map.setTime(parts[1]);
            savedMaps.add(map);
            when(routeMapRepo.save(map)).thenReturn(map);
        }

        // Call the createRoute method
        Route createdRoute = routeService.createRoute(destinations, busId);

        // Verify that the created Route object has the correct properties
        assertEquals(savedRoute.getId(), createdRoute.getId());
    }


   
    }
