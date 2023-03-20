package nrifintech.busMangementSystem.controllers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;

class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private RouteService routeService;

    @Mock
    private RouteInfoService routeInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRoute() {
        List<String> destinations = Arrays.asList("destination1", "destination2");
        int busId = 123;

        Route createdRoute = new Route();
        when(routeService.createRoute(destinations, busId)).thenReturn(createdRoute);

        ResponseEntity<Route> response = routeController.createRoute(destinations, busId);

        verify(routeService).createRoute(destinations, busId);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdRoute, response.getBody());
    }

    @Test
    void testDeleteRoute() {
        int routeId = 123;

        ResponseEntity<?> response = routeController.deleteRoute(routeId);

        verify(routeService).deleteRoute(routeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetAllRoute() {
        // mock RouteService
        RouteService routeService = Mockito.mock(RouteService.class);

        // list of routes to be returned by the mock RouteService
        List<Route> expectedRoutes = new ArrayList<>();
        expectedRoutes.add(new Route());
        expectedRoutes.add(new Route());
        expectedRoutes.add(new Route());

        // mock RouteService to return the list of routes when getRoute() is called
        Mockito.when(routeService.getRoute()).thenReturn(expectedRoutes);

        // new RouteController and inject the mock RouteService
        RouteController routeController = new RouteController();
        routeController.setRouteService(routeService);

        // getAllroute() method on the RouteController
        ResponseEntity<List<Route>> response = routeController.getAllroute();

        // Verify
        Mockito.verify(routeService, Mockito.times(1)).getRoute();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRoutes, response.getBody());
    }
    
    @Test
    public void testGetRouteById() {
        // create a mock Route object
        Route mockRoute = new Route();
        mockRoute.setId(1);
        mockRoute.setStart_destination_id(3);
        mockRoute.setEnd_destination_id(7);
        mockRoute.setTotal_destinations(5);

        when(routeService.getRoute(anyInt())).thenReturn(mockRoute);

        ResponseEntity<Route> response = routeController.getRouteById(1);

        // verify
        verify(routeService, times(1)).getRoute(1);

        // verify that the HTTP status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // verify that the response body contains the expected Route object
        assertEquals(mockRoute, response.getBody());
    }
    
    @Test
    public void testUpdateRoute() {
        RouteService routeService = mock(RouteService.class);
        RouteController routeController = new RouteController();
        routeController.setRouteService(routeService);

        int routeId = 1;
        List<String> destinations = Arrays.asList("Dest1", "Dest2");
        int busId = 1;
        Route updatedRoute = new Route();
   
        when(routeService.updateRoute(destinations, routeId, busId)).thenReturn(updatedRoute);
        
        ResponseEntity<Route> responseEntity = routeController.updateRoute(destinations, busId, routeId);
        
        verify(routeService).updateRoute(destinations, routeId, busId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedRoute, responseEntity.getBody());
    }

    @Test
    public void testGetRouteDestinations() {
        int routeId = 1;
        List<Map<String, Object>> expectedDestinations = new ArrayList<>();
        expectedDestinations.add(new HashMap<>());

        Mockito.when(routeService.getRouteDestinations(routeId)).thenReturn(expectedDestinations);

        ResponseEntity<?> response = routeController.getRouteDestinations(routeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Map<String, Object>> actualDestinations = (List<Map<String, Object>>) response.getBody();
        assertEquals(expectedDestinations.size(), actualDestinations.size());
    }



}
