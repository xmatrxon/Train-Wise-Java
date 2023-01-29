package com.jsfcourse.userInfo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import jsf.course.dao.CzlonkostwoDAO;
import jsf.course.dao.KlientDAO;
import jsf.course.dao.KarnetDAO;
import jsf.course.enities.Czlonkostwo;
import jsf.course.enities.Klient;
import jsf.course.enities.Karnet;


@Named
@RequestScoped
public class UserInfoBB {
	
	private static final String PAGE_PERSON_EDIT = "PersonEditAdmin?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = "/pages/PersonList.xhtml";
	private static final String MAIN_PAGE = "/pages/index.xhtml";

	@EJB
	CzlonkostwoDAO czlonkostwoDAO;
	@EJB
	KlientDAO klientDAO;
	@EJB
	KarnetDAO karnetDAO;
	
	@Inject
	Flash flash;
	
	@Inject
	FacesContext context;
	
	private Czlonkostwo czlonkostwo;
	int a;
	int klienta;
	
	FacesContext ctx = FacesContext.getCurrentInstance();

    private String nazwisko;
    
	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
    

public Czlonkostwo getCzlonkostwo() {

    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer klient = (Integer) session.getAttribute("id");
    Czlonkostwo czlonkostwo = czlonkostwoDAO.getFullList(klient);
     
    if (czlonkostwo == null) {
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Brak wyników", null));
    }
    

    if(czlonkostwo !=null) {
        Date karnetData = czlonkostwo.getData_zakonczenia();
        Date currentDate = new java.util.Date();
        if (karnetData.compareTo(currentDate) < 0) {
        	czlonkostwoDAO.delete(czlonkostwo);
        }    	
    }
    
    return czlonkostwo;
}

public Karnet getKarnet() {
	
	Karnet karnet = karnetDAO.getFullKarnet();	
	return karnet;
}

public List<Klient> getList(){
	List<Klient> list = null;
	
	Map<String,Object> searchParams = new HashMap<String, Object>();
	
	if (nazwisko != null && nazwisko.length() > 0){
		searchParams.put("nazwisko", nazwisko);
	}
	
	list = klientDAO.getList(searchParams);
	
	return list;
}

public List<Czlonkostwo> getLista() {
	
	return czlonkostwoDAO.getLista();
	
}

public String editPerson(Klient klientt) {
	
      HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true) ;
      session.setAttribute("klientt",klientt);

	return PAGE_PERSON_EDIT;
}

public String deactivePerson(Klient klient) {
	
	klient.setAktywny((byte) 0);
	klientDAO.update(klient);
	
	return null;
}

public String activatePerson(Klient klient) {
	
	klient.setAktywny((byte) 1);
	klientDAO.update(klient);
	
	return null;
}

	
}
