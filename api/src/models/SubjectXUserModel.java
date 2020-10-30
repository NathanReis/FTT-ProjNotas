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
	name = "tbSubjectsXUsers"
)
public class SubjectXUserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unsigned
	private int id;
	@ManyToOne
	@JoinColumn(
		name = "idSubject",
		foreignKey = @ForeignKey(name = "fk_tbSubjectsXUsers_idSubject")
	)
	private SubjectModel subject;
	@ManyToOne
	@JoinColumn(
		name = "idTeachingInstitution",
		foreignKey = @ForeignKey(name = "fk_tbSubjectsXUsers_idTeachingInstitution")
	)
	private TeachingInstitutionModel teachingInstitution;
	@ManyToOne
	@JoinColumn(
		name = "idUser",
		foreignKey = @ForeignKey(name = "fk_tbSubjectsXUsers_idUser")
	)
	private UserModel user;
	@Column
	private double grade;
	@Column
	@Unsigned
	private int semester;
	@Column
	@Unsigned
	private int year;
	
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
	
	public UserModel getUser() {
		return this.user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public double getGrade() {
		return this.grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public int getSemester() {
		return this.semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
}
