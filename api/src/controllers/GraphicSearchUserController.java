package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.JsonHelper;
import models.AverageGradeSModel;
import models.ErrorModel;
import models.GraphicSearchUserModel;
import repositories.ConnectionDB;
import repositories.GraphicSearchUserRepository;

public class GraphicSearchUserController extends Controller<GraphicSearchUserModel>{
	public GraphicSearchUserController() {
		super(new GraphicSearchUserRepository());
	}
	
	public void averageGradeS(HttpServletRequest request, HttpServletResponse response, Class<GraphicSearchUserModel> classType) throws Exception{
		
		String stringJson = JsonHelper.getJsonRequest(request);
		GraphicSearchUserModel model = this.gson.fromJson(stringJson, classType);
		
		AverageGradeSModel grade = new AverageGradeSModel();
		
		try {
			
			grade = ((GraphicSearchUserRepository)this.repository).averageGradeS(model);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(grade));	
		}catch(Exception exception) {
			ErrorModel error = new ErrorModel();
			error.setHasError(true);
			error.setMessageError(exception.getMessage());
			
			response
				.getWriter()
				.append(this.gson.toJson(error));
		}
		
	}
}
