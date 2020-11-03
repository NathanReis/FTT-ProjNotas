package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.TeachingInstitutionModel;

public class TeachingInstitutionRepository extends Repository<TeachingInstitutionModel> {
	public TeachingInstitutionRepository() {
		super("tbTeachingInstitutions");
	}

	@Override
	public int create(TeachingInstitutionModel entity) throws SQLException {
		String sql = "INSERT INTO " + this.table + " ";
		sql       += "    (name) ";
		sql       += "VALUES ";
		sql       += "    (?)";
		
		try(PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			
			stmt.setString(1, entity.getName());
			stmt.executeUpdate();
		}
		return this.getInsertedId();
	}

	@Override
	public List<TeachingInstitutionModel> findAll() throws SQLException {
		
		String sql = "SELECT * ";
		sql       += "FROM " + this.table;
		
		List<TeachingInstitutionModel> teachingInstitution = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				TeachingInstitutionModel tIM = new TeachingInstitutionModel();
				tIM.setId(rs.getInt("id"));
				tIM.setName(rs.getString("name"));
				teachingInstitution.add(tIM);
				
			}
		}
		
		return teachingInstitution;
	}

	@Override
	public TeachingInstitutionModel findFirst(String field, double value) throws SQLException {
		
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1";
		
		TeachingInstitutionModel tIM = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setDouble(1, value);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				tIM.setId(rs.getInt("id"));
				tIM.setName(rs.getString("name"));
			}
		}
		
		return tIM;
	}

	@Override
	public TeachingInstitutionModel findFirst(String field, int value) throws SQLException {
		
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1";
		
		TeachingInstitutionModel tIM = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setInt(1, value);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				tIM.setId(rs.getInt("id"));
				tIM.setName(rs.getString("name"));
			}
		}
		
		return tIM;
	}

	@Override
	public TeachingInstitutionModel findFirst(String field, String value) throws SQLException {
		
		String sql = "SELECT * ";
		sql       += "FROM " + this.table + " ";
		sql       += "WHERE " + field + " = ? ";
		sql       += "LIMIT 1";
		
		TeachingInstitutionModel tIM = null;
		
		try (PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setString(1, value);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				tIM.setId(rs.getInt("id"));
				tIM.setName(rs.getString("name"));
			}
		}
		
		return tIM;
	}

	@Override
	public void update(TeachingInstitutionModel entity) throws SQLException {
		
		String sql = "UPDATE " + this.table + " ";
		sql       += "SET ";
		sql       += "  name = ? ";
		sql       += "WHERE ";
		sql       += "   id = ?";
		
		try(PreparedStatement stmt = ConexaoBd.getConexao().prepareStatement(sql)){
			stmt.setString(1,entity.getName());
			stmt.setInt(2, entity.getId());
			
			stmt.executeUpdate();
		}
		
	}
	
	
}

