package nrifintech.busMangementSystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nrifintech.busMangementSystem.Service.interfaces.DestinationService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@ExtendWith(MockitoExtension.class)
public class DestinationControllerTest {
    
    @Mock
    private DestinationService destinationService;
    
    @InjectMocks
    private DestinationController destinationController;
    
    private List<Destination> destinations;
    
    private Destination destination;
    
    @BeforeEach
    public void setup() {
        destinations = new ArrayList<>();
        destination = new Destination();
        destination.setName("Demo station");
        destination.setLatitude(23.45f);
        destination.setLongitude(34.45f);
        destinations.add(destination);
    }
    
    @Test
    public void testGetAllDestination() {
        //mock
        when(destinationService.getDestination()).thenReturn(destinations);
        // Call the controller method
        ResponseEntity<List<Destination>> response = destinationController.getAllDestination();
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destinations, response.getBody());
    }
    
    @Test
    public void testGetDestinationById() {
        when(destinationService.getDestination(destination.getId())).thenReturn(destination);
        ResponseEntity<Destination> response = destinationController.getDestinationById(destination.getId());
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destination, response.getBody());
    }
    
    @Test
    public void testCreateDestination() {
        when(destinationService.createDestination(any(Destination.class))).thenReturn(destination);

        ResponseEntity<Destination> response = destinationController.createDestination(destination);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(destination, response.getBody());
    }
    
    @Test
    public void testUpdateDestination() {

        when(destinationService.updateDestination(any(Destination.class), any(Integer.class))).thenReturn(destination);

        ResponseEntity<Destination> response = destinationController.updateDestination(destination, destination.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destination, response.getBody());
    }
    
    @Test
    public void testDeleteDestination() {
        doNothing().when(destinationService).deleteDestination(destination.getId());

        ResponseEntity<?> response = destinationController.deleteDestination(destination.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetDestinationByName() {
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        List<Destination> destinations = Arrays.asList(destination1, destination2);

        when(destinationService.getDestinationByName(ArgumentMatchers.anyString())).thenReturn(destinations);

        ResponseEntity<?> response = destinationController.getDestinationByName("Destination");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(destinations, response.getBody());
    }
}
