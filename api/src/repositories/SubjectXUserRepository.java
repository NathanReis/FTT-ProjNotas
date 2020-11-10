package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.SubjectModel;
import models.SubjectXUserModel;
import models.TeachingInstitutionModel;
import models.UserModel;

public class SubjectXUserRepository extends Repository<SubjectXUserModel> {
	public SubjectXUserRepository() {
		super("tbSubjectsXUsers");
	}

	@Override
	public void create(SubjectXUserModel entity) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "  (idSubject, idTeachingInstitution, idUser, grade, semester, year) ";
		sql       += "VALUES ";
		sql       += "  (?, ?, ?, ?, ?, ?);";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, entity.getSubject().getId());
			stmt.setInt(2, entity.getTeachingInstitution().getId());
			stmt.setInt(3, entity.getUser().getId());
			stmt.setDouble(4, entity.getGrade());
			stmt.setInt(5, entity.getSemester());
			stmt.setInt(6, entity.getYear());
			
			stmt.executeUpdate();
		}
		
		entity.setId(this.getInsertedId());
	}
	
	@Override
	public SubjectXUserModel fillModel(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		SubjectRepository subjectRepository = new SubjectRepository();
		SubjectModel subjectModel = subjectRepository.findFirst("id", resultSet.getInt("idSubject"));
		
		TeachingInstitutionRepository institutionRepository = new TeachingInstitutionRepository();
		TeachingInstitutionModel institutionModel = institutionRepository.findFirst("id", resultSet.getInt("idTeachingInstitution"));
		
		UserRepository userRepository = new UserRepository();
		UserModel userModel = userRepository.findFirst("id", resultSet.getInt("idUser"));
		
		SubjectXUserModel subjectXUserModel = new SubjectXUserModel();
		subjectXUserModel.setId(resultSet.getInt("id"));
		subjectXUserModel.setSubject(subjectModel);
		subjectXUserModel.setTeachingInstitution(institutionModel);
		subjectXUserModel.setUser(userModel);
		subjectXUserModel.setGrade(resultSet.getDouble("grade"));
		subjectXUserModel.setSemester(resultSet.getInt("semester"));
		subjectXUserModel.setYear(resultSet.getInt("year"));
		
		return subjectXUserModel;
	}
	
	@Override
	public void update(SubjectXUserModel entity) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  idSubject = ?, ";
		sql       += "  idTeachingInstitution = ?, ";
		sql       += "  idUser = ?, ";
		sql       += "  grade = ?, ";
		sql       += "  semester = ?, ";
		sql       += "  year = ? ";
		sql       += "WHERE ";
		sql       += "  id = ?;";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, entity.getSubject().getId());
			stmt.setInt(2, entity.getTeachingInstitution().getId());
			stmt.setInt(3, entity.getUser().getId());
			stmt.setDouble(4, entity.getGrade());
			stmt.setInt(5, entity.getSemester());
			stmt.setInt(6, entity.getYear());
			stmt.setInt(7, entity.getId());
			
			stmt.executeUpdate();
		}
	}
}
