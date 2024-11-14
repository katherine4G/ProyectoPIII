
package Model;

/**
 *
 * @author kathe
 */

public class Course {
    private String NRC;
    private String codeCourse; 
    private String nameCourse;
    private Department department; 


    public Course(String NRC, String codeCourse, String nameCourse, Department department) {
        this.NRC = NRC;
        this.codeCourse = codeCourse;
        this.nameCourse = nameCourse;
        this.department = department;
    }
    
    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public String getCodeCourse() {
        return codeCourse;
    }
       public void setCodeCourse(String codeCourse) {    
        this.codeCourse = codeCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Department [NRC=" + NRC + ", codeCourse=" + codeCourse + ",nameCourse="+
               ", course=" + (department != null ? department.toString() : "null") + "]";
    }
}
