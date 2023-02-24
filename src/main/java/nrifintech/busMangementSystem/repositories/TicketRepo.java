package nrifintech.busMangementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nrifintech.busMangementSystem.entities.Ticket;


public interface TicketRepo extends  JpaRepository<Ticket, Integer>{

}
