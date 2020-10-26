package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract public class Connection {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	public static EntityManager getInstancie() {
		if(em == null) {
			emf = Persistence.createEntityManagerFactory("projnotas");
			em = emf.createEntityManager();
		}
		
		return em;
	}
	
	public static void closeInstancie() {
		if(em != null) {
			em.close();
			emf.close();
			
			em = null;
			emf = null;
		}
	}
}