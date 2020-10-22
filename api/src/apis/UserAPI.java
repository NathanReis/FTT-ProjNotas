package apis;

import java.io.IOException;
import javax.servlet.ServletConfig;
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
@WebServlet("/user")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson _gson;
	private UserController _userController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAPI() {
        super();
        
        _gson = new Gson();
        _userController = new UserController();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
				
		response
			.getWriter()
			.append("GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			
			String stringJson = JsonHelper.getJsonRequest(request);
			UserModel userModel = _gson.fromJson(stringJson, UserModel.class);
			_userController.create(userModel);
			
			response
				.getWriter()
				.append("POST");
		} catch(Exception err) {
			response
				.getWriter()
				.append(err.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		response
			.getWriter()
			.append("PUT");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		response
			.getWriter()
			.append("DELETE");
	}

}
