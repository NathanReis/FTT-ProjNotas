package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ErrorModel;
import models.UserModel;
import repositories.ConnectionDB;
import repositories.UserRepository;
import helpers.JsonHelper;
import com.google.gson.Gson;

public class UserController extends Controller<UserModel> {
	public UserController() {
		super (new UserRepository());
	}
	
	private Gson gson = new Gson();

	@Override
	public void create(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			
			String stringJson = JsonHelper.getJsonRequest(request);
			UserModel userModel = this.gson.fromJson(stringJson, UserModel.class);
			
			int id = this.repository.create(userModel);
			
			userModel.setId(id);
			
			ConnectionDB.closeInstance();
			
			response
					.getWriter()
					.append(this.gson.toJson(userModel));
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
	public void delete(int id, HttpServletRequest request ,HttpServletResponse response) throws IOException {
		try {
			this.repository.delete(id);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append("DELETE");
			
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
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			String stringJson = JsonHelper.getJsonRequest(request);
			UserModel userModel = this.gson.fromJson(stringJson, UserModel.class);
			
			this.repository.update(userModel);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(userModel));
			
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
		
	}

	@Override
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<UserModel> model = null;
		
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
	public void findFirst(String field, double value, HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserModel model = null;
		
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
	public void findFirst(String field, int value, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserModel model = null;
		
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
	public void findFirst(String field, String value, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserModel model = null;
		
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
