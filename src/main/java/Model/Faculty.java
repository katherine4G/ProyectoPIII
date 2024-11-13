package Model;

/**
 *
 * @author kathe
 */

public class Faculty {
    private int facultyId;             
    private String facultyName;         
    private String facultyType;    
    private University university;   

    // Constructor
    public Faculty(int facultyId, String facultyName, String facultyType, University university) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.facultyType = facultyType;
        this.university = university;
    }

    // Getters y Setters
    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyType() {
        return facultyType;
    }

    public void setFacultyType(String facultyType) {
        this.facultyType = facultyType;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    // Método toString para mostrar la información de la facultad
    @Override
    public String toString() {
        return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyType=" + facultyType + 
               ", university=" + university + "]";
    }
}



//public class Faculty {
//    
//    private String facultyId;
//    private String facultyName;
//    private String facultyType;
//    private String universityId;
//
//    public Faculty(String facultyId, String facultyName, String facultyType, String universityId) {
//        this.facultyId = facultyId;
//        this.facultyName = facultyName;
//        this.facultyType = facultyType;
//        this.universityId = universityId;
//    }
//
//
//    public String getFacultyId() {
//        return facultyId;
//    }
//    public void setFacultyId(String facultyId) {
//        this.facultyId = facultyId;
//    }
//    public String getUniversityId() {
//        return universityId;
//    }
//    public void setUniversityId(String universityId) {
//        this.universityId = universityId;
//    }
//    public String getFacultyName() {
//        return facultyName;
//    }
//    public String getFacultyType() {
//        return facultyType;
//    }
//}