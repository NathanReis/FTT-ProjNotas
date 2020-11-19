package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GradeIdSubjectModel;
import models.GradeIdUserModel;

public class GradeRepository extends Repository<GradeIdUserModel>{
	public GradeRepository() {
		super("tbSubjectsXUsers");
	}

	@Override
	public void create(GradeIdUserModel entity) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GradeIdUserModel fillModel(ResultSet resulSet) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GradeIdUserModel entity) throws SQLException, ClassNotFoundException {
		
		for(GradeIdSubjectModel grade: entity.getSubjectGrade()) {
			String sql = "UPDATE " + this.table + " ";
			sql       += "SET ";
			sql       += "  grade = ?";
			sql       += "WHERE ";
			sql       += "  idSubject = ?";
			
			try (PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)){
				
				stmt.setDouble(1, grade.getGrade());
				stmt.setInt(2, grade.getIdSubject());
				
				stmt.executeUpdate();
			}
		}
	}
}
