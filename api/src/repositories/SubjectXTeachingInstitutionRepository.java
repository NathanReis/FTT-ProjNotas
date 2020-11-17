package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.SubjectModel;
import models.SubjectXTeachingInstitutionModel;
import models.TeachingInstitutionModel;
import models.ToggleActiveModel;

public class SubjectXTeachingInstitutionRepository extends Repository<SubjectXTeachingInstitutionModel> {
	public SubjectXTeachingInstitutionRepository() {
		super("tbSubjectsXTeachingInstitutions");
	}

	@Override
	public void create(SubjectXTeachingInstitutionModel entity) {}
	
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
	public void update(SubjectXTeachingInstitutionModel entity) {}
	
	public void toggleActive(ToggleActiveModel toggleActiveModel) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  active = ? ";
		sql       += "WHERE ";
		sql       += "  idTeachingInstitution = ? AND";
		sql       += "  idSubject = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			 stmt.setString(1, toggleActiveModel.getActive());
			 stmt.setInt(2, toggleActiveModel.getIdTeachingInstitution());
			 stmt.setInt(3, toggleActiveModel.getIdSubject());
			 
			 stmt.executeUpdate();
		}
	}
}
