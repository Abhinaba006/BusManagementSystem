package nrifintech.busMangementSystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.payloads.ApiResponse;

public class TicketControllerTest {

  @Mock
  private TicketService ticketService;

  @InjectMocks
  private TicketController ticketController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateTicket() {
    Ticket ticket = new Ticket();
    ticket.setBusId(11);
    ticket.setDate("10-03-2023");
    ticket.setRouteId(22);
    ticket.setStatus("confirmed");
    ticket.setUserId(1);

    doNothing().when(ticketService).createTicket(any(Ticket.class));

    ResponseEntity<?> response = ticketController.createTicket(ticket);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Ticket booked successfully!", ((ApiResponse) response.getBody()).getMessage());
    assertEquals(true, ((ApiResponse) response.getBody()).isSuccess());
  }
  
  @Test
  public void testCancelTicket() {
    int ticketId = 1;

    doNothing().when(ticketService).cancelTicket(ticketId);

    ResponseEntity<?> response = ticketController.cancleTicket(ticketId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Ticket cancelled successfully!", ((ApiResponse) response.getBody()).getMessage());
    assertEquals(true, ((ApiResponse) response.getBody()).isSuccess());
  }
}
