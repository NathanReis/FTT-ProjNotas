package models;

import java.util.ArrayList;

public class TeachingInstitutionModel extends Model {
	private String name;
	private ArrayList<SubjectsInstitutionModel> subjects = new ArrayList<SubjectsInstitutionModel>();
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<SubjectsInstitutionModel> getSubjects() {
		return this.subjects;
	}
	
	public void addSubject(SubjectsInstitutionModel subject) {
		this.subjects.add(subject);
	}
}
