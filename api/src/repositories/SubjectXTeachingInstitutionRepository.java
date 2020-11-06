package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.SubjectModel;
import models.SubjectXTeachingInstitutionModel;
import models.TeachingInstitutionModel;

public class SubjectXTeachingInstitutionRepository extends Repository<SubjectXTeachingInstitutionModel> {
	public SubjectXTeachingInstitutionRepository() {
		super("tbSubjectsXTeachingInstitutions");
	}

	@Override
	public int create(SubjectXTeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "  (idSubject, idTeachingInstitution, active) ";
		sql       += "VALUES ";
		sql       += "  (?, ?, ?);";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, entity.getSubject().getId());
			stmt.setInt(2, entity.getTeachingInstitution().getId());
			stmt.setString(3, "A");
			
			stmt.executeUpdate();
		}
		
		return this.getInsertedId();
	}
	
	@Override
	public SubjectXTeachingInstitutionModel fillModel(ResultSet resultSet) throws SQLException {
		SubjectModel subjectModel = new SubjectModel();
		subjectModel.setId(resultSet.getInt("idSubject"));
		
		TeachingInstitutionModel teachingInstitutionModel = new TeachingInstitutionModel();
		teachingInstitutionModel.setId(resultSet.getInt("idTeachingInstitution"));
		
		SubjectXTeachingInstitutionModel subjectXTeachingInstitutionModel = new SubjectXTeachingInstitutionModel();
		subjectXTeachingInstitutionModel.setId(resultSet.getInt("id"));
		subjectXTeachingInstitutionModel.setSubject(subjectModel);
		subjectXTeachingInstitutionModel.setTeachingInstitution(teachingInstitutionModel);
		subjectXTeachingInstitutionModel.setActive(resultSet.getString("active"));
		
		return subjectXTeachingInstitutionModel;
	}

	@Override
	public void update(SubjectXTeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  idSubject = ?, idTeachingInstitution = ?, active = ? ";
		sql       += "WHERE ";
		sql       += "  id = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, entity.getSubject().getId());
			stmt.setInt(2, entity.getTeachingInstitution().getId());
			stmt.setString(3, entity.getActive());
			stmt.setInt(4, entity.getId());
			
			stmt.executeUpdate();
		}
	}
}
