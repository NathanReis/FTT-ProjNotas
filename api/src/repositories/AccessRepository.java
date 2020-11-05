package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.AccessModel;
import models.TeachingInstitutionModel;
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
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
				userModel = new UserModel();
				userModel.setId(rs.getInt("id"));
				userModel.setType(rs.getString("type"));
				userModel.setUserName(rs.getString("userName"));
				userModel.setTeachingInstitution(teachingInstitutionModel);
			}
		}
		
		return userModel;
	}
}
