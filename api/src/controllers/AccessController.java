package controllers;

import models.AccessModel;
import models.UserModel;
import repositories.ConnectionDB;
import repositories.AccessRepository;

public class AccessController {
	protected AccessRepository repository = new AccessRepository();
	
	public UserModel login(AccessModel accessModel) {
		UserModel userModel = null;
		
		try {
			userModel = this.repository.login(accessModel);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return userModel;
	}
}
