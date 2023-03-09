package nrifintech.busMangementSystem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;


import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;


public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT * FROM ticket WHERE user_id = :user_id", nativeQuery = true)
    List<Ticket> findByUserId(@Param("user_id") int user_id);
    @Query(value = "SELECT * FROM ticket WHERE route_id = :route_id AND date = :date AND status = 'WAITING' ORDER BY id ASC LIMIT 1",nativeQuery = true)
    Ticket findLatestUser(@Param("route_id") int route_id, @Param("date") String date);
}
