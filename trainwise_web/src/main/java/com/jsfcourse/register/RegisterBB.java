package com.jsfcourse.register;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import jsf.course.dao.KlientDAO;
import jsf.course.enities.Klient;

@Named
@RequestScoped
public class RegisterBB {
	
	private static final String MAIN_PAGE = "/pages/index.xhtml";
	
	private String imie;
	private String nazwisko;
	private String nrTel;
	private String pesel;
	private String login;
	private String haslo;
	private String idKlienta;
	
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
	
}
