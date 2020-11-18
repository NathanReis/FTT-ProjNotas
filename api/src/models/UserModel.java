package models;

import java.util.ArrayList;

public class UserModel extends Model {
	private String userName;
	private String password;
	private String type;
	private TeachingInstitutionModel teachingInstitution;
	private ArrayList<SubjectUserModel> subjects = new ArrayList<SubjectUserModel>();
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public TeachingInstitutionModel getTeachingInstitution() {
		return this.teachingInstitution;
	}

	public void setTeachingInstitution(TeachingInstitutionModel teachingInstitution) {
		this.teachingInstitution = teachingInstitution;
	}

	public ArrayList<SubjectUserModel> getSubjects() {
		return this.subjects;
	}
	
	public void addSubject(SubjectUserModel subject) {
		this.subjects.add(subject);
	}
}
