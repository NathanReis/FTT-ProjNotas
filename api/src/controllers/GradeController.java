package controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helpers.JsonHelper;
import models.ErrorModel;
import models.GradeIdUserModel;
import repositories.ConnectionDB;
import repositories.GradeRepository;

public class GradeController extends Controller<GradeIdUserModel>{
	public GradeController() {
		super (new GradeRepository());
	}
			
	public void update(HttpServletRequest request, HttpServletResponse response, Class<GradeIdUserModel> classType) throws IOException {
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			GradeIdUserModel model = this.gson.fromJson(stringJson, classType);
			
			this.repository.update(model);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(model));
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
