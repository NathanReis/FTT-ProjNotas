package services;

import java.sql.SQLException;
import models.Model;

abstract public class Service <T extends Model>{
	
	public void ValidaUpdate(T userName)throws SQLException, ClassNotFoundException{};
	public void ValidaUser(T userName)throws SQLException, ClassNotFoundException{};
}
