package models;

public class GraphicSearchUserModel extends Model{
	private int idUser;
	private int idSubject;
	private int idTeachingInstitution;
	private int semester;
	private int year;
	
	public int getIdUser() {
		return idUser;
	}

	public int getIdSubject() {
		return idSubject;
	}

	public int getIdTeachingInstitution() {
		return idTeachingInstitution;
	}

	public int getSemester() {
		return semester;
	}

	public int getYear() {
		return year;
	}
	
}
