package Model;

/**
 *
 * @author kathe
 */
public class EnrollmentProfessor {
    private int enrollProfessorId;
    private User professor; 
    private Course course;

    public EnrollmentProfessor(int enrollProfessorId, User professor, Course course) {
        this.enrollProfessorId = enrollProfessorId;
        this.professor = professor;
        this.course = course;
    }

    public int getEnrollProfessorId() { return enrollProfessorId; }
    public void setEnrollProfessorId(int enrollProfessorId) { this.enrollProfessorId = enrollProfessorId; }
    public User getProfessor() { return professor; }
    public void setProfessor(User professor) { this.professor = professor; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
