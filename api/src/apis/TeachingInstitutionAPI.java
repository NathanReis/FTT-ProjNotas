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
				
				this.teachingInstitutionController.findAll(request, response); 
				
			} else if(parameters.matches("^\\d+$")) {
				// /teaching-institution/1
				
				this.teachingInstitutionController.findFirst("id", Integer.parseInt(parameters),request, response);
				
				
			}  else {
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
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		this.teachingInstitutionController.update(request, response);
		
		
	}
}
