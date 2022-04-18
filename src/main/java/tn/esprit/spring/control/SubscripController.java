package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;

import tn.esprit.spring.entity.Assurance;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.Subscription;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.StripeService;
import tn.esprit.spring.service.SubscripService;

import tn.esprit.spring.service.UserSerivce;
import tn.esprit.spring.service.UserServiceImpl;

@Scope(value = "session")
@Controller(value = "subscriptController")
@ELBeanName(value = "subscriptController")
@Join(path= "/l1", to = "/publicab_login.jsf")
public class SubscripController {
	
	private Date datedeb;
	private Date datefin;
	
	private User user;private String etat;private float prix;
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Long getReclamationIdToBeUpdated() {
		return reclamationIdToBeUpdated;
	}
	public void setReclamationIdToBeUpdated(Long reclamationIdToBeUpdated) {
		this.reclamationIdToBeUpdated = reclamationIdToBeUpdated;
	}
	int idc;String carta; int expMonth;int expYear;String cvc;
	
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public int getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	private Long reclamationIdToBeUpdated;
	public void displayUser(Subscription rec)
	{
	this.setCode(rec.getCode());
	this.setDatedeb(rec.getDateDebut());
	this.setDatefin(rec.getDateFin());
	this.setEtat(rec.getEtat());
	
	this.setUser(rec.getUser());
	this.setReclamationIdToBeUpdated(rec.getId());
	}
	public String modifyUser() {
		String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ){ return usercontroller.gotopagelog();}else{
		subService.addOrUpdateSubscription(new Subscription(reclamationIdToBeUpdated,etat,datedeb,datefin,code,prix,user));
		}return navigateTo;
		}
	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserController usercontroller;
	@Autowired
	UserSerivce userservice;
	@Autowired
	SubscripService subService;
	private Long id; private String code; private Subscription subscription;
	private Boolean loggedIn;
	
	public Date getDatedeb() {
		return datedeb;
	}
	public void setDatedeb(Date datedeb) {
		this.datedeb = datedeb;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserServiceImpl getUserService() {
		return userService;
	}
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	public UserController getUsercontroller() {
		return usercontroller;
	}
	public void setUsercontroller(UserController usercontroller) {
		this.usercontroller = usercontroller;
	}
	public UserSerivce getUserservice() {
		return userservice;
	}
	public void setUserservice(UserSerivce userservice) {
		this.userservice = userservice;
	}
	public SubscripService getSubService() {
		return subService;
	}
	public void setSubService(SubscripService subService) {
		this.subService = subService;
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
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String doLogin() {
		String navigateTo= "null";
		
		
		Subscription subscription=subService.authenticate(id, code);
		if(subscription!= null && subscription.getPrix()==null) {
		navigateTo= "/pages/user/amir.xhtml?faces-redirect=true";
		loggedIn= true; }
		else{
		FacesMessage facesMessage=
		new FacesMessage("Login Failed: please check your id/code and try again.");
		FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;
		
		
		}
		public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return"/publicab_login.xhtml?faces-redirect=true";
		}
		public String addSubscription() {
			String navigateTo = "null"; 
			User currentuser=usercontroller.getAuthenticatedUser();
			if (currentuser==null ){ return usercontroller.gotopagelog();}else{
			subService.addOrUpdateSubscription(new Subscription( datedeb, datefin,code,currentuser));}
			return navigateTo; 
			}
		public String modifyPrix() {
			String navigateTo = "null"; 
			User currentuser=usercontroller.getAuthenticatedUser();
			if (currentuser==null ){ return usercontroller.gotopagelog();}else{
			subService.updatePrix();}
			return navigateTo; 
			}
		public String creerAbSurveillance(){
			return "/pages/user/creerAbPublication.xhtml?faces-redirect=true";
		}
		public String afabonnement(){
			return "/pages/user/affpub.xhtml?faces-redirect=true";
		}
		private List<Subscription> surveils;
		public List<Subscription> getSurveils() {
			User currentuser=usercontroller.getAuthenticatedUser();
			//System.out.println(currentuser);
			surveils= subService.affichersurveil(currentuser);
			//System.out.println(surveillances);
			return surveils;
		}
		public void setSurveils(List<Subscription> surveils) {
			this.surveils = surveils;
		}
		public void removeSurv(String surveillanceId)
		{
			
			subService.deleteSurv(surveillanceId);
		
		}
		@Autowired
		StripeService stripeService;
		
		public StripeService getStripeService() {
			return stripeService;
		}
		public void setStripeService(StripeService stripeService) {
			this.stripeService = stripeService;
		}
		public String Pay(int idc,String carta, int expMonth,int expYear,String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{
			 
			
			String navigateTo = "null"; 
			User currentuser=usercontroller.getAuthenticatedUser();
			if (currentuser==null ){ return usercontroller.gotopagelog();}else{
			
			if(stripeService.PayPublication(idc,carta,expMonth,expYear,cvc)==true){
				FacesMessage facesMessage =

						new FacesMessage("payment made ");

				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			}else{
				FacesMessage facesMessage =

						new FacesMessage("please check your bank details and try again.");

				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			}}
			return navigateTo; 
		}
}
