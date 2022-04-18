package tn.esprit.spring.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Objet;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.EmailService;
import tn.esprit.spring.service.ReclamationServiceImpl;

@Named
@RequestScoped
@Scope(value = "session")
@Controller(value = "reclamationController")
@ELBeanName(value = "reclamationController")
@Join(path= "/l2", to = "/surveillancelog.jsf")
public class ReclamationController {
	@Autowired
	ReclamationServiceImpl recService;
	@Autowired
	UserController usercontroller;
	@Autowired
	EmailService em;
	private User user;
	private Date date;
	private Objet objet;
	private String description;
	private String fichier;
	Reclamation rec;
	private Long reclamationIdToBeUpdated;
	public void displayUser(Reclamation rec)
	{
	this.setDate(rec.getDate());
	this.setDescription(rec.getDescription());
	this.setFichier(rec.getFichier());
	this.setObjet(rec.getObjet());
	this.setUser(rec.getUser());
	this.setReclamationIdToBeUpdated(rec.getId());
	}
	public String modifyUser() {
		String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ){ return usercontroller.gotopagelog();}else{
		recService.updateReclamation(new Reclamation(reclamationIdToBeUpdated,date,objet,description,fichier,user));
		}return navigateTo;
		}
	public Long getReclamationIdToBeUpdated() {
		return reclamationIdToBeUpdated;
	}
	public void setReclamationIdToBeUpdated(Long reclamationIdToBeUpdated) {
		this.reclamationIdToBeUpdated = reclamationIdToBeUpdated;
	}
	public ReclamationServiceImpl getRecService() {
		return recService;
	}
	public void setRecService(ReclamationServiceImpl recService) {
		this.recService = recService;
	}
	public UserController getUsercontroller() {
		return usercontroller;
	}
	public void setUsercontroller(UserController usercontroller) {
		this.usercontroller = usercontroller;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private UploadedFile file;
    
	 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
 public Objet[] getObjets() { return Objet.values(); }
 
    public void upload() {
    	
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        else{
        	System.out.println("file is null");}
    }
     
   
        
    
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	public Objet getObjet() {
		return objet;
	}
	public void setObjet(Objet objet) {
		this.objet = objet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFichier() {
		return fichier;
	}
	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	
	private String destination = "C:\\Users\\USER\\Documents\\workspace-sts-3.8.4.RELEASE\\dari\\src\\main\\resources\\META-INF\\resources\\upload\\";
	
	public void TransferTile (String fileName,InputStream in){
		 try{
			OutputStream out=new FileOutputStream(new File(destination +fileName));
			int reader=0;
			byte[] bytes=new byte[(int) file.getSize()];
			while((reader= in.read(bytes))!= -1){
				out.write(bytes,0,reader);
			}
			in.close();
			out.flush();
			out.close();
		   }catch (IOException e){
			System.out.println(e.getMessage());
		}}
	private Boolean loggedIn;
	
	public EmailService getEm() {
		return em;
	}
	public void setEm(EmailService em) {
		this.em = em;
	}
	public Reclamation getRec() {
		return rec;
	}
	public void setRec(Reclamation rec) {
		this.rec = rec;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String addReclamation() {
		String navigateTo = "null";
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ) {return usercontroller.gotopagelog();}else{
		
		try {
			
			upload();
			System.out.println("AAA"+file.getFileName());
			TransferTile(file.getFileName(),file.getInputStream());
			rec.setFichier(file.getFileName());}
		 catch(Exception e){
		}
		
		recService.addReclamation(new Reclamation(objet, description,currentuser));}
		return navigateTo; 
		}
	public String reponse() {
		String navigateTo = "null"; 
		User currentuser=usercontroller.getAuthenticatedUser();
		if (currentuser==null ) {return usercontroller.gotopagelog();}
		else{
		em.sendMail();}
		return navigateTo; 
	}
	private List<Reclamation> surveils;

	public List<Reclamation> getSurveils() {
		User currentuser=usercontroller.getAuthenticatedUser();
		//System.out.println(currentuser);
		surveils= recService.affichersurveil(currentuser);
		//System.out.println(surveillances);
		return surveils;
	}
	public void setSurveils(List<Reclamation> surveils) {
		this.surveils = surveils;
	}
	public void removeSurv(String surveillanceId)
	{
		
		recService.deleteSurv(surveillanceId);
		
	}
}
