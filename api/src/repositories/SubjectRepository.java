package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.SubjectModel;

public class SubjectRepository extends Repository<SubjectModel> {
	public SubjectRepository(){
		super("tbSubjects");
	}
	
	@Override
	public int create(SubjectModel user) throws SQLException, ClassNotFoundException {
		return 0;
	} 
	
	@Override
	public ArrayList<SubjectModel> findAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM " + this.table + ";";
		
		ArrayList<SubjectModel> subjectModels = new ArrayList<SubjectModel>();
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				SubjectModel subjectModel = new SubjectModel();
				subjectModel.setId(rs.getInt("id"));
				subjectModel.setDescription(rs.getString("description"));
				
				subjectModels.add(subjectModel);
			}
		}
		
		return subjectModels;
	}
	
	
	@Override
	public SubjectModel findFirst(String field, double value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		SubjectModel subjectModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setDouble(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				subjectModel = new SubjectModel();
				subjectModel.setId(rs.getInt("id"));
				subjectModel.setDescription(rs.getString("description"));
			}
		}
		
		return subjectModel;
	}
	
	@Override
	public SubjectModel findFirst(String field, int value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		SubjectModel subjectModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				subjectModel = new SubjectModel();
				subjectModel.setId(rs.getInt("id"));
				subjectModel.setDescription(rs.getString("description"));
			}
		}
		
		return subjectModel;
	}
	
	@Override
	public SubjectModel findFirst(String field, String value) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		SubjectModel subjectModel = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				subjectModel = new SubjectModel();
				subjectModel.setId(rs.getInt("id"));
				subjectModel.setDescription(rs.getString("description"));
			}
		}
		
		return subjectModel;
	}
	
	@Override
	public void update(SubjectModel user) throws SQLException, ClassNotFoundException {} 
}
