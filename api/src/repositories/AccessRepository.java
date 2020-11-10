package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.AccessModel;
import models.UserModel;

public class AccessRepository {
	public UserModel login(AccessModel accessModel) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM tbUsers ";
		sql       += "WHERE userName = ? AND password = ?;";
		
		UserModel userModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, accessModel.getUserName());
			stmt.setString(2, accessModel.getPassword());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				UserRepository userRepository = new UserRepository();
				userModel = userRepository.fillModel(rs);
			}
		}
		
		return userModel;
	}
}
