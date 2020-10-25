package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import jdk.jfr.Unsigned;

@Entity
@Table(
	name = "tbTeachingInstitutions",
	uniqueConstraints = @UniqueConstraint(
		columnNames = {
			"name"
		}
	)
)
public class TeachingInstitutionModel extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unsigned
	private int id;
	@Column
	private String name;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId(String name) {
		this.name = name;
	}
}
