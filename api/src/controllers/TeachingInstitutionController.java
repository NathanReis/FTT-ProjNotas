package controllers;

import models.TeachingInstitutionModel;

import repositories.TeachingInstitutionRepository;

public class TeachingInstitutionController extends Controller<TeachingInstitutionModel> {
	public TeachingInstitutionController() {
		super(new TeachingInstitutionRepository());
	}
		
}
