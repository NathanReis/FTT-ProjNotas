package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.GraphicSearchUserController;
import models.GraphicSearchUserModel;

/**
 * Servlet implementation class GraphicSearchUserAPI
 */
@WebServlet("/GraphicSearchUserAPI/*")
public class GraphicSearchUserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GraphicSearchUserController graphicSearchUserController = new GraphicSearchUserController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphicSearchUserAPI() {
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
			
			this.graphicSearchUserController.averageGradeS(request, response, GraphicSearchUserModel.class);
			
		}catch(Exception exception) {
			response
			.getWriter()
			.append(exception.getMessage());
		}
	}

}
