package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SubjectModel;
import repositories.SubjectRepository;

public class SubjectController extends Controller<SubjectModel>{
	public SubjectController(){
		super(new SubjectRepository());
	}
	
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response, Class<SubjectModel> classType) {}
	
	@Override
	public void delete(int id, HttpServletRequest request, HttpServletResponse response) {}
	
	@Override
	public void update(HttpServletRequest request, HttpServletResponse response,  Class<SubjectModel> classType) {}
}
