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

import jdk.jfr.Unsigned;

@Entity
@Table(
	name = "tbSubjectsXTeachingInstitutions"
)
public class SubjectXTeachingInstitutionModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unsigned
	protected int id;
	@ManyToOne
	@JoinColumn(
		name = "idSubject",
		foreignKey = @ForeignKey(name = "fk_tbSubjectsXTeachingInstitutions_idSubject")
	)
	private SubjectModel subject;
	@ManyToOne
	@JoinColumn(
		name = "idTeachingInstitution",
		foreignKey = @ForeignKey(name = "fk_tbSubjectsXTeachingInstitutions_idTeachingInstitution")
	)
	private TeachingInstitutionModel teachingInstitution;
	@Column
	private String active;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public SubjectModel getSubject() {
		return this.subject;
	}
	
	public void setSubject(SubjectModel subject) {
		this.subject = subject;
	}
	
	public TeachingInstitutionModel getTeachingInstitution() {
		return this.teachingInstitution;
	}
	
	public void setTeachingInstitution(TeachingInstitutionModel teachingInstitution) {
		this.teachingInstitution = teachingInstitution;
	}
	
	public String getActive() {
		return this.active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
}
