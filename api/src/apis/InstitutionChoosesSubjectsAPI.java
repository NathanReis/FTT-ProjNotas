package apis;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.SubjectController;

@WebServlet("/institution-chooses-subjects/*")
public class InstitutionChoosesSubjectsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstitutionChoosesSubjectsAPI() {
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
			SubjectController subjectController = new SubjectController(); 
			subjectController.institutionChoosesSubjects(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
