package com.jsfcourse.login;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import jsf.course.dao.KlientDAO;
import jsf.course.enities.Klient;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_LOGIN = "/pages/LoginView.xhtml?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String MAIN_PAGE = "/pages/UserInfoView.xhtml";

	private String login;
	private String haslo;
	private String idKlienta;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	
	public String getId_klienta() {
		return idKlienta;
	}

	public void setId_klienta(String idKlienta) {
		this.idKlienta = idKlienta;
	}
	
	
	@EJB
	KlientDAO klientDAO;
	

	public String doLogin(){
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		Klient klient = klientDAO.getUserFromDatabase(login, haslo);

		// 2. if bad login or password - stay with error info
		if (klient == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		

		// 3. if logged in: get User roles, save in RemoteClient and store it in session
		
		RemoteClient<Klient> client = new RemoteClient<Klient>(); //create new RemoteClient
		client.setDetails(klient);

		client.getRoles().add(klient.getRola());
		
		//store RemoteClient with request info in session (needed for SecurityFilter)
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		
	      HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
	      session.setAttribute("id",klient.getIdKlienta());
	      
		// and enter the system (now SecurityFilter will pass the request)
		return MAIN_PAGE;
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return PAGE_LOGIN;
	}
}