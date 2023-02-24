package nrifintech.busMangementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nrifintech.busMangementSystem.entities.User;


public interface UserRepo extends  JpaRepository<User, Integer>{

}
