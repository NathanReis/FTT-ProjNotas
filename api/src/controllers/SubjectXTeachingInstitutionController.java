package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SubjectXTeachingInstitutionModel;
import repositories.SubjectXTeachingInstitutionRepository;

public class SubjectXTeachingInstitutionController extends Controller<SubjectXTeachingInstitutionModel>{
	public SubjectXTeachingInstitutionController() {
		super(new SubjectXTeachingInstitutionRepository());
	}
	
	@Override
	public void delete(int id, HttpServletRequest request, HttpServletResponse response) {}
}
