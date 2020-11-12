package services;

import java.sql.SQLException;
import models.UserModel;
import repositories.UserRepository;

public class UserService extends Service<UserModel>{
	

	public UserService() {
		super();
	}
	
	public void ValidaUpdate(UserModel userType) throws SQLException, ClassNotFoundException {
		
		if(userType.getType().equals("I")) {
			
			UserRepository userRepository = new UserRepository();
			
			UserModel model = userRepository.findFirst("id", userType.getId());
			
			if(!model.getUserName().equals(userType.getUserName())) {
				
				this.ValidaUser(userType);
			}
		} 
	}

	public void ValidaUser(UserModel userName) throws SQLException, ClassNotFoundException {
		UserRepository userRepository = new UserRepository();
		UserModel user = userRepository.findFirst("userName", userName.getUserName());
		
		if(user != null) {
			throw new IllegalArgumentException("login já cadastrado");
		}
		
	}
}
