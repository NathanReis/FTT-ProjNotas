package controllers;

import java.util.List;

import models.UserModel;
import repositories.Connection;
import repositories.UserRepository;

public class UserController {
	private UserRepository userRepository = new UserRepository();
	
	public UserModel create(UserModel userModel) {
		userModel = this.userRepository.save(userModel, 0);
		
		Connection.closeInstancie();
		
		return userModel;
	}
	
	public void delete(int id) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, "id", id);
		
		this.userRepository.delete(userModel);
		
		Connection.closeInstancie();
	}
	
	public List<UserModel> findAll() {
		List<UserModel> userModels = this.userRepository.findAll(UserModel.class);
		
		Connection.closeInstancie();
		
		return userModels;
	}
	
	public UserModel findFirst(String field, double value) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, field, value);
		
		Connection.closeInstancie();
		
		return userModel;
	}
	
	public UserModel findFirst(String field, int value) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, field, value);
		
		Connection.closeInstancie();
		
		return userModel;
	}
	
	public UserModel findFirst(String field, String value) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, field, value);
		
		Connection.closeInstancie();
		
		return userModel;
	}
	
	public UserModel update(UserModel userModel) {
		userModel = this.userRepository.save(userModel, userModel.getId());
		
		Connection.closeInstancie();
		
		return userModel;
	}
}