package Model;

/**
 *
 * @author kathe
 */
public class Faculty {
    
    private String facultyId;
    private String facultyName;
    private String facultyType;
    private String universityId;

    public Faculty(String facultyId, String facultyName, String facultyType, String universityId) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.facultyType = facultyType;
        this.universityId = universityId;
    }


    public String getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
    public String getUniversityId() {
        return universityId;
    }
    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }
    public String getFacultyName() {
        return facultyName;
    }
    public String getFacultyType() {
        return facultyType;
    }
}