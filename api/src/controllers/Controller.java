package controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Controller {
	protected EntityManagerFactory _emf;
	protected EntityManager _em;
	
	public Controller() {
		_emf = Persistence.createEntityManagerFactory("projnotas");
		_em = _emf.createEntityManager();
	}
}