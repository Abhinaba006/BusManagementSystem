package nrifintech.busMangementSystem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT * FROM ticket WHERE user_id = :user_id order by id desc", nativeQuery = true)
    List<Ticket> findByUserId(@Param("user_id") int user_id);
    
    @Query(value = "SELECT * FROM ticket WHERE route_id = :route_id AND date = :date AND status = 'WAITING' ORDER BY id ASC LIMIT 1",nativeQuery = true)
    Ticket findLatestUser(@Param("route_id") int route_id, @Param("date") String date);
    
    @Query(value = "SELECT * FROM ticket WHERE user_id = :user_id AND date = :date AND status = 'CONFIRMED' LIMIT 1",nativeQuery = true)
    Ticket findUserByPresentDate(@Param("user_id") int user_id, @Param("date") String date);

    @Query(value = "SELECT * FROM ticket WHERE date < :date",nativeQuery = true)
	List<Ticket> findPastTickets(String date);
    
    //get total ticket done by user u.
    @Query(value="SELECT COUNT(*) FROM ticket where user_id = :user_id and status='AVAILED'",nativeQuery = true)
    Integer getTotalTicketsDoneByUser(int user_id);
    
    //delete all tickets with given route_id.
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ticket WHERE route_id = :route_id and date>=:currentDate", nativeQuery = true)
    void DeleteUpcomingTicketsByRouteId(int route_id,String currentDate);

    
    @Query(value = "SELECT COUNT(DISTINCT user_id) AS unique_users, "
            + "MONTH(STR_TO_DATE(date, '%d:%m:%Y')) AS month_number "
            + "FROM ticket "
            + "GROUP BY month_number", nativeQuery = true)
List<Integer> countUniqueUsersByMonth();

    @Query(value="SELECT COUNT(*) FROM ticket where date = :date and status=:status",nativeQuery = true)
	Integer getCountOfTodaysTicketsByStatus(String date,String status);

    @Query(value="SELECT COUNT(*) FROM ticket where date <= :date and status=:status",nativeQuery = true)
    Integer getCountOfPastTicketsByStatus(String date, String status);



    @Query(value = "SELECT * FROM ticket WHERE user_id = :user_id order by id desc", nativeQuery = true)
    Page<Ticket> findByUserId(@Param("user_id") int userId, Pageable pageable);

	

}
