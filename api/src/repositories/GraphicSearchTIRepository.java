package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.AverageGradeTIModel;
import models.GraphicSearchTIModel;

public class GraphicSearchTIRepository extends Repository<GraphicSearchTIModel>{
	
	public GraphicSearchTIRepository() {
		super("tbSubjectsXUsers");
	}

	@Override
	public void create(GraphicSearchTIModel entity) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GraphicSearchTIModel fillModel(ResultSet resulSet) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GraphicSearchTIModel entity) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	public AverageGradeTIModel averageGradeTI(GraphicSearchTIModel model) throws SQLException, ClassNotFoundException{
		AverageGradeTIModel averageGrade = new AverageGradeTIModel();
		
		double grade = this.gradeTI(model); 
		double otherGrade = this.gradeOtherTI(model);
		
		averageGrade.setAverageTeachingInstitution(grade);
		averageGrade.setAverageOther(otherGrade);
		
		return averageGrade;
	}
	
	public double gradeTI(GraphicSearchTIModel model) throws SQLException, ClassNotFoundException{
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
					
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				gradeInstitution = rs.getDouble("media");
			}
		}
		return gradeInstitution;
	}
	
	public double gradeOtherTI(GraphicSearchTIModel model) throws SQLException, ClassNotFoundException{
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
					
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				gradeOther = rs.getDouble("media");
			}
		}
		return gradeOther;
	}
}
