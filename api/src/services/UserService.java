package services;

import java.sql.SQLException;

import models.TeachingInstitutionModel;
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
			int idInalterado = model.getTeachingInstitution().getId();
			
			if(model.getUserName().equals(userType.getUserName())) {
				
				throw new IllegalArgumentException("Nome informado � o mesmo do atual");
			}
			else if(model != null) {
				
				if(idInalterado != userType.getTeachingInstitution().getId()) {
					
					throw new IllegalArgumentException("N�o � permitido a altera��o do id da institui��o");
				} else {

					this.ValidaUser(userType);
				}
			}
		} else {
			
			throw new IllegalArgumentException("Us�ario Aluno n�o pode ser alterado");
		}
	}

	public void ValidaUser(UserModel userName) throws SQLException, ClassNotFoundException {
		UserRepository userRepository = new UserRepository();
		UserModel user = userRepository.findFirst("userName", userName.getUserName());
		
		if(user != null) {
			throw new IllegalArgumentException("login j� cadastrado");
		}
		
	}
}
