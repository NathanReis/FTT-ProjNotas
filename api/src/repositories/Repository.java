package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Model;

abstract public class Repository<TEntity extends Model> {
	public TEntity findFirst(Class<TEntity> classEntity, String field, double value) {
		String sql = "SELECT entity ";
		sql       += "FROM " + classEntity.getName() + " AS entity ";
		sql       += "WHERE " + field + " = :" + field;
		
		Query query = Connection.getInstance().createQuery(sql);
		query.setParameter(field, value);
		query.setMaxResults(1);
		
		List<TEntity> results = query.getResultList();
		
		if(results.size() == 0) {
			return null;
		}
		
		return results.get(0);
	}
	
	public TEntity findFirst(Class<TEntity> classEntity, String field, int value) {
		String sql = "SELECT entity ";
		sql       += "FROM " + classEntity.getName() + " AS entity ";
		sql       += "WHERE " + field + " = :" + field;
		
		Query query = Connection.getInstance().createQuery(sql);
		query.setParameter(field, value);
		query.setMaxResults(1);
		
		List<TEntity> results = query.getResultList();
		
		if(results.size() == 0) {
			return null;
		}
		
		return results.get(0);
	}
	
	public TEntity findFirst(Class<TEntity> classEntity, String field, String value) {
		String sql = "SELECT entity ";
		sql       += "FROM " + classEntity.getName() + " AS entity ";
		sql       += "WHERE " + field + " = :" + field;
		
		Query query = Connection.getInstance().createQuery(sql);
		query.setParameter(field, value);
		query.setMaxResults(1);
		
		List<TEntity> results = query.getResultList();
		
		if(results.size() == 0) {
			return null;
		}
		
		return results.get(0);
	}
	
	public void delete(TEntity entity) {
		Connection.getInstance().getTransaction().begin();
		
		try {
			Connection.getInstance().remove(entity);
			
			Connection.getInstance().getTransaction().commit();
		} catch(Exception exception) {
			Connection.getInstance().getTransaction().rollback();
		}
	}

	public List<TEntity> findAll(Class<TEntity> classEntity) {
		String sql = "SELECT entity ";
		sql       += "FROM " + classEntity.getName() + " AS entity";
		
		Query query = Connection.getInstance().createQuery(sql);
		List<TEntity> result = query.getResultList();
		
		return result;
	}

	public TEntity save(TEntity entity, int id) {
		Connection.getInstance().getTransaction().begin();
		
		try {
			if(id == 0) {
				Connection.getInstance().persist(entity);
			} else {
				Connection.getInstance().merge(entity);
			}
			
			Connection.getInstance().getTransaction().commit();
		} catch(Exception exception) {
			Connection.getInstance().getTransaction().rollback();
		}
		
		return entity;
	}
}
