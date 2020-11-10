package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.SubjectModel;

public class SubjectRepository extends Repository<SubjectModel> {
	public SubjectRepository(){
		super("tbSubjects");
	}
	
	@Override
	public void create(SubjectModel user) throws SQLException, ClassNotFoundException {}
	
	@Override
	public SubjectModel fillModel(ResultSet resultSet) throws SQLException {
		SubjectModel subjectModel = new SubjectModel();
		subjectModel.setId(resultSet.getInt("id"));
		subjectModel.setDescription(resultSet.getString("description"));
		
		return subjectModel;
	}
	
	@Override
	public void update(SubjectModel user) throws SQLException, ClassNotFoundException {} 
}
