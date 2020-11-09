package apis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controllers.UserController;
import helpers.JsonHelper;
import models.UserModel;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/user/*")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserController userController = new UserController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAPI() {
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
			String parameters = request.getRequestURI().replaceFirst("^.*/user/*", "");
			
			if(parameters.isBlank()) {
				// /user
				
				this.userController.findAll(request, response);
				
				
			} else if(parameters.matches("^\\d+$")) {
				// /user/1
				
				this.userController.findFirst("id", Integer.parseInt(parameters), request, response);
				
			
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
		
		this.userController.create(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		this.userController.update(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		if(!request.getRequestURI().matches("^.*/user/\\d+$")) {
			// ! /user/1
			
			response
				.getWriter()
				.append("INVALID ROUTE DELETE");
			
			return;
		}
		
		int id = Integer.parseInt(request.getRequestURI().replaceFirst(".*/user/+", ""));
		this.userController.delete(id, request, response);
		
		
	}
}
