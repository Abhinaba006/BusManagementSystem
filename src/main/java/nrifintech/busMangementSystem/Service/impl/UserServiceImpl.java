package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.exception.UnauthorizedAction;
import nrifintech.busMangementSystem.payloads.UserDto;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	public ModelMapper modelMapper;
	

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		//same user can't be created multiple times for each role type.
		System.out.println(userRepo.findByOnlyEmail(user.getEmail()));
		if(userRepo.findByOnlyEmail(user.getEmail()).isEmpty())
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepo.save(user);
		}
		else 
			throw new UnauthorizedAction("similiar user created","Admin");
//		return userRepo.save(user);
	}

	@Override
	public User updateUser(User newUser, int id) {
		// TODO Auto-generated method stub
		User  user = userRepo.findById(id).orElseThrow(() -> new ResouceNotFound("User", "id", id));
		// if(userRepo.findByOnlyEmail(user.getEmail()) != null) throw new UnauthorizedAction("similiar user create","user");
		if(newUser.getName()!=null) user.setName(newUser.getName());
		if(newUser.getEmail()!=null) user.setEmail(newUser.getEmail());
		if(newUser.getPassword()!=null) user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		if(newUser.getEmployeeId()!=null) user.setEmployeeId(newUser.getEmployeeId());

//		if(newUser.getPassword()!=null) user.setPassword(newUser.getPassword());

		
		return this.userRepo.save(user);
	}

	@Override
	public UserDto getUser(int id) {
		// TODO Auto-generated method stub
		 User user =  this.userRepo.findById(id).orElseThrow(() -> new ResouceNotFound("User", "id", id));
		 return  modelMapper.map(user, UserDto.class);


	}
	
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		 return this.userRepo.findByEmail(email, 0).orElseThrow(() -> new ResouceNotFound("User", "email", -1));
	}
	
	@Override
	public User getAdminByEmail(String email) {
		// TODO Auto-generated method stub
		 return this.userRepo.findByEmail(email, 1).orElseThrow(() -> new ResouceNotFound("User", "email", -1));
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



	@Override
	public boolean checkUser(String email, String password) {

		User user = userRepo.findByOnlyEmail(email).orElseThrow(()->new ResouceNotFound("User","Email",0));
		
		return  passwordEncoder.matches(password, user.getPassword());
		
		
//		if(user.getPassword().equals(password)) return true;
//		else return false;
}

	@Override
	public boolean checkAdmin(String email, String password) {
		User admin = userRepo.findByEmail(email,1).orElseThrow(()->new ResouceNotFound("User","Email",0));
		return  passwordEncoder.matches(password, admin.getPassword());
//		if(admin.getPassword().equals(password)) return true;
//		else return false;
	}

}
