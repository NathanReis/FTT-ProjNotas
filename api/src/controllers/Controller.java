package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Model;
import repositories.ConnectionDB;
import repositories.Repository;

abstract public class Controller<T extends Model> {
	protected Repository<T> repository;
	
	public Controller(Repository<T> repository) {
		this.repository = repository;
	}
	
	abstract public void create(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	abstract public void delete(int id, HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	abstract public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	abstract public void findFirst(String field, double value, HttpServletRequest request, HttpServletResponse response)throws IOException; 
	
	abstract public void findFirst(String field, int value, HttpServletRequest request, HttpServletResponse response)throws IOException; 
	
	abstract public void findFirst(String field, String value, HttpServletRequest request, HttpServletResponse response)throws IOException; 
	
	abstract public void update(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
