package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.TeachingInstitutionModel;
import services.TeachingInstitutionService;

public class TeachingInstitutionRepository extends Repository<TeachingInstitutionModel> {
	public TeachingInstitutionRepository() {
		super("tbTeachingInstitutions");
	}

	@Override
	public int create(TeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		
		TeachingInstitutionService valida = new TeachingInstitutionService();
		
		valida.ValidaUser(entity);
		
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "    (name) ";
		sql       += "VALUES ";
		sql       += "    (?);";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, entity.getName());
			stmt.executeUpdate();
		}
		
		return this.getInsertedId();
	}

	@Override
	public ArrayList<TeachingInstitutionModel> findAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM " + this.table + ";";
		
		ArrayList<TeachingInstitutionModel> teachingInstitutionModels = new ArrayList<TeachingInstitutionModel>();
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("id"));
				teachingInstitutionModel.setName(rs.getString("name"));
				
				teachingInstitutionModels.add(teachingInstitutionModel);
			}
		}
		
		return teachingInstitutionModels;
	}

	@Override
	public TeachingInstitutionModel findFirst(String field, double value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		TeachingInstitutionModel teachingInstitutionModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setDouble(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("id"));
				teachingInstitutionModel.setName(rs.getString("name"));
			}
		}
		
		return teachingInstitutionModel;
	}

	@Override
	public TeachingInstitutionModel findFirst(String field, int value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		TeachingInstitutionModel teachingInstitutionModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("id"));
				teachingInstitutionModel.setName(rs.getString("name"));
			}
		}
		
		return teachingInstitutionModel;
	}

	@Override
	public TeachingInstitutionModel findFirst(String field, String value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		TeachingInstitutionModel teachingInstitutionModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				teachingInstitutionModel = new TeachingInstitutionModel();
				teachingInstitutionModel.setId(rs.getInt("id"));
				teachingInstitutionModel.setName(rs.getString("name"));
			}
		}
		
		return teachingInstitutionModel;
	}

	@Override
	public void update(TeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  name = ? ";
		sql       += "WHERE ";
		sql       += "  id = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1,entity.getName());
			stmt.setInt(2, entity.getId());
			
			stmt.executeUpdate();
		}
	}
}
