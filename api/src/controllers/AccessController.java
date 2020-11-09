package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helpers.JsonHelper;
import models.AccessModel;
import models.UserModel;
import repositories.ConnectionDB;
import repositories.AccessRepository;

public class AccessController {
	protected AccessRepository repository = new AccessRepository();
	private Gson gson = new Gson();
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		UserModel userModel = null;
		
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			AccessModel accessModel = this.gson.fromJson(stringJson, AccessModel.class);
			
			userModel = this.repository.login(accessModel);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(userModel));
			
		} catch(Exception exception) {
			response
				.getWriter()
				.append(exception.getMessage());
		}
		
	}
}
