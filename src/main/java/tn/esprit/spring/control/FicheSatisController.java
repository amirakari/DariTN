package tn.esprit.spring.control;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Fiche;
import tn.esprit.spring.entity.Question2;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.FicheService;
@Scope(value = "session") 
@Controller(value = "FicheSatisController") 
@ELBeanName(value = "FicheSatisController") 
@Join(path = "/a", to = "/login.jsf")
public class FicheSatisController {
	private String question3; private String question4; private String question5;
	private boolean question1; private Question2 question2;private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String question6; 
	private String question7;
	private String question8;
	private String proposition;
	public Question2[] getQuestions2() { return Question2.values(); }
	
	public Question2 getQuestion2() {
		return question2;
	}

	public FicheService getFicheservice() {
		return ficheservice;
	}
	public void setFicheservice(FicheService ficheservice) {
		this.ficheservice = ficheservice;
	}
	public UserController getUsercontroller() {
		return usercontroller;
	}
	public void setUsercontroller(UserController usercontroller) {
		this.usercontroller = usercontroller;
	}

	@Autowired
	FicheService ficheservice;
	@Autowired
	UserController usercontroller;
	private Boolean loggedIn;
	
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	private Long reclamationIdToBeUpdated;
	
	public Long getReclamationIdToBeUpdated() {
		return reclamationIdToBeUpdated;
	}

	public void setReclamationIdToBeUpdated(Long reclamationIdToBeUpdated) {
		this.reclamationIdToBeUpdated = reclamationIdToBeUpdated;
	}

	public void displayUser(Fiche rec)
	{
	this.setQuestion1(rec.getQuestion1());
	this.setQuestion2(rec.getQuetion2());
	this.setQuestion3(rec.getQuestion3());
	this.setQuestion4(rec.getQuestion4());
	this.setQuestion5(rec.getQuestion5());
	this.setQuestion6(rec.getQuestion6());
	this.setQuestion7(rec.getQuestion7());
	this.setQuestion8(rec.getQuestion8());
	this.setProposition(rec.getProposition());
	this.setUser(rec.getUser());
	this.setReclamationIdToBeUpdated(rec.getId());
	}
	public String modifyUser() {
		String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ){ return usercontroller.gotopagelog();}else{
		ficheservice.addFiche(new Fiche(reclamationIdToBeUpdated,question1,question2,question3,question4,question5
				,question6,question7,question8,proposition,user));
	}return navigateTo;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String addFiche() {
		String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ) {return usercontroller.gotopagelog();}else{
		ficheservice.addFiche(new Fiche(question1, question2, question3, question4, question5, question6,question7,question8,proposition,currentuser));}
		return navigateTo; 
	}
public int getNombretrueJPQL() {
		
		return ficheservice.getNombretrueJPQL();
	}
public int getNombrefalseJPQL() {
	
	return ficheservice.getNombrefalseJPQL();
}
public int getNombreamiJPQL() {
	
	return ficheservice.getNombreamiJPQL();
}
public int getNombrefamilleJPQL() {
	
	return ficheservice.getNombrefamilleJPQL();
}
public int getNombresitewebJPQL() {
	
	return ficheservice.getNombresitewebJPQL();
}
public int getNombrefacebookJPQL() {
	
	return ficheservice.getNombrefacebookJPQL();
}
	public String getQuestion3() {
		return question3;
	}
	public void setQuestion3(String question3) {
		this.question3 = question3;
	}
	public String getQuestion4() {
		return question4;
	}
	public void setQuestion4(String question4) {
		this.question4 = question4;
	}
	public String getQuestion5() {
		return question5;
	}
	public void setQuestion5(String question5) {
		this.question5 = question5;
	}
	public boolean getQuestion1() {
		return question1;
	}
	public void setQuestion1(boolean question1) {
		this.question1 = question1;
	}
	
	public void setQuestion2(Question2 question2) {
		this.question2 = question2;
	}
	public String getQuestion6() {
		return question6;
	}
	public void setQuestion6(String question6) {
		this.question6 = question6;
	}
	public String getQuestion7() {
		return question7;
	}
	public void setQuestion7(String question7) {
		this.question7 = question7;
	}
	public String getQuestion8() {
		return question8;
	}
	public void setQuestion8(String question8) {
		this.question8 = question8;
	}
	public String getProposition() {
		return proposition;
	}
	public void setProposition(String proposition) {
		this.proposition = proposition;
	}
	
	private List<Fiche> surveils;

	public List<Fiche> getSurveils() {
		User currentuser=usercontroller.getAuthenticatedUser();
		//System.out.println(currentuser);
		surveils= ficheservice.affichersurveil(currentuser);
		//System.out.println(surveillances);
		return surveils;
	}
	public void setSurveils(List<Fiche> surveils) {
		this.surveils = surveils;
	}
	public void removeSurv(String surveillanceId)
	{
		
		ficheservice.deleteSurv(surveillanceId);
		 
	}
}
