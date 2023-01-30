package jsf.course.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.course.enities.Karnet;
import jsf.course.dao.KarnetDAO;


@Stateless
public class KarnetDAO {
	@PersistenceContext
	EntityManager em;
	
	public Karnet get(Object id) {
		return em.find(Karnet.class, id);
	}
	
	public Karnet getFullKarnet() {
		Query query = em.createQuery("SELECT k FROM Karnet k");
		try {
            return (Karnet) query.getResultList().get(0);
        } catch (Exception e) {    }

        return null;
    }
		
	}
