package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GraphicSearchTIController;
import models.GraphicSearchTIModel;

/**
 * Servlet implementation class GraphicSearchInstitutionAPI
 */
@WebServlet("/GraphicSearchInstitutionAPI")
public class GraphicSearchInstitutionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GraphicSearchTIController graphicSearchTIController = new GraphicSearchTIController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphicSearchInstitutionAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			
			this.graphicSearchTIController.averageGradeTI(request, response, GraphicSearchTIModel.class);
			
		}catch(Exception exception) {
			response
			.getWriter()
			.append(exception.getMessage());
		}
	}

}
