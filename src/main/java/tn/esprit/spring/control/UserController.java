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

import tn.esprit.spring.entity.Type;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.UserSerivce;


@Scope(value = "session") 
@Controller(value = "userController") 
@ELBeanName(value = "userController") 
@Join(path = "/a", to = "/login.jsf")

public class UserController  {
	@Autowired
	UserSerivce userservice;
	private String prenom;
	private String nom;
	private String username;
	private Long cin;
	private Long tel ;
	private String email;
	private Long codepostal ;
	private String adresse;
	private String profession;
	private String relation;
	private User authenticatedUser;
	
	public User getAuthenticatedUser() {
		return authenticatedUser;
	}
	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}


	private Date datenaissance;
	private Type type;
	private List<User> users;
	private Long userIdToBeUpdated;
	public Type[] getTypes() { return Type.values(); }
	public void displayUser(User user)
	{
	this.setEmail(user.getEmail());
	this.setPassword(user.getPassword());
	this.setNom(user.getNom());
	this.setPrenom(user.getPrenom());
	this.setAdresse(user.getAdresse());
	this.setProfession(user.getProfession());
	this.setRelation(user.getRelation());
	this.setUsername(user.getUsername());
	this.setCin(user.getCin());
	this.setTel(user.getTel());
	this.setCodepostal(user.getCodepostal());
	this.setDatenaissance(user.getDatenaissance());
	this.setType(user.getType());
	this.setUserIdToBeUpdated(user.getId());
	}
	public List<User> getUser1() {
		users= userservice.retrieveAllUsers();
		return users;
	}
	public void modifyUser() {
		userservice.addUser(new User(userIdToBeUpdated,prenom,nom,username,cin,tel,email,codepostal,adresse,profession,relation,password,datenaissance,type));
	}
	public void addUser() {
		userservice.addUser(new User(prenom,nom,username,cin,tel,email,codepostal,adresse,profession,relation,password,datenaissance,type));
		
		}
	public void removeUser(String userId) {
		userservice.deleteUser(userId);
		}
	
	
	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public Long getCin() {
		return cin;
	}



	public void setCin(Long cin) {
		this.cin = cin;
	}



	public Long getTel() {
		return tel;
	}



	public void setTel(Long tel) {
		this.tel = tel;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getCodepostal() {
		return codepostal;
	}



	public void setCodepostal(Long codepostal) {
		this.codepostal = codepostal;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getProfession() {
		return profession;
	}



	public void setProfession(String profession) {
		this.profession = profession;
	}



	public String getRelation() {
		return relation;
	}



	public void setRelation(String relation) {
		this.relation = relation;
	}



	public Date getDatenaissance() {
		return datenaissance;
	}



	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public Long getUserIdToBeUpdated() {
		return userIdToBeUpdated;
	}



	public void setUserIdToBeUpdated(Long userIdToBeUpdated) {
		this.userIdToBeUpdated = userIdToBeUpdated;
	}



	public long ajouterUser(User user)
	{
		userservice.addUser(user);
		return user.getId();
	}
    

	
	public void deleteUserById(String userId) {
		userservice.deleteUser(userId);
		
	}


	public List<User> getAllUsers() {
		
		return userservice.retrieveAllUsers();
				}

	
	private String login;
	private String password;
	private User user;
	private Boolean loggedIn;
	
	 private boolean actif; 
	
	

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	

	

	public UserSerivce getUserservice() {
		return userservice;
	}

	public void setUserservice(UserSerivce userservice) {
		this.userservice = userservice;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	

	

	public String doLogin() {
		String navigateTo = "null";
		authenticatedUser= userservice.authenticate(login, password); 
		//User user=userservice.authenticate(login, password);
		if (authenticatedUser != null  && authenticatedUser.getType() == Type.Admin)
		{ 
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			loggedIn = true;
		}
		else if (authenticatedUser != null  && authenticatedUser.getType() == Type.SimpleUser)
		{ 
			navigateTo = "/pages/user/welcomeUser.xhtml?faces-redirect=true";
			loggedIn = true;
		}
		
		else {
			FacesMessage facesMessage =

					new FacesMessage("Login Failed: please check your username/password and try again.");

			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		}
		return navigateTo;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	public String gotopagelog(){
		return "/login.xhtml?faces-redirect=true";
	}
	public String gotoLog(){
		return "/pages/user/UserProfile.xhtml?faces-redirect=true";
	}
	public String gotoUserProfile(){
		return "/pages/user/UserProfile.xhtml?faces-redirect=true";
	}
	public String gotoRent(){
		return "/pages/user/salsabil.xhtml?faces-redirect=true";
	}
	public String gotoBuy(){
		return "/pages/user/eya.xhtml?faces-redirect=true";
	}
	public String gotoSubscribe(){
		return "/pages/user/Logsubs.xhtml?faces-redirect=true";
	}
	public String gotoPublication(){
		return "/pages/user/publicab_login.xhtml?faces-redirect=true";
	}
	public String gotoSurveillance(){
		return "/pages/user/surveillancelog.xhtml?faces-redirect=true";
	}
	public String gotoMeuble(){
		return "/pages/user/oumema.xhtml?faces-redirect=true";
	}
	public String gotoVirtualVisit(){
		return "/pages/user/chaima.xhtml?faces-redirect=true";
	}
	
	public String gotolistAdvertisement(){
		return "/pages/user/AnnoncesListing.xhtml?faces-redirect=true";
	}
	public String gotoHomepageUser(){
		return "/pages/user/welcomeUser.xhtml?faces-redirect=true";
	}
	
	public String creerCompte(){
		return "/pages/user/user_register.xhtml?faces-redirect=true";
	}
	public String gotoReclamation(){
		return "/pages/user/reclamation.xhtml?faces-redirect=true";
	}
	public String gotoFiche(){
		return "/pages/user/fiche.xhtml?faces-redirect=true";
	}
	public String gotoAff(){
		return "/pages/user/table.xhtml?faces-redirect=true";
	}
	public String gotoLogassurance(){
		return "/pages/user/assurance_login.xhtml?faces-redirect=true";
	}
	public String gotoStatistique(){
		return "/pages/admin/bar.xhtml?faces-redirect=true";
	}
}
