package com.jsfcourse.userInfo;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.FilterMeta;

import java.io.Serializable;
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
@ViewScoped
public class UserInfoBB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_PERSON_EDIT = "PersonEditAdmin?faces-redirect=true";

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
	
	int a;
	int klienta;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
	
	Map<String,Object> searchParams = new HashMap<String, Object>();
	
	private LazyDataModel<Klient> lazyModelKlient;
	
	List<Klient> list = null;

    private String nazwisko;
    
	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	public LazyDataModel<Klient> getLazyModelKlient() {
		return lazyModelKlient;
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

@PostConstruct

public void init() {
	
	if (nazwisko != null && nazwisko.length() > 0){
		searchParams.put("nazwisko", nazwisko);
	}
	else {
		searchParams.clear();
	}
	
	this.lazyModelKlient = new LazyDataModel<Klient>( ) {
		private static final long serialVersionUID = 1L;
		
		@Override
		
		public List<Klient> load(int first, int pageSize, Map<String, SortMeta> sort, Map<String, FilterMeta> filter) {
			setRowCount(klientDAO.getFull(searchParams));
			
			list = klientDAO.getList(first, pageSize, searchParams);
			
			if (list == null) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Brak wyników", null));				
			}
						
			return list;
		}
	};
}
}
