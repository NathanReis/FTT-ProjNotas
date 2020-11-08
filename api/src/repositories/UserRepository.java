package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.TeachingInstitutionModel;
import models.UserModel;
import services.UserService;

public class UserRepository extends Repository<UserModel> {
	public UserRepository(){
		super("tbUsers");
	}
	
	@Override
	public int create(UserModel user) throws SQLException, ClassNotFoundException {
		UserService valida = new UserService();
		
		valida.ValidaUser(user);
		
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "  (password, userName, idTeachingInstitution, type) ";
		sql       += "VALUES ";
		sql       += "  (?, ?, ?, ?);";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1,user.getPassword());
			stmt.setString(2, user.getUserName());
			stmt.setInt(3, user.getTeachingInstitution().getId());
			stmt.setString(4, user.getType());
			
			stmt.executeUpdate();
		}
		
		return this.getInsertedId();
	}
	
	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		UserModel userModel = this.findFirst("id", id);
		
		if(userModel.getType().equals("I")) {
			TeachingInstitutionRepository teachingInstitutionRepository = new TeachingInstitutionRepository();
			
			teachingInstitutionRepository.delete(userModel.getTeachingInstitution().getId());
		}
		
		String sql = "DELETE FROM " + this.table + " WHERE id = ?;"; 
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			 stmt.setInt(1, id);
			 stmt.executeUpdate();
		}
	}
	
	@Override
	public UserModel fillModel(ResultSet resultSet) throws SQLException {
		TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
		teachingInstitutionModel.setId(resultSet.getInt("idTeachingInstitution"));
		
		UserModel userModel = new UserModel();
		userModel.setId(resultSet.getInt("id"));
		userModel.setType(resultSet.getString("type"));
		userModel.setUserName(resultSet.getString("userName"));
		userModel.setTeachingInstitution(teachingInstitutionModel);
		
		return userModel;
	}
	
	@Override
	public void update(UserModel user) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  password = ?, idTeachingInstitution = ? ";
		sql       += "WHERE ";
		sql       += "  id = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1,user.getPassword());
			stmt.setInt(2, user.getTeachingInstitution().getId());
			stmt.setInt(3, user.getId());
			
			stmt.executeUpdate();
		}
	} 
}
