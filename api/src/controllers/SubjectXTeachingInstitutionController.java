package controllers;

import models.SubjectXTeachingInstitutionModel;
import repositories.SubjectXTeachingInstitutionRepository;

public class SubjectXTeachingInstitutionController extends Controller<SubjectXTeachingInstitutionModel>{
	public SubjectXTeachingInstitutionController() {
		super(new SubjectXTeachingInstitutionRepository());
	}
}
