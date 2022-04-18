package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

public interface UserDetailsService {
	User loadUserByEmail(String email)  ;
}
