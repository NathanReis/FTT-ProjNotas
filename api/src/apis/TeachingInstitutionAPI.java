package apis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.TeachingInstitutionController;
import helpers.JsonHelper;
import models.TeachingInstitutionModel;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/teaching-institution/*")
public class TeachingInstitutionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
	private TeachingInstitutionController teachingInstitutionController = new TeachingInstitutionController(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeachingInstitutionAPI() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			String parameters = request.getRequestURI().replaceFirst("^.*/teaching-institution/*", "");
			
			if(parameters.isBlank()) {
				// /teaching-institution
				
				List<TeachingInstitutionModel> teachingInstitutionModels = this.teachingInstitutionController.findAll(); 
				
				response
					.getWriter()
					.append(this.gson.toJson(teachingInstitutionModels));
			} else if(parameters.matches("^\\d+$")) {
				// /teaching-institution/1
				
				TeachingInstitutionModel teachingInstitutionModel = this.teachingInstitutionController.findFirst("id", Integer.parseInt(parameters));
				
				response
					.getWriter()
					.append(this.gson.toJson(teachingInstitutionModel));
			} else if(parameters.matches("^.+/.+$")) {
				// /teaching-institution/id/1
				
				// FUNCIONA APENAS QUANDO SEGUNDO PARÂMETRO É STRING NO BANCO
				
				String valuesParameters[] = parameters.split("/");
				TeachingInstitutionModel teachingInstitutionModel = this.teachingInstitutionController.findFirst(valuesParameters[0], valuesParameters[1]);
				
				response
					.getWriter()
					.append(this.gson.toJson(teachingInstitutionModel));
			} else {
				response
					.getWriter()
					.append("INVALID ROUTE GET");
				
				return;
			}
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			TeachingInstitutionModel teachingInstitutionModel = this.gson.fromJson(stringJson, TeachingInstitutionModel.class);
			
			teachingInstitutionModel = this.teachingInstitutionController.create(teachingInstitutionModel);
			
			response
				.getWriter()
				.append(this.gson.toJson(teachingInstitutionModel));
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			TeachingInstitutionModel teachingInstitutionModel = this.gson.fromJson(stringJson, TeachingInstitutionModel.class);
			
			teachingInstitutionModel = this.teachingInstitutionController.update(teachingInstitutionModel);
			
			response
				.getWriter()
				.append(this.gson.toJson(teachingInstitutionModel));
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		if(!request.getRequestURI().matches("^.*/teaching-institution/\\d+$")) {
			// ! /teaching-institution/1
			
			response
				.getWriter()
				.append("INVALID ROUTE DELETE");
			
			return;
		}
		
		try {
			int id = Integer.parseInt(request.getRequestURI().replaceFirst(".*/teaching-institution/+", ""));
			this.teachingInstitutionController.delete(id);
			
			response
				.getWriter()
				.append("DELETE");
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}
}
