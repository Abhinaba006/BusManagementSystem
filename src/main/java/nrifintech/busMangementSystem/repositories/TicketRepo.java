package nrifintech.busMangementSystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nrifintech.busMangementSystem.entities.Ticket;


public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    @Query("SELECT t FROM Ticket t WHERE t.status = 'waiting' ORDER BY t.createdAt ASC")
    List<Ticket> findByBusIdAndStatusOrderByCreatedAtDesc(int busId, String status);
}
