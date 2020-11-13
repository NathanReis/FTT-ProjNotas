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
	
	public ArrayList<SubjectModel> filterForInstitutions(String description, int idInstitution, int page, int qtd) throws SQLException, ClassNotFoundException {
		ArrayList<SubjectModel> subjects = new ArrayList<SubjectModel>();
		
		Boolean hasWhere = false;
		String sql = "SELECT s.* ";
		sql       += "FROM " + this.table + " AS s ";
		
		if(idInstitution != 0) {
			sql += "  INNER JOIN tbSubjectsXTeachingInstitutions AS sxti ";
			sql += "    ON sxti.idSubject = s.id ";
		}
		
		if(!description.equals("")) {
			sql += (hasWhere ? "AND" : "WHERE") + " s.description LIKE ? ";
			hasWhere = true;
		}
		
		if(idInstitution != 0) {
			sql += (hasWhere ? "AND" : "WHERE") + " sxti.idTeachingInstitution = ? ";
			sql += "AND sxti.active = 'A' ";
			hasWhere = true;
		}
		
		sql += "LIMIT " + (page * qtd - qtd) + ", " + qtd + ";";
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			if(!description.equals("")) {
				description = "%" + description + "%";
				stmt.setString(1, description);
				
				if(idInstitution != 0) {
					stmt.setInt(2, idInstitution);
				}
			} else if(idInstitution != 0) {
				stmt.setInt(1, idInstitution);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				subjects.add(this.fillModel(rs));
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
}
