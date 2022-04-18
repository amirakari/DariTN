package tn.esprit.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.control.AffichageController;
import tn.esprit.spring.control.SurveillanceController;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.EmailService;
import tn.esprit.spring.service.FicheService;
import tn.esprit.spring.service.SurveillanceService;
import tn.esprit.spring.service.UserSerivce;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DariApplicationTests {
	@Autowired
	UserSerivce us;
	@Autowired
	SurveillanceService survser;
	@Autowired
	SurveillanceController cu;
	@Autowired
	AffichageController af;
	@Autowired
	EmailService email;
	@Autowired
	FicheService ficheservice;
	
	@Test
	public void contextLoads() {
		//us.retrieveAllUsers();
		//System.out.println(survser.affichersurveilconnecté((long) 1));;
		//us.addUser(new User("aaa","zzzz",(long) 22));
		//Surveillance sur=new Surveillance(new Date(),new Date(),"aaaa",(long) 2);
	//	survser.addOrUpdateSurveillance(sur);
		//System.out.println(cu.getAuthenticatedSurveillance()) ;
		//System.out.println(af.getSurveillanceconnecté());
		email.sendMail();
		//System.out.println(ficheservice.getNombrefacebookJPQL());
		//User u1=new User(1,"amir","mornaguia",12);
		
		//survser.affichersurveil(u1.setId((long) 1));
	}
	
}
