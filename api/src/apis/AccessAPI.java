package apis;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.AccessController;
import helpers.JsonHelper;
import models.AccessModel;
import models.UserModel;

/**
 * Servlet implementation class AccessAPI
 */
@WebServlet("/access")
public class AccessAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
	private AccessController accessController = new AccessController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessAPI() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		this.accessController.login(request, response);
		
		
	}

}
