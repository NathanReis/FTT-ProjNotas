package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helpers.JsonHelper;
import models.ErrorModel;
import models.Model;
import repositories.ConnectionDB;
import repositories.Repository;

abstract public class Controller<T extends Model> {
	protected Gson gson = new Gson();
	protected Repository<T> repository;
	
	public Controller(Repository<T> repository) {
		this.repository = repository;
	}
	
	public void create(HttpServletRequest request, HttpServletResponse response, Class<T> classType) throws IOException {
		try {
			String stringJson = JsonHelper.getJsonRequest(request);
			T model = this.gson.fromJson(stringJson, classType);
			
			int id = this.repository.create(model);
			
			model.setId(id);
			
			ConnectionDB.closeInstance();
			
			response
				.getWriter()
				.append(this.gson.toJson(model));
		} catch(Exception exception) {
			ErrorModel error = new ErrorModel();
			error.setHasError(true);
			error.setMessageError(exception.getMessage());
			
			response
				.getWriter()
				.append(this.gson.toJson(error));
		}
	}
	
	abstract public void delete(int id, HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	abstract public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	abstract public void findFirst(String field, double value, HttpServletRequest request, HttpServletResponse response)throws IOException; 
	
	abstract public void findFirst(String field, int value, HttpServletRequest request, HttpServletResponse response)throws IOException; 
	
	abstract public void findFirst(String field, String value, HttpServletRequest request, HttpServletResponse response)throws IOException; 
	
	abstract public void update(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
