package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.SubjectInstitutionModel;
import models.SubjectModel;
import models.SubjectUserModel;

public class SubjectRepository extends Repository<SubjectModel> {
	public SubjectRepository(){
		super("tbSubjects");
	}
	
	@Override
	public void create(SubjectModel user) {}
	
	@Override
	public void delete(int id) {}

	@Override
	public SubjectModel fillModel(ResultSet resultSet) throws SQLException {
		SubjectModel subjectModel = new SubjectModel();
		subjectModel.setId(resultSet.getInt("id"));
		subjectModel.setDescription(resultSet.getString("description"));
		
		return subjectModel;
	}
	
	@Override
	public void update(SubjectModel user) {}
	
	public ArrayList<SubjectModel> institutionChoosesSubjects(int idInstitution, String description, int page, int qtd) throws SQLException, ClassNotFoundException {
		ArrayList<SubjectModel> subjects = new ArrayList<SubjectModel>();
		
		String sql = "SELECT s.* ";
		sql       += "FROM " + this.table + " AS s ";
		sql       += "WHERE s.id NOT IN (";
		sql       += "  SELECT sxti.idSubject ";
		sql       += "  FROM tbSubjectsXTeachingInstitutions AS sxti ";
		sql       += "  WHERE sxti.idTeachingInstitution = ?";
		sql       += ") ";
		
		if(!description.equals("")) {
			sql += "AND s.description LIKE ? ";
		}
		
		sql += "LIMIT " + (page * qtd - qtd) + ", " + qtd + ";";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, idInstitution);
			
			if(!description.equals("")) {
				description = "%" + description + "%";
				stmt.setString(2, description);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				subjects.add(this.fillModel(rs));
			}
		}
		
		return subjects;
	}
	
	public ArrayList<SubjectInstitutionModel> subjectsInstitution(int idInstitution, String description, int page, int qtd) throws SQLException, ClassNotFoundException {
		ArrayList<SubjectInstitutionModel> subjects = new ArrayList<SubjectInstitutionModel>();
		
		String sql = "SELECT ";
		sql       += "  s.*, ";
		sql       += "  sxti.active ";
		sql       += "FROM tbSubjectsXTeachingInstitutions AS sxti ";
		sql       += "  INNER JOIN " + this.table + " AS s ";
		sql       += "    ON s.id = sxti.idSubject ";
		sql       += "WHERE sxti.idTeachingInstitution = ? ";
		
		if(!description.equals("")) {
			sql += "AND s.description LIKE ? ";
		}
		
		sql += "LIMIT " + (page * qtd - qtd) + ", " + qtd + ";";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, idInstitution);
			
			if(!description.equals("")) {
				description = "%" + description + "%";
				stmt.setString(2, description);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				SubjectInstitutionModel subjectInstitutionModel = new SubjectInstitutionModel();
				subjectInstitutionModel.setSubject(this.fillModel(rs));
				subjectInstitutionModel.setActive(rs.getString("active"));
				
				subjects.add(subjectInstitutionModel);
			}
		}
		
		return subjects;
	}
	
	public ArrayList<SubjectModel> filterForStudents(int idUser, String description, int page, int qtd) throws SQLException, ClassNotFoundException {
		ArrayList<SubjectModel> subjects = new ArrayList<SubjectModel>();
		
		String sql = "SELECT s.* ";
		sql       += "FROM " + this.table + " AS s ";
		sql       += "  INNER JOIN tbSubjectsXUsers AS sxu ";
		sql       += "    ON sxu.idSubject = s.id ";
		sql       += "WHERE sxu.idUser = ? ";
		
		if(!description.equals("")) {
			sql += "  AND s.description LIKE ? ";
		}
		
		sql += "LIMIT " + (page * qtd - qtd) + ", " + qtd + ";";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, idUser);
			
			if(!description.equals("")) {
				description = "%" + description + "%";
				stmt.setString(2, description);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				subjects.add(this.fillModel(rs));
			}
		}
		
		return subjects;
	}

	public ArrayList<SubjectUserModel> subjectsUser(int idUser, String description, int page, int qtd) throws SQLException, ClassNotFoundException {
		ArrayList<SubjectUserModel> subjects = new ArrayList<SubjectUserModel>();
		
		String sql = "SELECT ";
		sql       += "  s.*, ";
		sql       += "  sxu.grade, ";
		sql       += "  sxu.semester, ";
		sql       += "  sxu.year ";
		sql       += "FROM tbSubjectsXUsers AS sxu ";
		sql       += "  INNER JOIN " + this.table + " AS s ";
		sql       += "    ON s.id = sxu.idSubject ";
		sql       += "WHERE ";
		sql       += "  sxu.idUser = ? AND ";
		sql       += "  sxu.idTeachingInstitution = (";
		sql       += "    SELECT u.idTeachingInstitution ";
		sql       += "    FROM tbUsers AS u ";
		sql       += "    WHERE u.id = ?";
		sql       += "  )";
		
		if(!description.equals("")) {
			sql += "AND s.description LIKE ? ";
		}
		
		sql += "LIMIT " + (page * qtd - qtd) + ", " + qtd + ";";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, idUser);
			stmt.setInt(2, idUser);
			
			if(!description.equals("")) {
				description = "%" + description + "%";
				stmt.setString(3, description);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				SubjectUserModel subjectUserModel = new SubjectUserModel();
				subjectUserModel.setSubject(this.fillModel(rs));
				subjectUserModel.setGrade(rs.getDouble("grade"));
				subjectUserModel.setSemester(rs.getInt("semester"));
				subjectUserModel.setYear(rs.getInt("year"));
				
				subjects.add(subjectUserModel);
			}
		}
		
		return subjects;
	}
}
