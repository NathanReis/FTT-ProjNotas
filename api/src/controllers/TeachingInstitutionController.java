package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helpers.JsonHelper;
import models.ErrorModel;
import models.TeachingInstitutionModel;
import models.UserModel;
import repositories.ConnectionDB;
import repositories.TeachingInstitutionRepository;

public class TeachingInstitutionController extends Controller<TeachingInstitutionModel> {
	public TeachingInstitutionController() {
		super(new TeachingInstitutionRepository());
	}
	
	private Gson gson = new Gson();
	
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) {}
	
	@Override
	public void delete(int id,HttpServletRequest request, HttpServletResponse response) {}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			String stringJson = JsonHelper.getJsonRequest(request);
			TeachingInstitutionModel teachingInstitutionModel = this.gson.fromJson(stringJson, TeachingInstitutionModel.class);
			
			this.repository.update(teachingInstitutionModel);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(teachingInstitutionModel));
			
		} catch(Exception exception) {
			ErrorModel error = new ErrorModel();
			error.setHasError(true);
			error.setMessageError(exception.getMessage());
			response
			.getWriter()
			.append(this.gson.toJson(error));
		}
		
	}

	@Override
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ArrayList<TeachingInstitutionModel> model = null;
		try {
			model = this.repository.findAll();
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(model));
			
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
		
		
	}

	@Override
	public void findFirst(String field, double value, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		TeachingInstitutionModel model = null;
		
		try {
			model = this.repository.findFirst(field, value);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(model));
			
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	
	}

	@Override
	public void findFirst(String field, int value, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		TeachingInstitutionModel model = null;
		
		try {
			model = this.repository.findFirst(field, value);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(model));
			
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}

	}

	@Override
	public void findFirst(String field, String value, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		TeachingInstitutionModel model = null;
		
		try {
			model = this.repository.findFirst(field, value);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(model));
			
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}
}
