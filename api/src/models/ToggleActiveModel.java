package models;

public class ToggleActiveModel {
	private int idTeachingInstitution;
	private int idSubject;
	private String active;
	
	public int getIdTeachingInstitution() {
		return this.idTeachingInstitution;
	}
	
	public void setIdTeachingInstitution(int idTeachingInstitution) {
		this.idTeachingInstitution = idTeachingInstitution;
	}
	
	public int getIdSubject() {
		return this.idSubject;
	}
	
	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}
	
	public String getActive() {
		return this.active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
}
