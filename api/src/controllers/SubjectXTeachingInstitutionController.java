package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helpers.JsonHelper;
import models.ErrorModel;
import models.TeachingInstitutionModel;
import repositories.ConnectionDB;
import repositories.TeachingInstitutionRepository;

public class SubjectXTeachingInstitutionController {
	private Gson gson = new Gson();
	
	public void addSubjects(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			TeachingInstitutionModel model = this.gson.fromJson(stringJson, TeachingInstitutionModel.class);
			
			TeachingInstitutionRepository repository = new TeachingInstitutionRepository();
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
