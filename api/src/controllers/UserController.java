package controllers;

import java.util.List;

import models.UserModel;
import repositories.ConexaoBd;
import repositories.UserRepository;

public class UserController extends Controller<UserModel> {
	
	public UserController(){
		super (new UserRepository());
	}
	
}