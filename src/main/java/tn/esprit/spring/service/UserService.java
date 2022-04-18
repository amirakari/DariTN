package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

public interface UserService {
	User findByUsernameOrEmail(String usernameOrEmail);
	
}
