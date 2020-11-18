package models;

public class SubjectUserModel {
	private SubjectModel subject;
	private double grade;
	private int semester;
	private int year;
	
	public SubjectModel getSubject() {
		return this.subject;
	}
	
	public void setSubject(SubjectModel subject) {
		this.subject = subject;
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
