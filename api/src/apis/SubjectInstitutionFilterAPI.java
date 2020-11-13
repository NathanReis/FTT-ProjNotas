package apis;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.SubjectController;

/**
 * Servlet implementation class SubjectFilterAPI
 */
@WebServlet("/subject-institution-filter")
public class SubjectInstitutionFilterAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectController subjectController = new SubjectController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectInstitutionFilterAPI() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			this.subjectController.filterForInstitutions(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
