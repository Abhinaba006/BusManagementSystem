package nrifintech.busMangementSystem.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.UserRepo;
import nrifintech.busMangementSystem.Service.interfaces.*;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User newUser, int id) {
		// TODO Auto-generated method stub
		User  user = userRepo.findById(id).orElseThrow(() -> new ResouceNotFound("User", "id", id));
		if(newUser.getName()!=null) user.setName(newUser.getName());
		if(newUser.getEmail()!=null) user.setEmail(newUser.getEmail());
		if(newUser.getPassword()!=null) user.setPassword(newUser.getPassword());
		
		return this.userRepo.save(user);
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		 return this.userRepo.findById(id).orElseThrow(() -> new ResouceNotFound("User", "id", id));
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		return  this.userRepo.findAll();
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResouceNotFound("User", "id", id));
		userRepo.delete(user);	
	}
	
}
