package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helpers.JsonHelper;
import models.ErrorModel;
import models.UserModel;
import repositories.ConnectionDB;
import repositories.UserRepository;

public class SubjectXUserController {
	private Gson gson = new Gson();
	
	public void addSubjects(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			UserModel model = this.gson.fromJson(stringJson, UserModel.class);
			
			UserRepository repository = new UserRepository();
			repository.addSubjects(model);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson("Inserido"));
		} catch(Exception exception) {
			ErrorModel error = new ErrorModel();
			error.setHasError(true);
			error.setMessageError(exception.getMessage());
			
			response
				.getWriter()
				.append(this.gson.toJson(error));
		}
	}
}
