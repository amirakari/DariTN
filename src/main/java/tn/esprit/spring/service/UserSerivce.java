package tn.esprit.spring.service;
import java.util.List;

import tn.esprit.spring.entity.User;
public interface UserSerivce {
	List<User> retrieveAllUsers();
	Long addUser(User u);
	void deleteUser(String id);
	User updateUser(User u);
	User retrieveUser(String id);
	public User authenticate(String login, String password) ;
}
