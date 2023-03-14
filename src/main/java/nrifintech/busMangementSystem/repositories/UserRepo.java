package nrifintech.busMangementSystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.Query;


import nrifintech.busMangementSystem.entities.User;


public interface UserRepo extends  JpaRepository<User, Integer>{


	@Query(value = "SELECT * FROM user WHERE  email = ?1 and type=?2", nativeQuery = true) // restore it
	Optional<User> findByEmail(String email, int type);
	
	@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
	Optional<User> findByOnlyEmail(String email);

}
