package models;

public class SubjectXUserModel extends Model {
	private SubjectModel subject;
	private TeachingInstitutionModel teachingInstitution;
	private UserModel user;
	private double grade;
	private int semester;
	private int year;
	
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
