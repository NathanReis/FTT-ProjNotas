package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import jdk.jfr.Unsigned;

@Entity
@Table(
	name = "tbUsers",
	uniqueConstraints = @UniqueConstraint(
		columnNames = {
			"userName"
		}
	)
)
public class UserModel extends Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unsigned
	protected int id;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String type;
	@ManyToOne
	@JoinColumn(
		name = "idTeachingInstitution",
		foreignKey = @ForeignKey(name = "fk_tbUsers_idTeachingInstitution")
	)
	private TeachingInstitutionModel teachingInstitution; 
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public TeachingInstitutionModel getTeachingInstitution() {
		return this.teachingInstitution;
	}

	public void setType(TeachingInstitutionModel teachingInstitution) {
		this.teachingInstitution = teachingInstitution;
	}
}
