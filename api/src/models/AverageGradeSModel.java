package models;

public class AverageGradeSModel extends Model{
	private double grade;
	private double averageInstitution;
	private double averageOther;
	
	public double getGrade() {
		return grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public double getAverageInstitution() {
		return averageInstitution;
	}
	
	public void setAverageInstitution(double averageInstitution) {
		this.averageInstitution = averageInstitution;
	}
	
	public double getAverageOther() {
		return averageOther;
	}
	
	public void setAverageOther(double averageOther) {
		this.averageOther = averageOther;
	}
	
}
