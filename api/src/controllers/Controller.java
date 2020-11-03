package controllers;

import java.util.ArrayList;

import models.Model;
import repositories.ConnectionDB;
import repositories.Repository;

abstract public class Controller<T extends Model> {
	protected Repository<T> repository;
	
	public Controller(Repository<T> repository) {
		this.repository = repository;
	}
	
	public void create(T model) {
		try {
			int id = this.repository.create(model);
			
			model.setId(id);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			this.repository.delete(id);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<T> findAll() {
		ArrayList<T> models = null;
		
		try {
			models = this.repository.findAll();
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return models;
	}
	
	public T findFirst(String field, double value) {
		T model = null;
		
		try {
			model = (T)this.repository.findFirst(field, value);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	public T findFirst(String field, int value) {
		T model = null;
		
		try {
			model = (T)this.repository.findFirst(field, value);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	public T findFirst(String field, String value) {
		T model = null;
		
		try {
			model = (T)this.repository.findFirst(field, value);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	public void update(T model) {
		try {
			this.repository.update(model);
			
			ConnectionDB.closeInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
