package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import jdk.jfr.Unsigned;

public abstract class Model {
	@Id
	@GeneratedValue
	@Unsigned
	protected int id;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
