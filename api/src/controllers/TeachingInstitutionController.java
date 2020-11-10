package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.TeachingInstitutionModel;
import repositories.TeachingInstitutionRepository;

public class TeachingInstitutionController extends Controller<TeachingInstitutionModel> {
	public TeachingInstitutionController() {
		super(new TeachingInstitutionRepository());
	}
	
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response, Class<TeachingInstitutionModel> classType) {}
	
	@Override
	public void delete(int id, HttpServletRequest request, HttpServletResponse response) {}
	
	@Override
	public void update(HttpServletRequest request, HttpServletResponse response, Class<TeachingInstitutionModel> classType) {}
}
