package com.jsfcourse.userEdit;

import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;

import jsf.course.dao.KlientDAO;
import jsf.course.enities.Klient;

@FacesConfig
@Named
@ViewScoped
public class UserEditBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	KlientDAO klientDAO;
	
	@Inject
	FacesContext context;
	
	private static final String MAIN_PAGE = "/pages/index.xhtml";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_PERSON_EDIT = "/pages/PersonEditAdmin.xhtml";

	private Klient klient = new Klient();
	private Klient loaded = null;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
	
	public Klient getKlient() {
		return klient;
	}
	

	public void onLoad() throws IOException {
		
	      HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true) ;
	      loaded = (Klient) session.getAttribute("klientt");
		
		if (loaded != null) {
			klient = loaded;
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}
	
	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
				klientDAO.update(klient);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return MAIN_PAGE;
	}
	
}
