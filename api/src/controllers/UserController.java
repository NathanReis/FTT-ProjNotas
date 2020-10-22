package controllers;

import models.UserModel;

public class UserController extends Controller {
	public UserController() {
		super();
	}
	
	public void create(UserModel userModel) {
		_em.getTransaction().begin();
		
		try {
			_em.persist(userModel);
			
			_em.getTransaction().commit();
		} catch(Exception exception) {
			_em.getTransaction().rollback();
		}
		
		_emf.close();
	}
}