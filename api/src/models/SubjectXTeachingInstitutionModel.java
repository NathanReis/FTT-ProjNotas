package models;

public class SubjectXTeachingInstitutionModel extends Model {
	private SubjectModel subject;
	private TeachingInstitutionModel teachingInstitution;
	private String active;
	
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
