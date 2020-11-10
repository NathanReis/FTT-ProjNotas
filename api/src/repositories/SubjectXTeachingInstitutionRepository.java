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
	public void create(SubjectXTeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
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
		
		entity.setId(this.getInsertedId());
	}
	
	@Override
	public void delete(int id) {}
	
	@Override
	public SubjectXTeachingInstitutionModel fillModel(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		SubjectRepository subjectRepository = new SubjectRepository();
		SubjectModel subjectModel = subjectRepository.findFirst("id", resultSet.getInt("idSubject"));
		
		TeachingInstitutionRepository institutionRepository = new TeachingInstitutionRepository();
		TeachingInstitutionModel institutionModel = institutionRepository.findFirst("id", resultSet.getInt("idTeachingInstitution"));
		
		SubjectXTeachingInstitutionModel subjectXTeachingInstitutionModel = new SubjectXTeachingInstitutionModel();
		subjectXTeachingInstitutionModel.setId(resultSet.getInt("id"));
		subjectXTeachingInstitutionModel.setSubject(subjectModel);
		subjectXTeachingInstitutionModel.setTeachingInstitution(institutionModel);
		subjectXTeachingInstitutionModel.setActive(resultSet.getString("active"));
		
		return subjectXTeachingInstitutionModel;
	}

	@Override
	public void update(SubjectXTeachingInstitutionModel entity) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  active = ? ";
		sql       += "WHERE ";
		sql       += "  id = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, entity.getActive());
			stmt.setInt(2, entity.getId());
			
			stmt.executeUpdate();
		}
	}
}
