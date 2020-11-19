package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.JsonHelper;
import models.AverageGradeTIModel;
import models.ErrorModel;
import models.GraphicSearchTIModel;
import repositories.ConnectionDB;
import repositories.GraphicSearchTIRepository;

public class GraphicSearchTIController extends Controller<GraphicSearchTIModel>{
	
	public GraphicSearchTIController() {
		super(new GraphicSearchTIRepository());
	}
	
	public void averageGradeTI(HttpServletRequest request, HttpServletResponse response, Class<GraphicSearchTIModel> classType) throws Exception{
		
		String stringJson = JsonHelper.getJsonRequest(request);
		GraphicSearchTIModel model = this.gson.fromJson(stringJson, classType);
		
		AverageGradeTIModel grade = new AverageGradeTIModel();
		
		try {
			
			grade = ((GraphicSearchTIRepository)this.repository).averageGradeTI(model);
			
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
