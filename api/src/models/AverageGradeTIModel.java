package models;

public class AverageGradeTIModel extends Model{
	private double averageTeachingInstitution;
	private double averageOther;
	
	public double getAverageTeachingInstitution() {
		return averageTeachingInstitution;
	}
	
	public void setAverageTeachingInstitution(double averageTeachingInstitution) {
		this.averageTeachingInstitution = averageTeachingInstitution;
	}

	public double getAverageOther() {
		return averageOther;
	}

	public void setAverageOther(double averageOther) {
		this.averageOther = averageOther;
	}
	
}
