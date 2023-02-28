package nrifintech.busMangementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nrifintech.busMangementSystem.entities.User;


public interface UserRepo extends  JpaRepository<User, Integer>{
	@Query(value = "SELECT * FROM user WHERE email = ?1 AND type = ?2", nativeQuery = true)
	User findByEmail(String email, int type);
}
