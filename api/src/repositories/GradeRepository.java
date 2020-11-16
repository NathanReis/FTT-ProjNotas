package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<GradeIdUserModel> findAll(int idUser){
		return null;
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response, Class<GradeIdUserModel> classType)
	
}
