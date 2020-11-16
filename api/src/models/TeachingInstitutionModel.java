package models;

import java.util.ArrayList;

public class TeachingInstitutionModel extends Model {
	private String name;
	private ArrayList<SubjectInstitutionModel> subjects = new ArrayList<SubjectInstitutionModel>();
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<SubjectInstitutionModel> getSubjects() {
		return this.subjects;
	}
	
	public void addSubject(SubjectInstitutionModel subject) {
		this.subjects.add(subject);
	}
}
