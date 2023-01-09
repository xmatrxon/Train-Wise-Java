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
import jsf.course.enities.Czlonkostwo;
import jsf.course.enities.Klient;

@Named
@RequestScoped
public class UserInfoBB {

	@EJB
	CzlonkostwoDAO czlonkostwoDAO;
	

private List<Czlonkostwo> list = null;

public List<Czlonkostwo> getList() {

    FacesContext ctx = FacesContext.getCurrentInstance();

    list = czlonkostwoDAO.getFullList();

    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer id = (Integer) session.getAttribute("id");
    
//    Czlonkostwo czlonkostwo = czlonkostwoDAO.getCzlonkostwo(id);
//    
//    System.out.println(czlonkostwo.getKlient());
    
    
    
    if (list == null) {
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Brak wyników", null));
    }
    
    return list;


}
	
	
}
