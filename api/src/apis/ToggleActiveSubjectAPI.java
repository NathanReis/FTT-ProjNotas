package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.SubjectXTeachingInstitutionController;

/**
 * Servlet implementation class ToggleActiveSubjectAPI
 */
@WebServlet("/toggle-active-subject")
public class ToggleActiveSubjectAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToggleActiveSubjectAPI() {
        super();
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		SubjectXTeachingInstitutionController subjectXTeachingInstitutionController = new SubjectXTeachingInstitutionController();
		subjectXTeachingInstitutionController.toggleActive(request, response);
	}
}
