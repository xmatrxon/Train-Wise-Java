package jsf.course.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import jsf.course.enities.Czlonkostwo;
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
	
	
	public List<Czlonkostwo> getFullList() {
		 List<Czlonkostwo> list = null;
		Query query = em.createQuery("SELECT c FROM Czlonkostwo c");
		
		try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
	}
	
	public Czlonkostwo getCzlonkostwo(Object klient) {
		return em.find(Czlonkostwo.class, klient);
	}
		
	}
