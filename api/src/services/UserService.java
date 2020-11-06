package services;

import java.sql.SQLException;

import models.UserModel;
import repositories.UserRepository;

public class UserService extends Service<UserModel>{
	public UserService() {
		super();
	}
	
	 UserRepository userRepository = new UserRepository();

	@Override
	public void ValidaUser(UserModel userName) throws SQLException, ClassNotFoundException {
		
		UserModel user = this.userRepository.findFirst("userName", userName.getUserName());
		
		if(user != null) {
			throw new IllegalArgumentException("login já cadastrado");
		}
	}
}
