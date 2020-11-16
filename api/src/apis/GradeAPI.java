package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GradeController;
import models.GradeIdUserModel;

/**
 * Servlet implementation class GradeAPI
 */
@WebServlet("/GradeAPI")
public class GradeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GradeController gradeController = new GradeController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			
			this.gradeController.findAll(request, response, GradeIdUserModel.class);
			
		}catch(Exception exception) {
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
			
			this.gradeController.update(request, response, GradeIdUserModel.class);
			
		}catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}

}
