package jsf.course.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import jsf.course.enities.Czlonkostwo;
import jsf.course.enities.Klient;
import jsf.course.dao.CzlonkostwoDAO;


@Stateless
public class CzlonkostwoDAO {
	@PersistenceContext
	EntityManager em;
	
	public void insert(Czlonkostwo czlonkostwo) {
		em.persist(czlonkostwo);
	}
	
	public Czlonkostwo update(Czlonkostwo czlonkostwo) {
		return em.merge(czlonkostwo);
	}

	public void delete(Czlonkostwo czlonkostwo) {
		em.remove(em.merge(czlonkostwo));
	}
	
	public Czlonkostwo get(Object id) {
		return em.find(Czlonkostwo.class, id);
	}
	
	
	public Czlonkostwo getFullList(int klient) {
		Query query = em.createQuery("SELECT c FROM Czlonkostwo c WHERE klient.id = :klient");
		query.setParameter("klient", klient);
		try {
            return (Czlonkostwo) query.getResultList().get(0);
        } catch (Exception e) {    }

        return null;
    }
	
	public List<Czlonkostwo> getLista() {
		List<Czlonkostwo> lista = null;
		Query query = em.createQuery("SELECT c FROM Czlonkostwo c");
		
		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
//	public Czlonkostwo  getSth(Object klient) {
//		Query query = em.createQuery("SELECT u FROM Czlonkostwo u WHERE u.klient = :klient");
//		query.setParameter("klient", klient);
//
//		try {
//			return (Czlonkostwo) query.getResultList().get(0);
//		} catch (Exception e) {	}
//		
//		return null;
//	}
	
	public Czlonkostwo getCzlonkostwo(Object klient) {
		return em.find(Czlonkostwo.class, klient);
	}
		
	}
