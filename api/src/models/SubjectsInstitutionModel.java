package models;

public class SubjectsInstitutionModel {
	private SubjectModel subject;
	private String active;
	
	public SubjectModel getSubject() {
		return this.subject;
	}
	
	public void setSubject(SubjectModel subject) {
		this.subject = subject;
	}
	
	public String getActive() {
		return this.active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
}
