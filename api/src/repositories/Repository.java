package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.Model;
import models.TeachingInstitutionModel;

abstract public class Repository<TEntity extends Model> {
	
	protected String table;
	
	Repository( String table){
		this.table = table;
	}
	
	abstract public int create(TEntity entity)throws SQLException;
	
	abstract public List<TEntity> findAll()throws SQLException;
	
	abstract public TEntity findFirst(String field, double value)throws SQLException;
	
	abstract public TEntity findFirst(String field, int value)throws SQLException;
	
	abstract public TEntity findFirst(String field, String value)throws SQLException;
	
	abstract public void update(TEntity entity)throws SQLException;
	
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM " + this.table + " WHERE id = ?"; 
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			 stmt.setInt(1, id);
			 stmt.executeUpdate();
		}
	}		
	
	public int getInsertedId()throws SQLException{
		String sql = "SELECT LAST_INSERT_ID() AS id FROM " + this.table;
		
		int id = 0;
		
		try(PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
					
				id = rs.getInt("id");
			}
		}
		
		return id;
	}
} 

