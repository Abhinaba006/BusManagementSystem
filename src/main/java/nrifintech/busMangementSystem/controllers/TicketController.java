package nrifintech.busMangementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.TicketService;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {
    @Autowired
    TicketService ticketService;
    
    //get
    @GetMapping("/ticket/get")
    public ResponseEntity<List<Ticket>> getAllTicket(){
        return ResponseEntity.ok(this.ticketService.getTicket());
    }
    
    @GetMapping("/ticket/get/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") int ticketId){
        return ResponseEntity.ok(this.ticketService.getTicket(ticketId));
    }
    
    //post
    @PostMapping("/ticket/create")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket){
        Ticket createdTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }
    
    //update
    @PostMapping("/ticket/update/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable("ticketId") int ticketId){
        Ticket updatedTicket = ticketService.updateTicket(ticket, ticketId);
        return ResponseEntity.ok(updatedTicket);
    }
    
    //delete
    @DeleteMapping("/ticket/delete/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") int ticketId){
        ticketService.deleteTicket(ticketId);
        return new ResponseEntity(new ApiResponse("ticket deleted", true), HttpStatus.OK);
    }
}
