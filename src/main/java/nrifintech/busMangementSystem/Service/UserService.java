package nrifintech.busMangementSystem.Service;

import java.util.List;

import nrifintech.busMangementSystem.entities.User;

public interface UserService {
	User createUser(User user);
	User updateUser(User user, int id);
	User getUser(int id);
	List<User> getUser();
	void deleteUser(int id);
}
