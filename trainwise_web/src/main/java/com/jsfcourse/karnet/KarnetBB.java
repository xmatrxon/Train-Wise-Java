package com.jsfcourse.karnet;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import jsf.course.dao.CzlonkostwoDAO;
import jsf.course.dao.KarnetDAO;
import jsf.course.dao.KlientDAO;
import jsf.course.enities.Czlonkostwo;
import jsf.course.enities.Karnet;
import jsf.course.enities.Klient;



@Named
@RequestScoped
public class KarnetBB {

	@Inject
	CzlonkostwoDAO czlonkostwoDAO;
	
	@Inject
	KlientDAO klientDAO;
	
	@Inject
	KarnetDAO karnetDAO;

	
	FacesContext ctx = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer klientt = (Integer) session.getAttribute("id");
    
    private Klient klient = new Klient();


	public String buyKarnet(int a) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		klient = klientDAO.getClientInfo(klientt);
		Czlonkostwo czlonkostwo = new Czlonkostwo();
		
		Karnet karnet = karnetDAO.get(a);
		
		Date currentDate = new java.util.Date();
	
		czlonkostwo.setKarnet(karnet);
		czlonkostwo.setKlient(klient);
		czlonkostwo.setData_rozpoczecia(currentDate);
		czlonkostwo.setData_zakonczenia(Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		czlonkostwoDAO.update(czlonkostwo);
		
		return null;
	}
	
}
