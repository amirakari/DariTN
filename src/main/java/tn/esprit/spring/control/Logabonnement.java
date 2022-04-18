package tn.esprit.spring.control;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Assurance;
import tn.esprit.spring.service.AssuranceService;
@Scope(value = "session")
@Controller(value = "logabonnement")
@ELBeanName(value = "logabonnement")
@Join(path= "/l2", to = "/surveillancelog.jsf")
public class Logabonnement {
	@Autowired
	AssuranceController assurancecontroller;
	private Boolean loggedIn;
	
	public AssuranceController getAssurancecontroller() {
		return assurancecontroller;
	}
	public void setAssurancecontroller(AssuranceController assurancecontroller) {
		this.assurancecontroller = assurancecontroller;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Assurance getAssurance() {
		return assurance;
	}
	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Assurance getAscon() {
		return ascon;
	}
	public void setAscon(Assurance ascon) {
		this.ascon = ascon;
	}
	private Long id; private String code; private Assurance assurance; private String etat;private Assurance ascon;
	public String doLogin() {
		String navigateTo= "null";
		 
		 
		
		if(assurancecontroller.getAscon()!= null && assurancecontroller.getEtat()=="paid") {
		navigateTo= "/pages/user/assurance.xhtml?faces-redirect=true";
		loggedIn= true; }
		else{
		FacesMessage facesMessage=
		new FacesMessage("Login Failed: please check your id/code and try again.");
		FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
		}
}
