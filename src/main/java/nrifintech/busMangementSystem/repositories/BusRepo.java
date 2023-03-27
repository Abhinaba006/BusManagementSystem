package nrifintech.busMangementSystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.User;


public interface BusRepo extends  JpaRepository<Bus, Integer>{

	@Query(value = "SELECT * FROM bus WHERE id NOT IN (SELECT bus_id FROM bus_map)", nativeQuery = true)
	List<Bus> findUnallotedBus();
	
	@Query(value = "SELECT * FROM bus WHERE bus_number = ?1", nativeQuery = true)
	Optional<Bus> findByBusNumber(String bus_number);

}
