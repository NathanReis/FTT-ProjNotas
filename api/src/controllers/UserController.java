package controllers;

import models.UserModel;
import repositories.UserRepository;

public class UserController extends Controller<UserModel> {
	public UserController() {
		super (new UserRepository());
	}	
}
