package tn.esprit.spring.service;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserSerivce {
@Autowired
UserRepository userRepository;

private static final Logger L=LogManager.getLogger(UserServiceImpl.class);

@Override
public List<User>retrieveAllUsers(){
	return (List<User>) userRepository.findAll();
	
}
@Override
public Long addUser(User u){
	 userRepository.save(u);
	 return u.getId();
	
}
@Override
public User updateUser(User u){
	return userRepository.save(u);
	
}

@Override
public void deleteUser(String id) {
	userRepository.deleteById(Long.parseLong(id));
	
}
@Override
public User retrieveUser(String id) {
	User u;
	u=userRepository.findById(Long.parseLong(id)).orElse(null);
	return u;
	
}
@Override
public User authenticate(String login, String password) {
return userRepository.getUserByEmailAndPassword(login, password);
}
}

