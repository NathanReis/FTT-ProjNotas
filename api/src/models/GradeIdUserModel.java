package models;

import java.util.ArrayList;

public class GradeIdUserModel extends Model{
	private int idUser;
	private ArrayList<GradeIdSubjectModel> subjectGrade = new ArrayList<GradeIdSubjectModel>();
	
	public int getIdUser() {
		return this.idUser;
	}

	public ArrayList<GradeIdSubjectModel> getSubjectGrade() {
		return this.subjectGrade;
	}
	
}
