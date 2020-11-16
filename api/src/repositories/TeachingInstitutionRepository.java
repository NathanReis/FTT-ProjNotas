package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.SubjectInstitutionModel;
import models.TeachingInstitutionModel;


public class TeachingInstitutionRepository extends Repository<TeachingInstitutionModel> {
	public TeachingInstitutionRepository() {
		super("tbTeachingInstitutions");
	}

	@Override
	public void create(TeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "  (name) ";
		sql       += "VALUES ";
		sql       += "  (?);";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, entity.getName());
			
			stmt.executeUpdate();
		}
		
		entity.setId(this.getInsertedId());
	}
	
	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM tbSubjectsXTeachingInstitutions WHERE idTeachingInstitution = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			 stmt.setInt(1, id);
			 stmt.executeUpdate();
		}
		
		sql = "DELETE FROM tbSubjectsXUsers WHERE idTeachingInstitution = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			 stmt.setInt(1, id);
			 stmt.executeUpdate();
		}
		
		sql = "UPDATE tbUsers SET idTeachingInstitution = null WHERE idTeachingInstitution = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			 stmt.setInt(1, id);
			 stmt.executeUpdate();
		}
		
		super.delete(id);
	}
	
	@Override
	public TeachingInstitutionModel fillModel(ResultSet resultSet) throws SQLException {
		TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
		teachingInstitutionModel.setId(resultSet.getInt("id"));
		teachingInstitutionModel.setName(resultSet.getString("name"));
		
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

	public void addSubjects(TeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		for(SubjectInstitutionModel subjectnstitution : entity.getSubjects()) {
			String sql = "DELETE FROM tbSubjectsXTeachingInstitutions ";
			sql       += "WHERE ";
			sql       += "  idSubject = ? AND ";
			sql       += "  idTeachingInstitution = ?;";
			
			try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
				stmt.setInt(1, subjectnstitution.getSubject().getId());
				stmt.setInt(2, entity.getId());
				
				stmt.executeUpdate();
			}
			
			sql  = "INSERT INTO tbSubjectsXTeachingInstitutions ";
			sql += "  (idSubject, idTeachingInstitution, active) ";
			sql += "VALUES ";
			sql += "  (?, ?, ?);";
			
			try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
				stmt.setInt(1, subjectnstitution.getSubject().getId());
				stmt.setInt(2, entity.getId());
				stmt.setString(3, "A");
				
				stmt.executeUpdate();
			}
		}
	}
}
