package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ErrorModel;
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
	
	public void filter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String description = "";
			int idInstitution = 0;
			int page = 1;
			int qtd = 20;
			
			if(request.getQueryString() != null) {
				String[] queryParams = request.getQueryString().split("&");
				int iMax = queryParams.length;
				
				String rgxDescription = "^description=";
				String rgxIdInstitution = "^idInstitution=";
				String rgxPage = "^page=";
				String rgxqtd = "^qtd=";
				
				for(int i = 0; i < iMax; i++) {
					if(queryParams[i].matches(rgxDescription + ".*")) {
						description = queryParams[i].replaceFirst(rgxDescription, "");
					} else if(queryParams[i].matches(rgxIdInstitution + ".*")) {
						idInstitution = Integer.parseInt(queryParams[i].replaceFirst(rgxIdInstitution, ""));
					} else if(queryParams[i].matches(rgxPage + ".*")) {
						page = Integer.parseInt(queryParams[i].replaceFirst(rgxPage, ""));
					} else if(queryParams[i].matches(rgxqtd + ".*")) {
						qtd = Integer.parseInt(queryParams[i].replaceFirst(rgxqtd, ""));
					}
				}
			}
			
			ArrayList<SubjectModel> subjects = ((SubjectRepository)this.repository).filter(description, idInstitution, page, qtd);
			
			response
				.getWriter()
				.append(this.gson.toJson(subjects));
		} catch(Exception exception) {
			ErrorModel errorModel = new ErrorModel();
			errorModel.setHasError(true);
			errorModel.setMessageError(exception.getMessage());
			
			response
				.getWriter()
				.append(this.gson.toJson(errorModel));
		}
	}
}
