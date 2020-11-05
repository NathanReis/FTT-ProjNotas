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
	
	private Gson gson = new Gson();
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
				
				List<UserModel> userModels = this.userController.findAll();
				
				response
					.getWriter()
					.append(this.gson.toJson(userModels));
			} else if(parameters.matches("^\\d+$")) {
				// /user/1
				
				UserModel userModel = this.userController.findFirst("id", Integer.parseInt(parameters));
				
				response
					.getWriter()
					.append(this.gson.toJson(userModel));
			
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
		
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			UserModel userModel = this.gson.fromJson(stringJson, UserModel.class);
			
			this.userController.create(userModel);
			
			response
				.getWriter()
				.append(this.gson.toJson(userModel));
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
		
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			UserModel userModel = this.gson.fromJson(stringJson, UserModel.class);
			
			this.userController.update(userModel);
			
			response
				.getWriter()
				.append(this.gson.toJson(userModel));
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
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
		
		try {
			int id = Integer.parseInt(request.getRequestURI().replaceFirst(".*/user/+", ""));
			this.userController.delete(id);
			
			response
				.getWriter()
				.append("DELETE");
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
	}
}
