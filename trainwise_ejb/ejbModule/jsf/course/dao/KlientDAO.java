package jsf.course.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	public Klient checkIfEgsists(String login, String pesel, String nrTel) {
		Query query = em.createQuery("SELECT u FROM Klient u WHERE u.login like :login OR u.pesel LIKE :pesel OR u.nrTel LIKE :nrTel");
		query.setParameter("login", login);
		query.setParameter("pesel", pesel);
		query.setParameter("nrTel", nrTel);
		
		try {
			return (Klient) query.getResultList().get(0);
		} catch (Exception e) {	}
		
		return null;
	}
	
	public List<Klient> getList(int first, int pageSize, Map<String, Object> searchParams) {
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
		
		query.setFirstResult(first);
		query.setMaxResults(pageSize);

		try {
			listt = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listt;
	}
	
	public Integer getFull(Map<String, Object> searchParams) {
		
		String nazwisko = (String) searchParams.get("nazwisko");
		
		String select = "select u ";
		String from = "from Klient u ";
		String where = "";
		
		if (nazwisko != null) {
			where += "where nazwisko like :nazwisko ";
		}
			
		Query query = em.createQuery(select + from + where);
		
		if (nazwisko != null) {
			query.setParameter("nazwisko", nazwisko+"%");
		}
       
        try {
            return (Integer) query.getResultList().size();
        } catch (Exception e) {    }

        return null;
	}
	
	public Klient getClientInfo(Object klient) {
		return em.find(Klient.class, klient);
	}
}
