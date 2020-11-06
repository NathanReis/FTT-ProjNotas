package services;

import java.sql.SQLException;
import models.Model;

abstract public class Service <T extends Model>{
	
	abstract public void ValidaUser(T userName)throws SQLException, ClassNotFoundException;
}
