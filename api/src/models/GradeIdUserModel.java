package models;

import java.util.ArrayList;

public class GradeIdUserModel extends Model{
	private int idUser;
	private ArrayList<GradeIdSubjectModel> subjectGrande = new ArrayList<GradeIdSubjectModel>();
	
	public int getIdUser() {
		return idUser;
	}
	
}
