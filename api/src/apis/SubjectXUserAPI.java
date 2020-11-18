package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.SubjectXUserController;
import models.SubjectXUserModel;

/**
 * Servlet implementation class SubjectXUserAPI
 */
@WebServlet("/subjectXUser/*")
public class SubjectXUserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectXUserController subjectXUserController = new SubjectXUserController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectXUserAPI() {
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
			String parameters = request.getRequestURI().replaceFirst("^.*/subjectXUser/*", "");
			
			if(parameters.isBlank()) {
				
				// this.subjectXUserController.findAll(request, response);		
			} else if(parameters.matches("^\\d+$")) {
				
				// this.subjectXUserController.findFirst("id", Integer.parseInt(parameters), request, response);
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
		
		this.subjectXUserController.addSubjects(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// this.subjectXUserController.update(request, response, SubjectXUserModel.class);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		if(!request.getRequestURI().matches("^.*/subjectXUser/\\d+$")) {
			
			response
				.getWriter()
				.append("INVALID ROUTE DELETE");
			
			return;
		}
		
		int id = Integer.parseInt(request.getRequestURI().replaceFirst(".*/subjectXUser/+", ""));
		// this.subjectXUserController.delete(id, request, response);
	}

}
