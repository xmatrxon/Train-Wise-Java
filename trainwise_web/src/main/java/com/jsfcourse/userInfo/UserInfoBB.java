package com.jsfcourse.userInfo;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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

	@EJB
	CzlonkostwoDAO czlonkostwoDAO;
	@EJB
	KlientDAO klientDAO;
	@EJB
	KarnetDAO karnetDAO;
	
	private Czlonkostwo czlonkostwo;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer klient = (Integer) session.getAttribute("id");
    


public Czlonkostwo getCzlonkostwo() {

    

    Czlonkostwo czlonkostwo = czlonkostwoDAO.getFullList(klient);

    
//    Czlonkostwo czlonkostwo = czlonkostwoDAO.getSth(klient);
//    
//    
//   
//    System.out.println(czlonkostwo.getID_czlonkostwa());
    
    
    
    if (czlonkostwo == null) {
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Brak wyników", null));
    }
    return czlonkostwo;


}

public Karnet getKarnet() {
	
	Karnet karnet = karnetDAO.getFullKarnet();	
	return karnet;
}


public List<Klient> getList() {
	
	return klientDAO.getList();
	
}

public List<Czlonkostwo> getLista() {
	
	return czlonkostwoDAO.getLista();
	
}
	
	
}
