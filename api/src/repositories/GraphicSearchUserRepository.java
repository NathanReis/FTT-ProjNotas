package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.AverageGradeSModel;
import models.GraphicSearchUserModel;
import models.Model;

public class GraphicSearchUserRepository extends Repository<GraphicSearchUserModel>{

	public GraphicSearchUserRepository() {
		super("tbSubjectsXUsers");
		
	}

	@Override
	public void create(GraphicSearchUserModel entity) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GraphicSearchUserModel entity) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GraphicSearchUserModel fillModel(ResultSet resulSet) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public AverageGradeSModel averageGradeS(GraphicSearchUserModel model) throws SQLException, ClassNotFoundException{
		AverageGradeSModel grade = new AverageGradeSModel();
		
		double userGrade = this.gradeUser(model);
		double institutionGrade = this.gradeInstitution(model); 
		double otherGrade = this.gradeOther(model);
		
		AverageGradeSModel average = new AverageGradeSModel();
		average.setGrade(userGrade);
		average.setAverageInstitution(institutionGrade);
		average.setAverageOther(otherGrade);
		
		return average;
	}
	
	public double gradeUser(GraphicSearchUserModel model) throws SQLException, ClassNotFoundException {
		String sql = "SELECT grade ";
		sql       += "FROM " + this.table;
		sql       += " WHERE ";
		sql       += "  idUser = ? AND ";
		sql       += "  idTeachingInstitution = ? AND ";
		sql       += "  idSubject = ? AND ";
		sql       += "  semester = ? AND ";
		sql       += "  year = ? ;";
		
		double grade = 0;
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, model.getIdUser());
			stmt.setInt(2, model.getIdTeachingInstitution());
			stmt.setInt(3, model.getIdSubject());
			stmt.setInt(4, model.getSemester());
			stmt.setInt(5, model.getYear());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				grade = rs.getDouble("grade");
			}
		}
		return grade;
	}
	
	public double gradeInstitution(GraphicSearchUserModel model) throws SQLException, ClassNotFoundException{
		String sql = "SELECT AVG(grade) AS media ";
		sql       += "FROM " + this.table;
		sql		  += " WHERE ";
		sql       += "  idTeachingInstitution = ? AND ";
		sql       += "  idSubject = ? AND ";
		sql       += "  semester = ? AND ";
		sql       += "  year = ? ;";
		
		double gradeInstitution = 0;
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)){
			stmt.setInt(1, model.getIdTeachingInstitution());
			stmt.setInt(2, model.getIdSubject());
			stmt.setInt(3, model.getSemester());
			stmt.setInt(4, model.getYear());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				gradeInstitution = rs.getDouble("media");
			}
		}
		return gradeInstitution;
	}
	
	public double gradeOther(GraphicSearchUserModel model) throws SQLException, ClassNotFoundException{
		String sql = "SELECT AVG(grade) AS media ";
		sql       += "FROM " + this.table;
		sql		  += " WHERE ";
		sql       += "  idTeachingInstitution NOT IN (?) AND ";
		sql       += "  idSubject = ? AND ";
		sql       += "  semester = ? AND ";
		sql       += "  year = ? ;";
		
		double gradeOther = 0;
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)){
			stmt.setInt(1, model.getIdTeachingInstitution());
			stmt.setInt(2, model.getIdSubject());
			stmt.setInt(3, model.getSemester());
			stmt.setInt(4, model.getYear());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				gradeOther = rs.getDouble("media");
			}
		}
		return gradeOther;
	}
}
