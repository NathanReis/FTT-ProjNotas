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
	name = "tbSubjects",
	uniqueConstraints = @UniqueConstraint(
		columnNames = {
			"description"
		}
	)
)
public class SubjectModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unsigned
	private int id;
	@Column
	private String description;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
