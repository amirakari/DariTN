 package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.service.*;
@Scope(value = "session")
@Controller(value = "userController1")
@ELBeanName(value = "userController1")
@Join(path= "/", to = "/pages/user/user_register")
public class UserRestController {
	@Autowired
	UserServiceImpl userService;
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
	private String password;
	private Date datenaissance;
	private Type type;
	private List<User> users;
	private Long userIdToBeUpdated;
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

	

	public Long getUserIdToBeUpdated() {
		return userIdToBeUpdated;
	}



	public void setUserIdToBeUpdated(Long userIdToBeUpdated) {
		this.userIdToBeUpdated = userIdToBeUpdated;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Type[] getTypes() { return Type.values(); }
	public UserServiceImpl getUserService() {
		return userService;
	}
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDatenaissance() {
		return datenaissance;
	}
	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	// http://localhost:8081/SpringMVC/servlet/retrieve-all-users
	@GetMapping("/retrieve-all-users")
	@ResponseBody
	public List<User> getUsers() {
	users= userService.retrieveAllUsers();
	return users;
}
	// http://localhost:8081/SpringMVC/servlet/retrieve-user/{user-id}
	@GetMapping("/retrieve-user/{user-id}")
	@ResponseBody
	public User retrieveUser(@PathVariable("user-id") String userId) {
	return userService.retrieveUser(userId);}
	
	// Ajouter User : http://localhost:8081/SpringMVC/servlet/add-user
	@PostMapping("/add-user")
	@ResponseBody
	public void addUser() {
	 userService.addUser(new User(prenom,nom,username,cin,tel,email,codepostal,adresse,profession,relation,password,datenaissance,type));
	
	}
	// http://localhost:8081/SpringMVC/servlet/remove-user/{user-id}
	@DeleteMapping("/remove-user/{user-id}")
	@ResponseBody
	public void removeUser(String userId) {
	userService.deleteUser(userId);
	}

	
	// http://localhost:8081/SpringMVC/servlet/modify-user
	@PutMapping("/modify-user")
	@ResponseBody
	public void modifyUser() {
		userService.addUser(new User(userIdToBeUpdated,prenom,nom,username,cin,tel,email,codepostal,adresse,profession,relation,password,datenaissance,type));
	}}
