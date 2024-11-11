
package Model;

/**
 *
 * @author kathe
 */

public class Course {
    private String NRC;
    private String codeCourse; 
    private String nameCourse;


    public Course(String NRC, String codeCourse, String nameCourse) {
        this.NRC = NRC;
        this.codeCourse = codeCourse;
        this.nameCourse = nameCourse;
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
       @Override
    public String toString() {
        return "Course{" +
                "NRC='" + NRC + '\'' +
                ", código='" + codeCourse + '\'' +
                ", Nombre='" + nameCourse + '\'' +
                '}';
    }
}
