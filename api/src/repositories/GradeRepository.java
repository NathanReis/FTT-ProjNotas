package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			sql       += "  grade = ? ";
			sql       += "WHERE ";
			sql       += "  idTeachingInstitution = ? AND ";
			sql       += "  idSubject = ? AND ";
			sql       += "  idUser = ? ;";
			
			try (PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)){
				
				stmt.setDouble(1, grade.getGrade());
				stmt.setInt(2, entity.getIdTeachingInstitution());
				stmt.setInt(3, grade.getIdSubject());
				stmt.setInt(4, entity.getIdUser());
				stmt.executeUpdate();
			}
		}
	}
}
