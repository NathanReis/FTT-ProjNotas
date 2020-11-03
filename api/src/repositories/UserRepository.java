package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import models.TeachingInstitutionModel;
import models.UserModel;

public class UserRepository extends Repository<UserModel> {
	
	public UserRepository(){
		super("tbUsers");
	}
	
	@Override
	public int create(UserModel user) throws SQLException{
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "  (password, userName, idTeachingInstitution, type) ";
		sql       += "VALUES ";
		sql       += "  (?, ?, ?, ?)";
		
		try(PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			
			stmt.setString(1,user.getPassword());
			stmt.setString(2, user.getUserName());
			stmt.setInt(3, user.getTeachingInstitution().getId());
			stmt.setString(4, user.getType());
			
			stmt.executeUpdate();
		}
		
		return this.getInsertedId();
	} 
	
	@Override
	public List<UserModel> findAll() throws SQLException{
		
		String sql = "SELECT * ";
		sql       += "FROM " + this.table ;
		
		List<UserModel> userModels = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
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
	public UserModel findFirst(String field, double value) throws SQLException{
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1";
		
		UserModel userModel = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setDouble(1, value);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
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
	public UserModel findFirst(String field, int value) throws SQLException{
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1";
		
		UserModel userModel = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setInt(1, value);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
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
	public UserModel findFirst(String field, String value) throws SQLException{
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1";
		
		UserModel userModel = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setString(1, value);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("idTeachingInstitution"));
				
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
	public void update(UserModel user) throws SQLException{
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  password = ?, idTeachingInstitution = ?, type = ? ";
		sql       += "WHERE ";
		sql       += "  id = ?";
		
		try(PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			
			stmt.setString(1,user.getPassword());
			stmt.setInt(2, user.getTeachingInstitution().getId());
			stmt.setString(3, user.getType());
			stmt.setInt(4, user.getId());
			
			stmt.executeUpdate();
		}
	} 
}

