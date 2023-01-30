package com.jsfcourse.karnet;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import jsf.course.dao.CzlonkostwoDAO;
import jsf.course.dao.KarnetDAO;
import jsf.course.dao.KlientDAO;
import jsf.course.enities.Czlonkostwo;
import jsf.course.enities.Karnet;
import jsf.course.enities.Klient;



@Named
@RequestScoped
public class KarnetBB {
	
	private static final String PAGE_PERSON = "UserInfoView?faces-redirect=true";

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
		
		klient = klientDAO.getClientInfo(klientt);
		Czlonkostwo czlonkostwo = new Czlonkostwo();
		
		Karnet karnet = karnetDAO.get(a);
		
		Date currentDate = new java.util.Date();
	
		czlonkostwo.setKarnet(karnet);
		czlonkostwo.setKlient(klient);
		czlonkostwo.setData_rozpoczecia(currentDate);
		czlonkostwo.setData_zakonczenia(Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		czlonkostwoDAO.update(czlonkostwo);
		
		return PAGE_PERSON;
	}
	
}
