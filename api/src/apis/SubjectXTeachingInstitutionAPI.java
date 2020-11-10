package apis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.SubjectXTeachingInstitutionController;
import models.SubjectXTeachingInstitutionModel;

/**
 * Servlet implementation class SubjectXTeachingInstitutionAPI
 */
@WebServlet("/subjectXTeachingInstitution/*")
public class SubjectXTeachingInstitutionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectXTeachingInstitutionController subjectXTeachingInstitutionController = new SubjectXTeachingInstitutionController(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectXTeachingInstitutionAPI() {
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
			String parameters = request.getRequestURI().replaceFirst("^.*/subjectXTeachingInstitution/*", "");
			
			if(parameters.isBlank()) {
				
				this.subjectXTeachingInstitutionController.findAll(request, response);
			} else if(parameters.matches("^\\d+$")) {
				
				this.subjectXTeachingInstitutionController.findFirst("id",Integer.parseInt(parameters),request,response);
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
		
		this.subjectXTeachingInstitutionController.create(request, response, SubjectXTeachingInstitutionModel.class);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		this.subjectXTeachingInstitutionController.update(request, response, SubjectXTeachingInstitutionModel.class);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		if(!request.getRequestURI().matches("^.*/subjectXTeachingInstitution/\\d+$")) {
			
			response
				.getWriter()
				.append("INVALID ROUTE DELETE");
			return;
		}
		
		int id = Integer.parseInt(request.getRequestURI().replaceFirst(".*/subjectXTeachingInstitution/+",""));
		this.subjectXTeachingInstitutionController.delete(id, request, response);
	}

}
