package controllers;

import java.util.List;

import models.Model;
import models.UserModel;
import repositories.ConexaoBd;
import repositories.Repository;

abstract public class Controller<T extends Model>{
	
	protected Repository repositorio;
	
	Controller(Repository repositorio){
		this.repositorio = repositorio;
	}
	
	public void create(T entModel) {
		try {
			
			int id = this.repositorio.create(entModel);
			entModel.setId(id);
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void delete(int id) {
		
		try {
			
			this.repositorio.delete(id);
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<T> findAll() {
		
		List<T> entModels = null;
		
		try {
			
			entModels = this.repositorio.findAll();
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return entModels;
	}
	
	public T findFirst( String field, double value) {
		
		T entModel = null;
		
		try {
			
			entModel = (T) this.repositorio.findFirst(field, value);
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return entModel;
	
	}
	
	public T findFirst( String field, int value) {
		
		T entModel = null;
		
		try {
			
			entModel = (T) this.repositorio.findFirst(field, value);
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return entModel;
	
	}
	
	public T findFirst( String field, String value) {
		
		T entModel = null;
		
		try {
			
			entModel = (T) this.repositorio.findFirst(field, value);
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return entModel;
	
	}
	
	public void update(T entModel) {
		try {
			
			this.repositorio.update(entModel);
			ConexaoBd.fecharConexao();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}

