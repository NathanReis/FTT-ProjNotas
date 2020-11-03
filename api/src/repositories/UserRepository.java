package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.TeachingInstitutionModel;
import models.UserModel;

public class UserRepository extends Repository<UserModel> {
	public UserRepository(){
		super("tbUsers");
	}
	
	@Override
	public int create(UserModel user) throws SQLException, ClassNotFoundException {
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
	public ArrayList<UserModel> findAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM " + this.table + ";";
		
		ArrayList<UserModel> userModels = new ArrayList<UserModel>();
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
				UserModel userModel = new UserModel();
				userModel.setId(rs.getInt("id"));
				userModel.setPassword(rs.getString("password"));
				userModel.setType(rs.getString("type"));
				userModel.setUserName(rs.getString("userName"));
				userModel.setTeachingInstitution(teachingInstitutionModel);
				
				userModels.add(userModel);
			}
		}
		
		return userModels;
	}
	
	
	@Override
	public UserModel findFirst(String field, double value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		UserModel userModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setDouble(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
				userModel = new UserModel();
				userModel.setId(rs.getInt("id"));
				userModel.setPassword(rs.getString("password"));
				userModel.setType(rs.getString("type"));
				userModel.setUserName(rs.getString("userName"));
				userModel.setTeachingInstitution(teachingInstitutionModel);
			}
		}
		
		return userModel;
	}
	
	@Override
	public UserModel findFirst(String field, int value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		UserModel userModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
				userModel = new UserModel();
				userModel.setId(rs.getInt("id"));
				userModel.setPassword(rs.getString("password"));
				userModel.setType(rs.getString("type"));
				userModel.setUserName(rs.getString("userName"));
				userModel.setTeachingInstitution(teachingInstitutionModel);
			}
		}
		
		return userModel;
	}
	
	@Override
	public UserModel findFirst(String field, String value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		UserModel userModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
				userModel = new UserModel();
				userModel.setId(rs.getInt("id"));
				userModel.setPassword(rs.getString("password"));
				userModel.setType(rs.getString("type"));
				userModel.setUserName(rs.getString("userName"));
				userModel.setTeachingInstitution(teachingInstitutionModel);
			}
		}
		
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
