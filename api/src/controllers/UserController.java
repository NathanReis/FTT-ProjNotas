package controllers;

import java.util.List;

import models.UserModel;
import repositories.Connection;
import repositories.UserRepository;

public class UserController {
	private UserRepository userRepository = new UserRepository();
	
	public UserModel create(UserModel userModel) {
		userModel = this.userRepository.save(userModel, 0);
		
		Connection.closeInstance();
		
		return userModel;
	}
	
	public void delete(int id) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, "id", id);
		
		this.userRepository.delete(userModel);
		
		Connection.closeInstance();
	}
	
	public List<UserModel> findAll() {
		List<UserModel> userModels = this.userRepository.findAll(UserModel.class);
		
		Connection.closeInstance();
		
		return userModels;
	}
	
	public UserModel findFirst(String field, double value) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, field, value);
		
		Connection.closeInstance();
		
		return userModel;
	}
	
	public UserModel findFirst(String field, int value) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, field, value);
		
		Connection.closeInstance();
		
		return userModel;
	}
	
	public UserModel findFirst(String field, String value) {
		UserModel userModel = this.userRepository.findFirst(UserModel.class, field, value);
		
		Connection.closeInstance();
		
		return userModel;
	}
	
	public UserModel update(UserModel userModel) {
		userModel = this.userRepository.save(userModel, userModel.getId());
		
		Connection.closeInstance();
		
		return userModel;
	}
}