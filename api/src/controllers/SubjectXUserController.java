package controllers;

import models.SubjectXUserModel;
import repositories.SubjectXUserRepository;

public class SubjectXUserController extends Controller<SubjectXUserModel>{
	public SubjectXUserController() {
		super(new SubjectXUserRepository());
	}
}
