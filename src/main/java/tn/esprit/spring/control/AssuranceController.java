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
import tn.esprit.spring.entity.Entreprise;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.Surveillance;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.AssuranceService;
import tn.esprit.spring.service.StripeService;

@Scope(value = "session")
@Controller(value = "assuranceController")
@ELBeanName(value = "assuranceController")
@Join(path= "/l3", to = "/logsubs.jsf")
public class AssuranceController {
	
	
	@Autowired
	AssuranceService assuranceservice;
	private Long id; private String code; private Assurance assurance; private String etat;private Assurance ascon;private float prix;
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Assurance getAscon() {
		ascon=assuranceservice.authenticate(id, code);
		return ascon;
	}
	private Long reclamationIdToBeUpdated;
	public Long getReclamationIdToBeUpdated() {
		return reclamationIdToBeUpdated;
	}
	public void setReclamationIdToBeUpdated(Long reclamationIdToBeUpdated) {
		this.reclamationIdToBeUpdated = reclamationIdToBeUpdated;
	}
	public void displayUser(Assurance rec)
	{
	this.setCode(rec.getCode());
	this.setDatedeb(rec.getDateDebut());
	this.setDatefin(rec.getDateFin());
	this.setEntreprise(rec.getEntreprise());
	this.setEtat(rec.getEtat());
	
	this.setUser(rec.getUser());
	this.setReclamationIdToBeUpdated(rec.getId());
	}
	public String modifyUser() {
		String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ){ return usercontroller.gotopagelog();}else{
		assuranceservice.addOrUpdateAssurance(new Assurance(reclamationIdToBeUpdated,datedeb,datefin,code,prix,entreprise,user));
		}return navigateTo;
		}
	public void setAscon(Assurance ascon) {
		this.ascon = ascon;
	}
	public String getEtat() {
		etat=ascon.getEtat();
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	private Boolean loggedIn;
	private Date datedeb;
	private Date datefin;
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
	@Autowired
	UserController usercontroller;
	
	public UserController getUsercontroller() {
		return usercontroller;
	}
	public void setUsercontroller(UserController usercontroller) {
		this.usercontroller = usercontroller;
	}
	private User user;
	private Entreprise entreprise;
	public Entreprise[] getEntreprises() { return Entreprise.values(); }
public String addAssurance() {
	String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null  ){ return usercontroller.gotopagelog();}else{
		assuranceservice.addOrUpdateAssurance(new Assurance( datedeb, datefin,code,entreprise,currentuser));}
		return navigateTo; 
		}
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

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
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
	
	
	public AssuranceService getAssuranceservice() {
		return assuranceservice;
	}
	public void setAssuranceservice(AssuranceService assuranceservice) {
		this.assuranceservice = assuranceservice;
	}
	public Assurance getAssurance() {
		return assurance;
	}
	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	public String doLogin() {
		String navigateTo= "null";
		 ascon=assuranceservice.authenticate(id, code);
		 System.out.println(ascon.getEtat());
		if(ascon!= null && ascon.getPrix() == null ) {
		navigateTo= "/pages/user/assurance.xhtml?faces-redirect=true";
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
		return"/logsubs.xhtml?faces-redirect=true";
		}
		public String modifyPrix() {
			String navigateTo = "null";
			User currentuser=usercontroller.getAuthenticatedUser();
			if (currentuser==null ) return usercontroller.gotopagelog();
			assuranceservice.updatePrix();
			return navigateTo; 
			
			}
		public String creerAbSurveillance(){
			return "/pages/user/creerAbAssurance.xhtml?faces-redirect=true";
		}
		public String afabonnement(){
			return "/pages/user/afassurance.xhtml?faces-redirect=true";
		}
		private List<Assurance> surveils;

		public List<Assurance> getSurveils() {
			User currentuser=usercontroller.getAuthenticatedUser();
			//System.out.println(currentuser);
			surveils= assuranceservice.affichersurveil(currentuser);
			//System.out.println(surveillances);
			return surveils;
		}
		public void setSurveils(List<Assurance> surveils) {
			this.surveils = surveils;
		}
		public void removeSurv(String surveillanceId)
		{
			
			assuranceservice.deleteSurv(surveillanceId);
		}
		@Autowired
		StripeService stripeService;
		
		public StripeService getStripeService() {
			return stripeService;
		}
		public void setStripeService(StripeService stripeService) {
			this.stripeService = stripeService;
		}
		public void Pay(int idc,String carta, int expMonth,int expYear,String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{
			
			
			
			if(stripeService.PayAssurance(idc,carta,expMonth,expYear,cvc)==true){
				FacesMessage facesMessage =

						new FacesMessage("payment made ");

				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			}else{
				FacesMessage facesMessage =

						new FacesMessage("please check your bank details and try again.");

				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			}
			
}
		public String gotoFiche(){
			return "/pages/user/publicite.xhtml?faces-redirect=true";
		}
}

