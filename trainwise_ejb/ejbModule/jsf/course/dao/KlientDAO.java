package jsf.course.dao;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jsf.course.enities.Klient;
import jsf.course.dao.KlientDAO;

@Stateless
public class KlientDAO {
	@PersistenceContext
	EntityManager em;
	
	public void insert(Klient klient) {
		em.persist(klient);
	}
	
	public Klient update(Klient klient) {
		return em.merge(klient);
	}

	public void delete(Klient klient) {
		em.remove(em.merge(klient));
	}
	
	public Klient get(Object id) {
		return em.find(Klient.class, id);
	}
	
	public Klient getUserFromDatabase(String login, String haslo) {
		Query query = em.createQuery("SELECT u FROM Klient u WHERE u.login like :login AND u.haslo LIKE :haslo");
		query.setParameter("login", login);
		query.setParameter("haslo", haslo);
		
		try {
			return (Klient) query.getResultList().get(0);
		} catch (Exception e) {	}
		
		return null;
	}
	
	public List<Klient> getList(Map<String, Object> searchParams) {
		List<Klient> listt = null;

		String select = "select u ";
		String from = "from Klient u ";
		String where = "";
		String orderby = "order by u.nazwisko asc, u.imie";

		String nazwisko = (String) searchParams.get("nazwisko");
		if (nazwisko != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.nazwisko like :nazwisko ";
		}
	
		Query query = em.createQuery(select + from + where + orderby);

		if (nazwisko != null) {
			query.setParameter("nazwisko", nazwisko+"%");
		}

		try {
			listt = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listt;
	}
	
	public Klient getClientInfo(Object klient) {
		return em.find(Klient.class, klient);
	}
}
