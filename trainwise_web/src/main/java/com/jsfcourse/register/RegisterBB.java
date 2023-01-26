package com.jsfcourse.register;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jsf.course.dao.KlientDAO;
import jsf.course.enities.Klient;

@FacesConfig
@Named
@ViewScoped
public class RegisterBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String MAIN_PAGE = "/pages/index.xhtml";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Klient klient = new Klient();
	
	private String imie;
	private String nazwisko;
	private String nrTel;
	private String pesel;
	private String login;
	private String haslo;
	private String idKlienta;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer id_klient = (Integer) session.getAttribute("id");
	
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	public String getNrTel() {
		return nrTel;
	}

	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}
	
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
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
	
	public String getIdKlienta() {
		return idKlienta;
	}

	public void setIdKlienta(String idKlienta) {
		this.idKlienta = idKlienta;
	}
	
	@Inject
	KlientDAO klientDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	public Klient getKlient() {
		return klient;
	}
	
	public void onLoad() throws IOException {
		
		klient = klientDAO.getClientInfo(id_klient);

		
		if (klient != null) {
			System.out.println("pobrano");
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			System.out.println("nie-pobrano");
		}

	}
	
	public String saveData() {
		if (klient == null) {
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

	public String doRegister() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		
		Klient klient = new Klient();
		
		klient.setImie(imie);
		klient.setNazwisko(nazwisko);
		klient.setNrTel(nrTel);
		klient.setPesel(pesel);
		klient.setLogin(login);
		klient.setHaslo(haslo);
		klient.setRola("user");
		klient.setAktywny((byte) 1);
		
		klientDAO.insert(klient);
		
		return MAIN_PAGE;
	}
	

	
	public String updateUser() {
	Klient klient = new Klient();
	
	klientDAO.update(klient);
		
		return MAIN_PAGE;
	}
	
}
