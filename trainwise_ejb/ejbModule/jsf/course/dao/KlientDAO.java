package jsf.course.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
	
	
}
