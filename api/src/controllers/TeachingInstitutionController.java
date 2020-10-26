package controllers;

import java.util.List;

import models.TeachingInstitutionModel;
import repositories.Connection;
import repositories.TeachingInstitutionRepository;

public class TeachingInstitutionController {
	private TeachingInstitutionRepository teachingInstitutionRepository = new TeachingInstitutionRepository();
	
	public TeachingInstitutionModel create(TeachingInstitutionModel teachingInstitutionModel) {
		teachingInstitutionModel = this.teachingInstitutionRepository.save(teachingInstitutionModel, 0);
		
		Connection.closeInstancie();
		
		return teachingInstitutionModel;
	}
	
	public void delete(int id) {
		TeachingInstitutionModel teachingInstitutionModel = this.teachingInstitutionRepository.findFirst(TeachingInstitutionModel.class, "id", id);
		
		this.teachingInstitutionRepository.delete(teachingInstitutionModel);
		
		Connection.closeInstancie();
	}
	
	public List<TeachingInstitutionModel> findAll() {
		List<TeachingInstitutionModel> teachingInstitutionModels = this.teachingInstitutionRepository.findAll(TeachingInstitutionModel.class);
		
		Connection.closeInstancie();
		
		return teachingInstitutionModels;
	}
	
	public TeachingInstitutionModel findFirst(String field, double value) {
		TeachingInstitutionModel teachingInstitutionModel = this.teachingInstitutionRepository.findFirst(TeachingInstitutionModel.class, field, value);
		
		Connection.closeInstancie();
		
		return teachingInstitutionModel;
	}
	
	public TeachingInstitutionModel findFirst(String field, int value) {
		TeachingInstitutionModel teachingInstitutionModel = this.teachingInstitutionRepository.findFirst(TeachingInstitutionModel.class, field, value);
		
		Connection.closeInstancie();
		
		return teachingInstitutionModel;
	}
	
	public TeachingInstitutionModel findFirst(String field, String value) {
		TeachingInstitutionModel teachingInstitutionModel = this.teachingInstitutionRepository.findFirst(TeachingInstitutionModel.class, field, value);
		
		Connection.closeInstancie();
		
		return teachingInstitutionModel;
	}
	
	public TeachingInstitutionModel update(TeachingInstitutionModel teachingInstitutionModel) {
		teachingInstitutionModel = this.teachingInstitutionRepository.save(teachingInstitutionModel, teachingInstitutionModel.getId());
		
		Connection.closeInstancie();
		
		return teachingInstitutionModel;
	}
}