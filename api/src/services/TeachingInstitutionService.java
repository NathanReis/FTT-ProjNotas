package services;

import java.sql.SQLException;
import models.TeachingInstitutionModel;
import repositories.TeachingInstitutionRepository;

public class TeachingInstitutionService extends Service<TeachingInstitutionModel> {
	
	public TeachingInstitutionService() {
		super();
	}
	
	private TeachingInstitutionRepository teachingInstitutionRepository = new TeachingInstitutionRepository();
	
	@Override
	public void ValidaUser(TeachingInstitutionModel userName) throws SQLException, ClassNotFoundException {
		
		TeachingInstitutionModel user = this.teachingInstitutionRepository.findFirst("name", userName.getName());
		
		if(user != null) {
			throw new IllegalArgumentException("login já cadastrado");
		}
	}
}
