package Model;

/**
 *
 * @author kathe
 */
public class EnrollmentStudent {
    private int enrollStudentId;
    private User student; 
    private Course course; 

    public EnrollmentStudent(int enrollStudentId, User student, Course course) {
        this.enrollStudentId = enrollStudentId;
        this.student = student;
        this.course = course;
    }

    public int getEnrollStudentId() { return enrollStudentId; }
    public void setEnrollStudentId(int enrollStudentId) { this.enrollStudentId = enrollStudentId; }
    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
