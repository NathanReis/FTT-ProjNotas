package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Model;

abstract public class Repository<TEntity extends Model> {
	protected String table;
	
	public Repository( String table){
		this.table = table;
	}
	
	abstract public void create(TEntity entity) throws SQLException, ClassNotFoundException;
	abstract public TEntity fillModel(ResultSet resulSet) throws SQLException, ClassNotFoundException;
	abstract public void update(TEntity entity) throws SQLException, ClassNotFoundException;
	
	public void delete(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM " + this.table + " WHERE id = ?;"; 
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			 stmt.setInt(1, id);
			 stmt.executeUpdate();
		}
	}
	
	public ArrayList<TEntity> findAll() throws SQLException, ClassNotFoundException {
		String sql = this.getSQLFindAll();
		
		ArrayList<TEntity> models = new ArrayList<TEntity>();
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				models.add(this.fillModel(rs));
			}
		}
		
		return models;
	}
	
	public TEntity findFirst(String field, double value) throws SQLException, ClassNotFoundException {
		String sql = this.getSQLFindFirst(field);
		
		TEntity model = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setDouble(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				model = this.fillModel(rs);
			}
		}
		
		return model;
	}
	
	public TEntity findFirst(String field, int value) throws SQLException, ClassNotFoundException {
		String sql = this.getSQLFindFirst(field);
		
		TEntity model = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setInt(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				model = this.fillModel(rs);
			}
		}
		
		return model;
	}
	
	public TEntity findFirst(String field, String value) throws SQLException, ClassNotFoundException {
		String sql = this.getSQLFindFirst(field);
		
		TEntity model = null;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			stmt.setString(1, value);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				model = this.fillModel(rs);
			}
		}
		
		return model;
	}
	
	public int getInsertedId() throws SQLException, ClassNotFoundException {
		String sql = "SELECT LAST_INSERT_ID() AS id FROM " + this.table + ";";
		
		int id = 0;
		
		try(PreparedStatement stmt = ConnectionDB.getInstance().prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");
			}
		}
		
		return id;
	}
	
	protected String getSQLFindAll() {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + ";";
		
		return sql;
	}
	
	protected String getSQLFindFirst(String field) {
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1;";
		
		return sql;
	}
}
