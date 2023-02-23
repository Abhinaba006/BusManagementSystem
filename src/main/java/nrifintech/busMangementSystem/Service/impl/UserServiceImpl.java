package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.UserService;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.UserRepo;

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
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		
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
