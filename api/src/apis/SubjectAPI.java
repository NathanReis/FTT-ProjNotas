package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.SubjectController;

/**
 * Servlet implementation class SubjectAPI
 */
@WebServlet("/subject/*")
public class SubjectAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectController subjectController = new SubjectController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectAPI() {
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
			String parameters = request.getRequestURI().replaceFirst("^.*/subject/*", "");
			
			if(parameters.isBlank()) {
				this.subjectController.findAll(request, response);
			} else if(parameters.matches("^\\d+$")) {
				this.subjectController.findFirst("id",Integer.parseInt(parameters),request,response);
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
}
